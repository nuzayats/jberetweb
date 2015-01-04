package org.nailedtothex.jberetweb.dto;

import java.io.Serializable;

public class JobParameterDto implements Serializable, Comparable<JobParameterDto> {

    String name;
    String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "JobParameterDto{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobParameterDto that = (JobParameterDto) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(JobParameterDto o) {
        return name.compareTo(o.name);
    }

    public JobParameterDto() {
    }

    public JobParameterDto(String name, String value) {
        this.name = name;
        this.value = value;
    }
}

