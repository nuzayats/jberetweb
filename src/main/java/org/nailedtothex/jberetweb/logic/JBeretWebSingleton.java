package org.nailedtothex.jberetweb.logic;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named("singleton")
@ApplicationScoped
public class JBeretWebSingleton {

    public enum OperationMode {
        DIRECT,
        REMOTE_FIXED,
        REMOTE_MULTIPLE
    }
    
    private static final Logger log = Logger.getLogger(JBeretWebSingleton.class.getName());
    private final String jobOperatorJndi;
    private final String jobOperatorName;
    private final String jobOperatorFormat;
    private final OperationMode operationMode;
    @PersistenceContext
    private EntityManager em;

    public JBeretWebSingleton() {
        try (InputStream is = JBeretWebSingleton.class.getResourceAsStream("/jberetweb.properties")) {
            Properties properties = new Properties();
            properties.load(is);

            jobOperatorJndi = properties.getProperty("jobOperator.jndi");
            jobOperatorName = properties.getProperty("jobOperator.name");
            jobOperatorFormat = properties.getProperty("jobOperator.format");

            operationMode = decideOperationMode(jobOperatorJndi, jobOperatorName, jobOperatorFormat);
            log.log(Level.INFO, "OperationMode={0}, jndi={1}, name={2}, format={3}",
                    new Object[]{operationMode, jobOperatorJndi, jobOperatorName, jobOperatorFormat});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T doWorkWithJobOperator(JobOperatorWork<T> w, String applicationName) throws NamingException {
        if (operationMode == OperationMode.DIRECT) {
            return w.doWorkWithJobOperator(BatchRuntime.getJobOperator());
        }

        final String lookupName = getLookupName(applicationName);
        log.log(Level.FINE, "lookupName={0}, applicationName={1}", new Object[]{lookupName, applicationName});
        InitialContext ic = null;
        try {
            ic = new InitialContext();
            JobOperator jobOperator = (JobOperator) ic.lookup(lookupName);
            return w.doWorkWithJobOperator(jobOperator);
        } finally {
            if (ic != null) {
                ic.close();
            }
        }
    }

    String getLookupName(String applicationName) {
        switch (operationMode) {
            case REMOTE_MULTIPLE:
                return new MessageFormat(jobOperatorFormat).format(new String[]{applicationName, jobOperatorName});
            case REMOTE_FIXED:
                return jobOperatorJndi;
            default:
                throw new RuntimeException();
        }
    }

    public OperationMode getOperationMode() {
        return operationMode;
    }

    static OperationMode decideOperationMode(String jndi, String name, String format) {
        if (isSupplied(jndi)) {
            return OperationMode.REMOTE_FIXED;
        }
        if (isSupplied(name) && isSupplied(format)) {
            return OperationMode.REMOTE_MULTIPLE;
        }
        return OperationMode.DIRECT;
    }

    static boolean isSupplied(String param) {
        return param != null && !param.trim().isEmpty();
    }

    String getApplicationNameByJobExecutionId(long jobExecutionId) {
        synchronized (em) {
            return em.createNamedQuery("findApplicationNameByJobExecutionId", String.class)
                    .setParameter("jobExecutionId", (int) jobExecutionId)
                    .getSingleResult();
        }
    }
}
