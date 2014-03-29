package org.nailedtothex.jberetweb.model.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.sql.Timestamp;

/**
 * Created by kyle on 2014/03/28.
 */
@Entity
@Table(name = "job_execution", schema = "public", catalog = "jbatch")
public class JobExecution {
    private Integer jobexecutionid;
    private Long jobinstanceid;
    private Integer version;
    private Timestamp createtime;
    private Timestamp starttime;
    private Timestamp endtime;
    private Timestamp lastupdatedtime;
    private String batchstatus;
    private String exitstatus;
    private String jobparameters;
    private String restartposition;
    private JobInstance jobInstanceByJobinstanceid;

    @Id
    @Column(name = "jobexecutionid")
    public Integer getJobexecutionid() {
        return jobexecutionid;
    }

    public void setJobexecutionid(Integer jobexecutionid) {
        this.jobexecutionid = jobexecutionid;
    }

    @Basic
    @Column(name = "jobinstanceid", updatable = false, insertable = false)
    public Long getJobinstanceid() {
        return jobinstanceid;
    }

    public void setJobinstanceid(Long jobinstanceid) {
        this.jobinstanceid = jobinstanceid;
    }

    @Basic
    @Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Basic
    @Column(name = "createtime")
    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    @Basic
    @Column(name = "starttime")
    public Timestamp getStarttime() {
        return starttime;
    }

    public void setStarttime(Timestamp starttime) {
        this.starttime = starttime;
    }

    @Basic
    @Column(name = "endtime")
    public Timestamp getEndtime() {
        return endtime;
    }

    public void setEndtime(Timestamp endtime) {
        this.endtime = endtime;
    }

    @Basic
    @Column(name = "lastupdatedtime")
    public Timestamp getLastupdatedtime() {
        return lastupdatedtime;
    }

    public void setLastupdatedtime(Timestamp lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    @Basic
    @Column(name = "batchstatus")
    public String getBatchstatus() {
        return batchstatus;
    }

    public void setBatchstatus(String batchstatus) {
        this.batchstatus = batchstatus;
    }

    @Basic
    @Column(name = "exitstatus")
    public String getExitstatus() {
        return exitstatus;
    }

    public void setExitstatus(String exitstatus) {
        this.exitstatus = exitstatus;
    }

    @Basic
    @Column(name = "jobparameters")
    public String getJobparameters() {
        return jobparameters;
    }

    public void setJobparameters(String jobparameters) {
        this.jobparameters = jobparameters;
    }

    @Basic
    @Column(name = "restartposition")
    public String getRestartposition() {
        return restartposition;
    }

    public void setRestartposition(String restartposition) {
        this.restartposition = restartposition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobExecution that = (JobExecution) o;

        if (batchstatus != null ? !batchstatus.equals(that.batchstatus) : that.batchstatus != null) return false;
        if (createtime != null ? !createtime.equals(that.createtime) : that.createtime != null) return false;
        if (endtime != null ? !endtime.equals(that.endtime) : that.endtime != null) return false;
        if (exitstatus != null ? !exitstatus.equals(that.exitstatus) : that.exitstatus != null) return false;
        if (jobexecutionid != null ? !jobexecutionid.equals(that.jobexecutionid) : that.jobexecutionid != null)
            return false;
        if (jobinstanceid != null ? !jobinstanceid.equals(that.jobinstanceid) : that.jobinstanceid != null)
            return false;
        if (jobparameters != null ? !jobparameters.equals(that.jobparameters) : that.jobparameters != null)
            return false;
        if (lastupdatedtime != null ? !lastupdatedtime.equals(that.lastupdatedtime) : that.lastupdatedtime != null)
            return false;
        if (restartposition != null ? !restartposition.equals(that.restartposition) : that.restartposition != null)
            return false;
        if (starttime != null ? !starttime.equals(that.starttime) : that.starttime != null) return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = jobexecutionid != null ? jobexecutionid.hashCode() : 0;
        result = 31 * result + (jobinstanceid != null ? jobinstanceid.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
        result = 31 * result + (starttime != null ? starttime.hashCode() : 0);
        result = 31 * result + (endtime != null ? endtime.hashCode() : 0);
        result = 31 * result + (lastupdatedtime != null ? lastupdatedtime.hashCode() : 0);
        result = 31 * result + (batchstatus != null ? batchstatus.hashCode() : 0);
        result = 31 * result + (exitstatus != null ? exitstatus.hashCode() : 0);
        result = 31 * result + (jobparameters != null ? jobparameters.hashCode() : 0);
        result = 31 * result + (restartposition != null ? restartposition.hashCode() : 0);
        return result;
    }

    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "jobinstanceid", referencedColumnName = "jobinstanceid", nullable = false)
    public JobInstance getJobInstanceByJobinstanceid() {
        return jobInstanceByJobinstanceid;
    }

    public void setJobInstanceByJobinstanceid(JobInstance jobInstanceByJobinstanceid) {
        this.jobInstanceByJobinstanceid = jobInstanceByJobinstanceid;
    }
}
