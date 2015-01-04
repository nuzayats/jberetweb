package org.nailedtothex.jberetweb.logic;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Named
@ApplicationScoped
public class JBeretWebSingleton {

    private boolean jobOperatorLookupRequired;
    private String jobOperatorJndi;

    public JBeretWebSingleton() {
        try (InputStream is = JBeretWebSingleton.class.getResourceAsStream("/jberetweb.properties")) {
            Properties properties = new Properties();
            properties.load(is);

            jobOperatorJndi = properties.getProperty("jobOperator.jndi");
            jobOperatorLookupRequired = jobOperatorJndi != null && !jobOperatorJndi.trim().isEmpty();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T doWorkWithJobOperator(JobOperatorWork<T> w) throws NamingException {
        if (!jobOperatorLookupRequired) {
            return w.doWorkWithJobOperator(BatchRuntime.getJobOperator());
        }

        InitialContext ic = null;
        try {
            ic = new InitialContext();
            JobOperator jobOperator = (JobOperator) ic.lookup(jobOperatorJndi);
            return w.doWorkWithJobOperator(jobOperator);
        } finally {
            if (ic != null) {
                ic.close();
            }
        }
    }


}
