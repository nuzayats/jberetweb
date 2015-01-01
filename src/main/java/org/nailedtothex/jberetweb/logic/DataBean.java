package org.nailedtothex.jberetweb.logic;

import org.nailedtothex.jberetweb.dto.JobExecutionTableRowDto;
import org.nailedtothex.jberetweb.dto.JobParameterDto;
import org.nailedtothex.jberetweb.entity.JobExecution;
import org.nailedtothex.jberetweb.entity.StepExecution;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class DataBean implements Serializable {

    Long jobExecutionCount;
    JobExecution jobExecution;
    List<JobExecutionTableRowDto> jobExecutionTableRowDtos;
    List<JobParameterDto> jobParameterDtos;
    List<StepExecution> stepExecutions;
    TablePaginator jobExecutionTablePaginator;
    String executionException;

    public String getExecutionException() {
        return executionException;
    }

    public void setExecutionException(String executionException) {
        this.executionException = executionException;
    }

    public TablePaginator getJobExecutionTablePaginator() {
        return jobExecutionTablePaginator;
    }

    public void setJobExecutionTablePaginator(TablePaginator jobExecutionTablePaginator) {
        this.jobExecutionTablePaginator = jobExecutionTablePaginator;
    }

    public JobExecution getJobExecution() {
        return jobExecution;
    }

    public void setJobExecution(JobExecution jobExecution) {
        this.jobExecution = jobExecution;
    }

    public List<StepExecution> getStepExecutions() {
        return stepExecutions;
    }

    public void setStepExecutions(List<StepExecution> stepExecutions) {
        this.stepExecutions = stepExecutions;
    }

    public Long getJobExecutionCount() {
        return jobExecutionCount;
    }

    public void setJobExecutionCount(Long jobExecutionCount) {
        this.jobExecutionCount = jobExecutionCount;
    }

    public List<JobExecutionTableRowDto> getJobExecutionTableRowDtos() {
        return jobExecutionTableRowDtos;
    }

    public void setJobExecutionTableRowDtos(List<JobExecutionTableRowDto> jobExecutionTableRowDtos) {
        this.jobExecutionTableRowDtos = jobExecutionTableRowDtos;
    }

    public List<JobParameterDto> getJobParameterDtos() {
        return jobParameterDtos;
    }

    public void setJobParameterDtos(List<JobParameterDto> jobParameterDtos) {
        this.jobParameterDtos = jobParameterDtos;
    }
}
