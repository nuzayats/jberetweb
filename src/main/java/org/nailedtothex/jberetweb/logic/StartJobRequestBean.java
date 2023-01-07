package org.nailedtothex.jberetweb.logic;

import org.nailedtothex.jberetweb.dto.JobParameterDto;

import jakarta.batch.operations.JobOperator;
import jakarta.batch.runtime.JobExecution;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import javax.naming.NamingException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Named
@RequestScoped
public class StartJobRequestBean {

    @Inject
    StartJobViewBean startJobViewBean;
    @Inject
    JBeretWebSingleton singleton;
    Long restartJobExecutionId;
    Long reExecJobExecutionId;
    @PersistenceContext
    EntityManager em;

    public Long getReExecJobExecutionId() {
        return reExecJobExecutionId;
    }

    public void setReExecJobExecutionId(Long reExecJobExecutionId) {
        this.reExecJobExecutionId = reExecJobExecutionId;
    }

    public Long getRestartJobExecutionId() {
        return restartJobExecutionId;
    }

    public void setRestartJobExecutionId(Long restartJobExecutionId) {
        this.restartJobExecutionId = restartJobExecutionId;
    }

    public void init() throws NamingException {
        List<JobParameterDto> params = new ArrayList<>();
        startJobViewBean.setJobParameters(params);

        final long oldJobExecutionId;
        if (restartJobExecutionId != null) {
            startJobViewBean.setStartJobType(StartJobType.RESTART);
            oldJobExecutionId = restartJobExecutionId;
        } else if (reExecJobExecutionId != null) {
            startJobViewBean.setStartJobType(StartJobType.RE_EXEC);
            oldJobExecutionId = reExecJobExecutionId;
        } else {
            startJobViewBean.setStartJobType(StartJobType.NEW);
            return;
        }

        final String applicationName = singleton.getApplicationNameByJobExecutionId(oldJobExecutionId);
        final JobExecution jobExecution = singleton.doWorkWithJobOperator(new JobOperatorWork<JobExecution>() {
            @Override
            public JobExecution doWorkWithJobOperator(JobOperator jobOperator) {
                return jobOperator.getJobExecution(oldJobExecutionId);
            }
        }, applicationName);
        startJobViewBean.setOldJobExecution(jobExecution);
        startJobViewBean.setJobName(jobExecution.getJobName());
        startJobViewBean.setApplicationName(applicationName);

        for (Object o : jobExecution.getJobParameters().keySet()) {
            String key = String.valueOf(o);
            params.add(new JobParameterDto(key, jobExecution.getJobParameters().getProperty(key)));
        }
    }

    public boolean isRestart() {
        return startJobViewBean.getStartJobType() == StartJobType.RESTART;
    }

    public boolean isReExec() {
        return startJobViewBean.getStartJobType() == StartJobType.RE_EXEC;
    }

    public void addJobParameter() {
        startJobViewBean.getJobParameters().add(new JobParameterDto(startJobViewBean.getNewJobParameterName(), startJobViewBean.getNewJobParameterValue()));
        startJobViewBean.setNewJobParameterName("");
        startJobViewBean.setNewJobParameterValue("");
    }

    public void removeJobParameter(int index) {
        startJobViewBean.getJobParameters().remove(index);
    }

    public void validateJobParameterName(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        final List<JobParameterDto> p = startJobViewBean.getJobParameters();
        for (JobParameterDto dto : p) {
            if (dto.getName().equals(value)) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Job Parameter '" + value + "' already exists",
                        null));
            }
        }
    }

    public void start() {
        final Properties props = new Properties();
        for (JobParameterDto jobParameterDto : startJobViewBean.getJobParameters()) {
            props.setProperty(jobParameterDto.getName(), jobParameterDto.getValue());
        }

        try {
            final Long executionId;
            final StringBuilder msg = new StringBuilder("job ");

            if (!isRestart()) {
                executionId = singleton.doWorkWithJobOperator(new JobOperatorWork<Long>() {
                    @Override
                    public Long doWorkWithJobOperator(JobOperator jobOperator) {
                        return jobOperator.start(startJobViewBean.getJobName(), props);
                    }
                }, startJobViewBean.getApplicationName());
                msg.append("started.");
            } else {
                executionId = singleton.doWorkWithJobOperator(new JobOperatorWork<Long>() {
                    @Override
                    public Long doWorkWithJobOperator(JobOperator jobOperator) {
                        return jobOperator.restart(startJobViewBean.getOldJobExecution().getExecutionId(), props);
                    }
                }, startJobViewBean.getApplicationName());
                msg.append("restarted.");
            }
            msg.append(" execution id is: ").append(executionId);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, msg.toString(), null));
        } catch (NamingException e) {
            try (StringWriter sw = new StringWriter();
                 PrintWriter pw = new PrintWriter(sw)) {
                e.printStackTrace(pw);
                pw.flush();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "error: " + sw.toString(), null));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
