package org.nailedtothex.jberetweb.dto;

import java.io.Serializable;
import java.util.Date;

public class JobExecutionTableRowDto implements Serializable {
    Integer jobExecutionId;
    String jobName;
    Date startTime;
    Date endTime;
    String batchStatus;
    JobExecutionAction action = JobExecutionAction.SELECT;

    public JobExecutionTableRowDto(Integer jobExecutionId, String jobName, Date startTime, Date endTime, String batchStatus) {
        this.jobExecutionId = jobExecutionId;
        this.jobName = jobName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.batchStatus = batchStatus;
    }

    public JobExecutionTableRowDto() {
    }

    public JobExecutionAction getAction() {
        return action;
    }

    public void setAction(JobExecutionAction action) {
        this.action = action;
    }

    public Integer getJobExecutionId() {
        return jobExecutionId;
    }

    public void setJobExecutionId(Integer jobExecutionId) {
        this.jobExecutionId = jobExecutionId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getBatchStatus() {
        return batchStatus;
    }

    public void setBatchStatus(String batchStatus) {
        this.batchStatus = batchStatus;
    }

    @Override
    public String toString() {
        return "JobExecutionTableRowDto{" +
                "jobExecutionId=" + jobExecutionId +
                ", jobName='" + jobName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", batchStatus='" + batchStatus + '\'' +
                ", action=" + action +
                '}';
    }
}
