package org.nailedtothex.jberetweb.service;

import org.nailedtothex.jberetweb.model.entity.JobExecution;
import org.nailedtothex.jberetweb.model.entity.JobInstance;
import org.nailedtothex.jberetweb.model.entity.StepExecution;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.util.*;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class JobRepositoryService {
    @PersistenceContext(name = "jberetweb-pu")
    EntityManager em;

    public Long countJobExecution() {
        return em.createNamedQuery("countJobExecution", Long.class).getSingleResult();
    }

    public List<Tuple> findStepListReport(int executionId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<StepExecution> se = cq.from(StepExecution.class);
        cq.multiselect(
                se.get("stepname"),
                se.get("starttime"),
                se.get("endtime"),
                se.get("exitstatus"),
                se.get("readcount"),
                se.get("writecount"),
                se.get("commitcount"),
                se.get("rollbackcount"),
                se.get("readskipcount"),
                se.get("processskipcount"),
                se.get("filtercount"),
                se.get("writeskipcount"));
        cq.where(cb.equal(se.get("jobexecutionid"), executionId));
        cq.orderBy(cb.asc(se.get("starttime")));
        return em.createQuery(cq).getResultList();
    }

    public List<Tuple> findJobListReport(String order, int firstResult, int maxResults) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<JobExecution> je = cq.from(JobExecution.class);
        Root<JobInstance> ji = cq.from(JobInstance.class);
        cq.multiselect(
                je.get("jobexecutionid"),
                ji.get("jobname"),
                je.get("starttime"),
                je.get("endtime"),
                je.get("batchstatus"));
        cq.where(cb.equal(je.get("jobinstanceid"), ji.get("jobinstanceid")));

        Path<Object> jobexecutionid = je.get("jobexecutionid");
        final Order ord;
        switch (order) {
            case "asc":
                ord = cb.asc(jobexecutionid);
                break;
            case "desc":
                ord = cb.desc(jobexecutionid);
                break;
            default:
                throw new IllegalArgumentException(order);
        }

        cq.orderBy(ord);

        return em.createQuery(cq).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public JobExecution findJobExecutionById(Integer jobExecutionId){
        return em.find(JobExecution.class, jobExecutionId);
    }
}
