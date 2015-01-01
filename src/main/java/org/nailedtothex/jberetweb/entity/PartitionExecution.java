package org.nailedtothex.jberetweb.entity;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "partition_execution")
@IdClass(PartitionExecutionPK.class)
public class PartitionExecution {
    private Integer partitionexecutionid;
    private Long stepexecutionid;
    private Integer version;
    private String batchstatus;
    private String exitstatus;
    private String executionexception;
    private byte[] persistentuserdata;
    private byte[] readercheckpointinfo;
    private byte[] writercheckpointinfo;

    @Id
    @Column(name = "partitionexecutionid")
    public Integer getPartitionexecutionid() {
        return partitionexecutionid;
    }

    public void setPartitionexecutionid(Integer partitionexecutionid) {
        this.partitionexecutionid = partitionexecutionid;
    }

    @Id
    @Column(name = "stepexecutionid")
    public Long getStepexecutionid() {
        return stepexecutionid;
    }

    public void setStepexecutionid(Long stepexecutionid) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PartitionExecution that = (PartitionExecution) o;

        if (batchstatus != null ? !batchstatus.equals(that.batchstatus) : that.batchstatus != null) return false;
        if (executionexception != null ? !executionexception.equals(that.executionexception) : that.executionexception != null)
            return false;
        if (exitstatus != null ? !exitstatus.equals(that.exitstatus) : that.exitstatus != null) return false;
        if (partitionexecutionid != null ? !partitionexecutionid.equals(that.partitionexecutionid) : that.partitionexecutionid != null)
            return false;
        if (!Arrays.equals(persistentuserdata, that.persistentuserdata)) return false;
        if (!Arrays.equals(readercheckpointinfo, that.readercheckpointinfo)) return false;
        if (stepexecutionid != null ? !stepexecutionid.equals(that.stepexecutionid) : that.stepexecutionid != null)
            return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;
        if (!Arrays.equals(writercheckpointinfo, that.writercheckpointinfo)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = partitionexecutionid != null ? partitionexecutionid.hashCode() : 0;
        result = 31 * result + (stepexecutionid != null ? stepexecutionid.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (batchstatus != null ? batchstatus.hashCode() : 0);
        result = 31 * result + (exitstatus != null ? exitstatus.hashCode() : 0);
        result = 31 * result + (executionexception != null ? executionexception.hashCode() : 0);
        result = 31 * result + (persistentuserdata != null ? Arrays.hashCode(persistentuserdata) : 0);
        result = 31 * result + (readercheckpointinfo != null ? Arrays.hashCode(readercheckpointinfo) : 0);
        result = 31 * result + (writercheckpointinfo != null ? Arrays.hashCode(writercheckpointinfo) : 0);
        return result;
    }
}
