package org.nailedtothex.jberetweb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import java.io.Serializable;

public class PartitionExecutionPK implements Serializable {
    private Integer partitionexecutionid;
    private Long stepexecutionid;

    @Column(name = "partitionexecutionid")
    @Id
    public Integer getPartitionexecutionid() {
        return partitionexecutionid;
    }

    public void setPartitionexecutionid(Integer partitionexecutionid) {
        this.partitionexecutionid = partitionexecutionid;
    }

    @Column(name = "stepexecutionid")
    @Id
    public Long getStepexecutionid() {
        return stepexecutionid;
    }

    public void setStepexecutionid(Long stepexecutionid) {
        this.stepexecutionid = stepexecutionid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PartitionExecutionPK that = (PartitionExecutionPK) o;

        if (partitionexecutionid != null ? !partitionexecutionid.equals(that.partitionexecutionid) : that.partitionexecutionid != null)
            return false;
        if (stepexecutionid != null ? !stepexecutionid.equals(that.stepexecutionid) : that.stepexecutionid != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = partitionexecutionid != null ? partitionexecutionid.hashCode() : 0;
        result = 31 * result + (stepexecutionid != null ? stepexecutionid.hashCode() : 0);
        return result;
    }
}
