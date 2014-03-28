package org.nailedtothex.jberetweb.model.bean;

import java.util.List;

/**
 * Created by kyle on 2014/03/29.
 */
public class DataTablesBean {
    private Integer sEcho;
    private String iTotalRecords;
    private String iTotalDisplayRecords;
    private List<List<String>> aaData;

    public String getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(String iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public String getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(String iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public List<List<String>> getAaData() {
        return aaData;
    }

    public void setAaData(List<List<String>> aaData) {
        this.aaData = aaData;
    }

    public Integer getsEcho() {
        return sEcho;
    }

    public void setsEcho(Integer sEcho) {
        this.sEcho = sEcho;
    }
}
