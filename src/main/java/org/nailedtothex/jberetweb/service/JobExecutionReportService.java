package org.nailedtothex.jberetweb.service;

import org.nailedtothex.jberetweb.model.bean.DataTablesBean;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Tuple;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyle on 2014/03/29.
 */
@Stateless
@Path("/JobExecutionReportService")
public class JobExecutionReportService {
    @EJB
    JobRepositoryService repositoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public DataTablesBean getJobExecutionReport(
            @QueryParam("sEcho") Integer sEcho,
            @QueryParam("iDisplayLength") Integer iDisplayLength,
            @QueryParam("iDisplayStart") Integer iDisplayStart,
            @QueryParam("sSortDir_0") String sSortDir_0) {

        List<Tuple> tuples = repositoryService.findJobListReport(sSortDir_0, iDisplayStart, iDisplayLength);
        List<List<String>> aaData = createAaData(tuples);
        Long count = repositoryService.countJobExecution();

        DataTablesBean dataTablesBean = new DataTablesBean();
        dataTablesBean.setsEcho(sEcho);
        dataTablesBean.setAaData(aaData);
        dataTablesBean.setiTotalRecords(String.valueOf(count));
        dataTablesBean.setiTotalDisplayRecords(String.valueOf(count));

        return dataTablesBean;
    }

    protected List<List<String>> createAaData(List<Tuple> tuples) {
        List<List<String>> list = new ArrayList<>(tuples.size());
        for (Tuple t : tuples) {
            List<String> subList = new ArrayList<>(t.getElements().size());
            for (int i = 0; i < t.getElements().size(); i++) {
                subList.add(String.valueOf(t.get(i)));
            }
            list.add(subList);
        }
        return list;
    }
}