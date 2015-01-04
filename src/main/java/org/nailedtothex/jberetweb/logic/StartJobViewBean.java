package org.nailedtothex.jberetweb.logic;

import org.nailedtothex.jberetweb.dto.JobParameterDto;

import javax.batch.runtime.JobExecution;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class StartJobViewBean implements Serializable {

    String jobName;
    List<JobParameterDto> jobParameters;
    String newJobParameterName;
    String newJobParameterValue;
    JobExecution oldJobExecution;

    public JobExecution getOldJobExecution() {
        return oldJobExecution;
    }

    public void setOldJobExecution(JobExecution oldJobExecution) {
        this.oldJobExecution = oldJobExecution;
    }

    public String getNewJobParameterName() {
        return newJobParameterName;
    }

    public void setNewJobParameterName(String newJobParameterName) {
        this.newJobParameterName = newJobParameterName;
    }

    public String getNewJobParameterValue() {
        return newJobParameterValue;
    }

    public void setNewJobParameterValue(String newJobParameterValue) {
        this.newJobParameterValue = newJobParameterValue;
    }

    public List<JobParameterDto> getJobParameters() {
        return jobParameters;
    }

    public void setJobParameters(List<JobParameterDto> jobParameters) {
        this.jobParameters = jobParameters;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
}
