package org.nailedtothex.jberetweb.entity;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "job_instance")
public class JobInstance {
    private Integer jobinstanceid;
    private Integer version;
    private String jobname;
    private String applicationname;
    private Collection<JobExecution> jobExecutionsByJobinstanceid;

    @Id
    @Column(name = "jobinstanceid")
    public Integer getJobinstanceid() {
        return jobinstanceid;
    }

    public void setJobinstanceid(Integer jobinstanceid) {
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
    @Column(name = "jobname")
    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    @Basic
    @Column(name = "applicationname")
    public String getApplicationname() {
        return applicationname;
    }

    public void setApplicationname(String applicationname) {
        this.applicationname = applicationname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobInstance that = (JobInstance) o;

        if (applicationname != null ? !applicationname.equals(that.applicationname) : that.applicationname != null)
            return false;
        if (jobinstanceid != null ? !jobinstanceid.equals(that.jobinstanceid) : that.jobinstanceid != null)
            return false;
        if (jobname != null ? !jobname.equals(that.jobname) : that.jobname != null) return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = jobinstanceid != null ? jobinstanceid.hashCode() : 0;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (jobname != null ? jobname.hashCode() : 0);
        result = 31 * result + (applicationname != null ? applicationname.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "jobInstanceByJobinstanceid")
    public Collection<JobExecution> getJobExecutionsByJobinstanceid() {
        return jobExecutionsByJobinstanceid;
    }

    public void setJobExecutionsByJobinstanceid(Collection<JobExecution> jobExecutionsByJobinstanceid) {
        this.jobExecutionsByJobinstanceid = jobExecutionsByJobinstanceid;
    }
}
