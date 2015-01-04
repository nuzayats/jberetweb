package org.nailedtothex.jberetweb.logic;

import javax.batch.operations.JobOperator;

interface JobOperatorWork<T> {
    T doWorkWithJobOperator(JobOperator jobOperator);
}
