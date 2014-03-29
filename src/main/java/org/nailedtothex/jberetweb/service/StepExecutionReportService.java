package org.nailedtothex.jberetweb.service;

import org.nailedtothex.jberetweb.model.bean.DataTablesBean;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Tuple;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by kyle on 2014/03/29.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@Path("/StepExecutionReportService/{executionId}")
public class StepExecutionReportService {
    @EJB
    JobRepositoryService repositoryService;
    @EJB
    DataTablesService dataTablesService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public DataTablesBean getStepExecutionReport(@QueryParam("sEcho") Integer sEcho, @PathParam("executionId") Integer executionId) {

        List<Tuple> tuples = repositoryService.findStepListReport(executionId);
        List<List<String>> aaData = dataTablesService.createAaDataWithTime(tuples);
        int count = tuples.size();

        DataTablesBean dataTablesBean = new DataTablesBean();
        dataTablesBean.setsEcho(sEcho);
        dataTablesBean.setAaData(aaData);
        dataTablesBean.setiTotalRecords(String.valueOf(count));
        dataTablesBean.setiTotalDisplayRecords(String.valueOf(count));

        return dataTablesBean;
    }
}