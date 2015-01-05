package org.nailedtothex.jberetweb.logic;

import org.nailedtothex.jberetweb.dto.JobExecutionAction;
import org.nailedtothex.jberetweb.dto.JobExecutionTableRowDto;
import org.nailedtothex.jberetweb.dto.JobParameterDto;
import org.nailedtothex.jberetweb.entity.JobExecution;
import org.nailedtothex.jberetweb.entity.StepExecution;

import javax.batch.operations.JobOperator;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Named
@RequestScoped
public class IndexRequestBean {

    private static final int ROWS_IN_SINGLE_PAGE = 10;

    private static final SelectItem[] ACTIONS_FOR_COMPLETED_OR_ABANDONED = new SelectItem[]{
            new SelectItem(JobExecutionAction.SELECT, JobExecutionAction.SELECT.getLabel()),
            new SelectItem(JobExecutionAction.RE_EXECUTE, JobExecutionAction.RE_EXECUTE.getLabel())
    };

    private static final SelectItem[] ACTIONS_FOR_STOPPED_OR_FAILED = new SelectItem[]{
            new SelectItem(JobExecutionAction.SELECT, JobExecutionAction.SELECT.getLabel()),
            new SelectItem(JobExecutionAction.RESTART, JobExecutionAction.RESTART.getLabel()),
            new SelectItem(JobExecutionAction.ABANDON, JobExecutionAction.ABANDON.getLabel()),
            new SelectItem(JobExecutionAction.RE_EXECUTE, JobExecutionAction.RE_EXECUTE.getLabel())
    };

    private static final SelectItem[] ACTIONS_FOR_STARTING_OR_STARTED = new SelectItem[]{
            new SelectItem(JobExecutionAction.SELECT, JobExecutionAction.SELECT.getLabel()),
            new SelectItem(JobExecutionAction.STOP, JobExecutionAction.STOP.getLabel()),
            new SelectItem(JobExecutionAction.RE_EXECUTE, JobExecutionAction.RE_EXECUTE.getLabel()),
    };

    @Inject
    IndexViewBean dataBean;
    @Inject
    JBeretWebSingleton singleton;
    @PersistenceContext
    EntityManager em;

    public void init() {
        Long count = em.createNamedQuery("countJobExecution", Long.class).getSingleResult();
        dataBean.setJobExecutionCount(count);
        dataBean.setJobExecutionTablePaginator(new TablePaginator(count, ROWS_IN_SINGLE_PAGE));
        fetch();
    }

    public void fetch() {
        final List<JobExecutionTableRowDto> data = em.createNamedQuery("findJobExecutionTableRow", JobExecutionTableRowDto.class)
                .setFirstResult((int) (dataBean.getJobExecutionTablePaginator().getCurrentRangeBegin() - 1))
                .setMaxResults(ROWS_IN_SINGLE_PAGE)
                .getResultList();
        dataBean.setJobExecutionTableRowDtos(data);
    }

    public void selectJobExecution(Integer jobExecutionId) {
        dataBean.setJobExecution(em.find(JobExecution.class, jobExecutionId));
        dataBean.setJobParameterDtos(parseJobParameters(dataBean.getJobExecution().getJobparameters()));
        dataBean.setStepExecutions(em.createNamedQuery("findStepExecutionsByJobExecutionId", StepExecution.class)
                .setParameter("jobExecutionId", jobExecutionId.longValue())
                .getResultList());
        dataBean.setExecutionException(getExecutionException(dataBean.getStepExecutions()));
    }

    protected static String getExecutionException(Collection<StepExecution> col) {
        for (StepExecution stepExecution : col) {
            if (stepExecution.getExecutionexception() != null) {
                return stepExecution.getExecutionexception();
            }
        }
        return null;
    }

    protected static List<JobParameterDto> parseJobParameters(String jobParameters) {
        if (jobParameters == null || jobParameters.trim().length() <= 0) {
            return Collections.emptyList();
        }

        final String[] params = jobParameters.split("\\n");
        List<JobParameterDto> list = new ArrayList<>(params.length);
        for (String param : params) {
            final int i = param.indexOf('=');
            final JobParameterDto dto = new JobParameterDto();
            dto.setName(param.substring(0, i - 1));
            dto.setValue(param.substring(i + 2, param.length()));
            list.add(dto);
        }

        Collections.sort(list);
        return list;
    }

    public void jobExecutionTableNext() {
        dataBean.getJobExecutionTablePaginator().next();
        fetch();
    }

    public void jobExecutionTablePrevious() {
        dataBean.getJobExecutionTablePaginator().previous();
        fetch();
    }

    public void jobExecutionTableFirst() {
        dataBean.getJobExecutionTablePaginator().first();
        fetch();
    }

    public void jobExecutionTableLast() {
        dataBean.getJobExecutionTablePaginator().last();
        fetch();
    }

    public boolean isSelectedJobExecution(Integer jobExecutionId) {
        return dataBean.getJobExecution() != null && Objects.equals(jobExecutionId, dataBean.getJobExecution().getJobexecutionid());
    }

    private static final SelectItem[] EMPTY_ITEM = new SelectItem[0];
    
    public SelectItem[] getSelectItems(String batchStatus) {
        switch (batchStatus) {
            case "COMPLETED":
            case "ABANDONED":
                return ACTIONS_FOR_COMPLETED_OR_ABANDONED;
            case "STOPPED":
            case "FAILED":
                return ACTIONS_FOR_STOPPED_OR_FAILED;
            case "STARTING":
            case "STARTED":
                return ACTIONS_FOR_STARTING_OR_STARTED;
        }
        return EMPTY_ITEM;
    }

    public void selectAction(JobExecutionTableRowDto d) throws NamingException {
        long executionId = (long) d.getJobExecutionId();
        switch (d.getAction()) {
            case SELECT:
                // nop
                return;
            case STOP:
                stop(executionId);
                break;
            case ABANDON:
                abandon(executionId);
                break;
        }
        fetch();
    }

    public void stop(final long executionId) throws NamingException {
        singleton.doWorkWithJobOperator(new JobOperatorWork<Object>() {
            @Override
            public Object doWorkWithJobOperator(JobOperator jobOperator) {
                jobOperator.stop(executionId);
                return null;
            }
        });
    }

    public void abandon(final long executionId) throws NamingException {
        singleton.doWorkWithJobOperator(new JobOperatorWork<Object>() {
            @Override
            public Object doWorkWithJobOperator(JobOperator jobOperator) {
                jobOperator.abandon(executionId);
                return null;
            }
        });
    }
}
