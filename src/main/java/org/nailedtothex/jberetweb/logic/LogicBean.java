package org.nailedtothex.jberetweb.logic;

import org.nailedtothex.jberetweb.dto.JobExecutionTableRowDto;
import org.nailedtothex.jberetweb.dto.JobParameterDto;
import org.nailedtothex.jberetweb.entity.JobExecution;
import org.nailedtothex.jberetweb.entity.StepExecution;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Named
@RequestScoped
public class LogicBean {

    private static final int ROWS_IN_SINGLE_PAGE = 10;
    @Inject
    DataBean dataBean;
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
}
