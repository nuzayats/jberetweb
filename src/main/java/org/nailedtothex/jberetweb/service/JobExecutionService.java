package org.nailedtothex.jberetweb.service;

import org.nailedtothex.jberetweb.model.entity.JobExecution;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by kyle on 2014/03/29.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@Path("/JobExecutionService/{executionId}")
public class JobExecutionService {

    @EJB
    JobRepositoryService jobRepositoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JobExecution getStepExecutionReport(@PathParam("executionId") Integer executionId) {
        return jobRepositoryService.findJobExecutionById(executionId);
    }
}