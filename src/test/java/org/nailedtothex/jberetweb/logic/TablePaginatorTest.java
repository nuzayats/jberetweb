package org.nailedtothex.jberetweb.logic;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TablePaginatorTest {

    @Test
    public void row100() {
        TablePaginator instance = new TablePaginator(100, 10);
        assertThat(instance.getCurrentRangeBegin(), is(1l));
        assertThat(instance.getCurrentRangeEnd(), is(10l));
        assertThat(instance.getTotalPages(), is(10));
        assertThat(instance.isNextPageAvailable(), is(true));
        assertThat(instance.isPreviousPageAvailable(), is(false));
    }

    @Test
    public void row100next() {
        TablePaginator instance = new TablePaginator(100, 10);
        instance.next();

        assertThat(instance.getCurrentRangeBegin(), is(11l));
        assertThat(instance.getCurrentRangeEnd(), is(20l));
        assertThat(instance.getTotalPages(), is(10));
        assertThat(instance.isNextPageAvailable(), is(true));
        assertThat(instance.isPreviousPageAvailable(), is(true));
    }

    @Test
    public void row100end() {
        TablePaginator instance = new TablePaginator(100, 10);
        for (int i = 0; i < 9; i++) instance.next();
        assertThat(instance.getCurrentRangeBegin(), is(91l));
        assertThat(instance.getCurrentRangeEnd(), is(100l));
        assertThat(instance.getTotalPages(), is(10));
        assertThat(instance.isNextPageAvailable(), is(false));
        assertThat(instance.isPreviousPageAvailable(), is(true));
    }

    @Test
    public void nodata() {
        TablePaginator instance = new TablePaginator(0, 10);
        assertThat(instance.getCurrentRangeBegin(), is(0l));
        assertThat(instance.getCurrentRangeEnd(), is(0l));
        assertThat(instance.getTotalPages(), is(0));
        assertThat(instance.isNextPageAvailable(), is(false));
        assertThat(instance.isPreviousPageAvailable(), is(false));
    }

    @Test
    public void noPagination() {
        TablePaginator instance = new TablePaginator(3, 10);
        assertThat(instance.getCurrentRangeBegin(), is(1l));
        assertThat(instance.getCurrentRangeEnd(), is(3l));
        assertThat(instance.getTotalPages(), is(1));
        assertThat(instance.isNextPageAvailable(), is(false));
        assertThat(instance.isPreviousPageAvailable(), is(false));
    }

    @Test
    public void last() {
        TablePaginator instance = new TablePaginator(100, 10);
        instance.last();
        assertThat(instance.getCurrentRangeBegin(), is(91l));
        assertThat(instance.getCurrentRangeEnd(), is(100l));
        assertThat(instance.getTotalPages(), is(10));
        assertThat(instance.isNextPageAvailable(), is(false));
        assertThat(instance.isPreviousPageAvailable(), is(true));
    }

    @Test
    public void lastAndFirst() {
        TablePaginator instance = new TablePaginator(10, 3);

        instance.last();
        assertThat(instance.getCurrentRangeBegin(), is(10l));
        assertThat(instance.getCurrentRangeEnd(), is(10l));
        assertThat(instance.getTotalPages(), is(4));
        assertThat(instance.isNextPageAvailable(), is(false));
        assertThat(instance.isPreviousPageAvailable(), is(true));

        instance.first();
        assertThat(instance.getCurrentRangeBegin(), is(1l));
        assertThat(instance.getCurrentRangeEnd(), is(3l));
        assertThat(instance.getTotalPages(), is(4));
        assertThat(instance.isNextPageAvailable(), is(true));
        assertThat(instance.isPreviousPageAvailable(), is(false));
    }

    @Test
    public void walkThrough() {
        TablePaginator instance = new TablePaginator(10, 3);

        assertThat(instance.getCurrentRangeBegin(), is(1l));
        assertThat(instance.getCurrentRangeEnd(), is(3l));
        assertThat(instance.getTotalPages(), is(4));
        assertThat(instance.isNextPageAvailable(), is(true));
        assertThat(instance.isPreviousPageAvailable(), is(false));

        instance.next();
        assertThat(instance.getCurrentRangeBegin(), is(4l));
        assertThat(instance.getCurrentRangeEnd(), is(6l));
        assertThat(instance.getTotalPages(), is(4));
        assertThat(instance.isNextPageAvailable(), is(true));
        assertThat(instance.isPreviousPageAvailable(), is(true));

        instance.next();
        assertThat(instance.getCurrentRangeBegin(), is(7l));
        assertThat(instance.getCurrentRangeEnd(), is(9l));
        assertThat(instance.getTotalPages(), is(4));
        assertThat(instance.isNextPageAvailable(), is(true));
        assertThat(instance.isPreviousPageAvailable(), is(true));

        instance.next();
        assertThat(instance.getCurrentRangeBegin(), is(10l));
        assertThat(instance.getCurrentRangeEnd(), is(10l));
        assertThat(instance.getTotalPages(), is(4));
        assertThat(instance.isNextPageAvailable(), is(false));
        assertThat(instance.isPreviousPageAvailable(), is(true));

        instance.previous();
        assertThat(instance.getCurrentRangeBegin(), is(7l));
        assertThat(instance.getCurrentRangeEnd(), is(9l));
        assertThat(instance.getTotalPages(), is(4));
        assertThat(instance.isNextPageAvailable(), is(true));
        assertThat(instance.isPreviousPageAvailable(), is(true));

        instance.previous();
        assertThat(instance.getCurrentRangeBegin(), is(4l));
        assertThat(instance.getCurrentRangeEnd(), is(6l));
        assertThat(instance.getTotalPages(), is(4));
        assertThat(instance.isNextPageAvailable(), is(true));
        assertThat(instance.isPreviousPageAvailable(), is(true));

        instance.previous();
        assertThat(instance.getCurrentRangeBegin(), is(1l));
        assertThat(instance.getCurrentRangeEnd(), is(3l));
        assertThat(instance.getTotalPages(), is(4));
        assertThat(instance.isNextPageAvailable(), is(true));
        assertThat(instance.isPreviousPageAvailable(), is(false));
    }
}