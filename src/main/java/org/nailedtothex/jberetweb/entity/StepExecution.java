package org.nailedtothex.jberetweb.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;

@Entity
@Table(name = "step_execution")
public class StepExecution {
    private Integer stepexecutionid;
    private Integer version;
    private String stepname;
    private Timestamp starttime;
    private Timestamp endtime;
    private String batchstatus;
    private String exitstatus;
    private String executionexception;
    private byte[] persistentuserdata;
    private Integer readcount;
    private Integer writecount;
    private Integer commitcount;
    private Integer rollbackcount;
    private Integer readskipcount;
    private Integer processskipcount;
    private Integer filtercount;
    private Integer writeskipcount;
    private byte[] readercheckpointinfo;
    private byte[] writercheckpointinfo;
    private Long jobexecutionid;

    @Id
    @Column(name = "stepexecutionid")
    public Integer getStepexecutionid() {
        return stepexecutionid;
    }

    public void setStepexecutionid(Integer stepexecutionid) {
        this.stepexecutionid = stepexecutionid;
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
    @Column(name = "stepname")
    public String getStepname() {
        return stepname;
    }

    public void setStepname(String stepname) {
        this.stepname = stepname;
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
    @Column(name = "executionexception")
    public String getExecutionexception() {
        return executionexception;
    }

    public void setExecutionexception(String executionexception) {
        this.executionexception = executionexception;
    }

    @Basic
    @Column(name = "persistentuserdata")
    public byte[] getPersistentuserdata() {
        return persistentuserdata;
    }

    public void setPersistentuserdata(byte[] persistentuserdata) {
        this.persistentuserdata = persistentuserdata;
    }

    @Basic
    @Column(name = "readcount")
    public Integer getReadcount() {
        return readcount;
    }

    public void setReadcount(Integer readcount) {
        this.readcount = readcount;
    }

    @Basic
    @Column(name = "writecount")
    public Integer getWritecount() {
        return writecount;
    }

    public void setWritecount(Integer writecount) {
        this.writecount = writecount;
    }

    @Basic
    @Column(name = "commitcount")
    public Integer getCommitcount() {
        return commitcount;
    }

    public void setCommitcount(Integer commitcount) {
        this.commitcount = commitcount;
    }

    @Basic
    @Column(name = "rollbackcount")
    public Integer getRollbackcount() {
        return rollbackcount;
    }

    public void setRollbackcount(Integer rollbackcount) {
        this.rollbackcount = rollbackcount;
    }

    @Basic
    @Column(name = "readskipcount")
    public Integer getReadskipcount() {
        return readskipcount;
    }

    public void setReadskipcount(Integer readskipcount) {
        this.readskipcount = readskipcount;
    }

    @Basic
    @Column(name = "processskipcount")
    public Integer getProcessskipcount() {
        return processskipcount;
    }

    public void setProcessskipcount(Integer processskipcount) {
        this.processskipcount = processskipcount;
    }

    @Basic
    @Column(name = "filtercount")
    public Integer getFiltercount() {
        return filtercount;
    }

    public void setFiltercount(Integer filtercount) {
        this.filtercount = filtercount;
    }

    @Basic
    @Column(name = "writeskipcount")
    public Integer getWriteskipcount() {
        return writeskipcount;
    }

    public void setWriteskipcount(Integer writeskipcount) {
        this.writeskipcount = writeskipcount;
    }

    @Basic
    @Column(name = "readercheckpointinfo")
    public byte[] getReadercheckpointinfo() {
        return readercheckpointinfo;
    }

    public void setReadercheckpointinfo(byte[] readercheckpointinfo) {
        this.readercheckpointinfo = readercheckpointinfo;
    }

    @Basic
    @Column(name = "writercheckpointinfo")
    public byte[] getWritercheckpointinfo() {
        return writercheckpointinfo;
    }

    public void setWritercheckpointinfo(byte[] writercheckpointinfo) {
        this.writercheckpointinfo = writercheckpointinfo;
    }

    @Basic
    @Column(name = "jobexecutionid")
    public Long getJobexecutionid() {
        return jobexecutionid;
    }

    public void setJobexecutionid(Long jobexecutionid) {
        this.jobexecutionid = jobexecutionid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StepExecution that = (StepExecution) o;

        if (batchstatus != null ? !batchstatus.equals(that.batchstatus) : that.batchstatus != null) return false;
        if (commitcount != null ? !commitcount.equals(that.commitcount) : that.commitcount != null) return false;
        if (endtime != null ? !endtime.equals(that.endtime) : that.endtime != null) return false;
        if (executionexception != null ? !executionexception.equals(that.executionexception) : that.executionexception != null)
            return false;
        if (exitstatus != null ? !exitstatus.equals(that.exitstatus) : that.exitstatus != null) return false;
        if (filtercount != null ? !filtercount.equals(that.filtercount) : that.filtercount != null) return false;
        if (!jobexecutionid.equals(that.jobexecutionid)) return false;
        if (!Arrays.equals(persistentuserdata, that.persistentuserdata)) return false;
        if (processskipcount != null ? !processskipcount.equals(that.processskipcount) : that.processskipcount != null)
            return false;
        if (readcount != null ? !readcount.equals(that.readcount) : that.readcount != null) return false;
        if (!Arrays.equals(readercheckpointinfo, that.readercheckpointinfo)) return false;
        if (readskipcount != null ? !readskipcount.equals(that.readskipcount) : that.readskipcount != null)
            return false;
        if (rollbackcount != null ? !rollbackcount.equals(that.rollbackcount) : that.rollbackcount != null)
            return false;
        if (starttime != null ? !starttime.equals(that.starttime) : that.starttime != null) return false;
        if (!stepexecutionid.equals(that.stepexecutionid)) return false;
        if (stepname != null ? !stepname.equals(that.stepname) : that.stepname != null) return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;
        if (writecount != null ? !writecount.equals(that.writecount) : that.writecount != null) return false;
        if (!Arrays.equals(writercheckpointinfo, that.writercheckpointinfo)) return false;
        if (writeskipcount != null ? !writeskipcount.equals(that.writeskipcount) : that.writeskipcount != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stepexecutionid.hashCode();
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (stepname != null ? stepname.hashCode() : 0);
        result = 31 * result + (starttime != null ? starttime.hashCode() : 0);
        result = 31 * result + (endtime != null ? endtime.hashCode() : 0);
        result = 31 * result + (batchstatus != null ? batchstatus.hashCode() : 0);
        result = 31 * result + (exitstatus != null ? exitstatus.hashCode() : 0);
        result = 31 * result + (executionexception != null ? executionexception.hashCode() : 0);
        result = 31 * result + (persistentuserdata != null ? Arrays.hashCode(persistentuserdata) : 0);
        result = 31 * result + (readcount != null ? readcount.hashCode() : 0);
        result = 31 * result + (writecount != null ? writecount.hashCode() : 0);
        result = 31 * result + (commitcount != null ? commitcount.hashCode() : 0);
        result = 31 * result + (rollbackcount != null ? rollbackcount.hashCode() : 0);
        result = 31 * result + (readskipcount != null ? readskipcount.hashCode() : 0);
        result = 31 * result + (processskipcount != null ? processskipcount.hashCode() : 0);
        result = 31 * result + (filtercount != null ? filtercount.hashCode() : 0);
        result = 31 * result + (writeskipcount != null ? writeskipcount.hashCode() : 0);
        result = 31 * result + (readercheckpointinfo != null ? Arrays.hashCode(readercheckpointinfo) : 0);
        result = 31 * result + (writercheckpointinfo != null ? Arrays.hashCode(writercheckpointinfo) : 0);
        result = 31 * result + jobexecutionid.hashCode();
        return result;
    }
}
