package org.nailedtothex.jberetweb.logic;

import org.nailedtothex.jberetweb.dto.JobParameterDto;

import javax.batch.operations.JobOperator;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;
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

    public void init() {
        List<JobParameterDto> params = new ArrayList<>();
        startJobViewBean.setJobParameters(params);
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
            final Long executionId = singleton.doWorkWithJobOperator(new JobOperatorWork<Long>() {
                @Override
                public Long doWorkWithJobOperator(JobOperator jobOperator) {
                    return jobOperator.start(startJobViewBean.getJobName(), props);
                }
            });
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "job started. execution id=" + executionId, null));
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
