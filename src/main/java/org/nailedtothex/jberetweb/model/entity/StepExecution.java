package org.nailedtothex.jberetweb.model.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Arrays;

/**
 * Created by kyle on 2014/03/28.
 */
@Entity
@javax.persistence.Table(name = "step_execution", schema = "public", catalog = "jbatch")
public class StepExecution {
    private Integer stepexecutionid;

    @Id
    @javax.persistence.Column(name = "stepexecutionid")
    public Integer getStepexecutionid() {
        return stepexecutionid;
    }

    public void setStepexecutionid(Integer stepexecutionid) {
        this.stepexecutionid = stepexecutionid;
    }

    private Integer version;

    @Basic
    @javax.persistence.Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    private String stepname;

    @Basic
    @javax.persistence.Column(name = "stepname")
    public String getStepname() {
        return stepname;
    }

    public void setStepname(String stepname) {
        this.stepname = stepname;
    }

    private Timestamp starttime;

    @Basic
    @javax.persistence.Column(name = "starttime")
    public Timestamp getStarttime() {
        return starttime;
    }

    public void setStarttime(Timestamp starttime) {
        this.starttime = starttime;
    }

    private Timestamp endtime;

    @Basic
    @javax.persistence.Column(name = "endtime")
    public Timestamp getEndtime() {
        return endtime;
    }

    public void setEndtime(Timestamp endtime) {
        this.endtime = endtime;
    }

    private String batchstatus;

    @Basic
    @javax.persistence.Column(name = "batchstatus")
    public String getBatchstatus() {
        return batchstatus;
    }

    public void setBatchstatus(String batchstatus) {
        this.batchstatus = batchstatus;
    }

    private String exitstatus;

    @Basic
    @javax.persistence.Column(name = "exitstatus")
    public String getExitstatus() {
        return exitstatus;
    }

    public void setExitstatus(String exitstatus) {
        this.exitstatus = exitstatus;
    }

    private String executionexception;

    @Basic
    @javax.persistence.Column(name = "executionexception")
    public String getExecutionexception() {
        return executionexception;
    }

    public void setExecutionexception(String executionexception) {
        this.executionexception = executionexception;
    }

    private byte[] persistentuserdata;

    @Basic
    @javax.persistence.Column(name = "persistentuserdata")
    public byte[] getPersistentuserdata() {
        return persistentuserdata;
    }

    public void setPersistentuserdata(byte[] persistentuserdata) {
        this.persistentuserdata = persistentuserdata;
    }

    private Integer readcount;

    @Basic
    @javax.persistence.Column(name = "readcount")
    public Integer getReadcount() {
        return readcount;
    }

    public void setReadcount(Integer readcount) {
        this.readcount = readcount;
    }

    private Integer writecount;

    @Basic
    @javax.persistence.Column(name = "writecount")
    public Integer getWritecount() {
        return writecount;
    }

    public void setWritecount(Integer writecount) {
        this.writecount = writecount;
    }

    private Integer commitcount;

    @Basic
    @javax.persistence.Column(name = "commitcount")
    public Integer getCommitcount() {
        return commitcount;
    }

    public void setCommitcount(Integer commitcount) {
        this.commitcount = commitcount;
    }

    private Integer rollbackcount;

    @Basic
    @javax.persistence.Column(name = "rollbackcount")
    public Integer getRollbackcount() {
        return rollbackcount;
    }

    public void setRollbackcount(Integer rollbackcount) {
        this.rollbackcount = rollbackcount;
    }

    private Integer readskipcount;

    @Basic
    @javax.persistence.Column(name = "readskipcount")
    public Integer getReadskipcount() {
        return readskipcount;
    }

    public void setReadskipcount(Integer readskipcount) {
        this.readskipcount = readskipcount;
    }

    private Integer processskipcount;

    @Basic
    @javax.persistence.Column(name = "processskipcount")
    public Integer getProcessskipcount() {
        return processskipcount;
    }

    public void setProcessskipcount(Integer processskipcount) {
        this.processskipcount = processskipcount;
    }

    private Integer filtercount;

    @Basic
    @javax.persistence.Column(name = "filtercount")
    public Integer getFiltercount() {
        return filtercount;
    }

    public void setFiltercount(Integer filtercount) {
        this.filtercount = filtercount;
    }

    private Integer writeskipcount;

    @Basic
    @javax.persistence.Column(name = "writeskipcount")
    public Integer getWriteskipcount() {
        return writeskipcount;
    }

    public void setWriteskipcount(Integer writeskipcount) {
        this.writeskipcount = writeskipcount;
    }

    private byte[] readercheckpointinfo;

    @Basic
    @javax.persistence.Column(name = "readercheckpointinfo")
    public byte[] getReadercheckpointinfo() {
        return readercheckpointinfo;
    }

    public void setReadercheckpointinfo(byte[] readercheckpointinfo) {
        this.readercheckpointinfo = readercheckpointinfo;
    }

    private byte[] writercheckpointinfo;

    @Basic
    @javax.persistence.Column(name = "writercheckpointinfo")
    public byte[] getWritercheckpointinfo() {
        return writercheckpointinfo;
    }

    public void setWritercheckpointinfo(byte[] writercheckpointinfo) {
        this.writercheckpointinfo = writercheckpointinfo;
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
        if (stepexecutionid != null ? !stepexecutionid.equals(that.stepexecutionid) : that.stepexecutionid != null)
            return false;
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
        int result = stepexecutionid != null ? stepexecutionid.hashCode() : 0;
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
        return result;
    }
}
