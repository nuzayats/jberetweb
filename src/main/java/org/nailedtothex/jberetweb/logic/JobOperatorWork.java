package org.nailedtothex.jberetweb.logic;

import jakarta.batch.operations.JobOperator;

interface JobOperatorWork<T> {
    T doWorkWithJobOperator(JobOperator jobOperator);
}
