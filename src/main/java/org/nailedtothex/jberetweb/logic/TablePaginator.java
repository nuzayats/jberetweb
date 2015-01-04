package org.nailedtothex.jberetweb.logic;

import java.io.Serializable;

public class TablePaginator implements Serializable {

    private final long totalRows;
    private final int rowsInSinglePage;
    private int currentPage = 1;

    public TablePaginator(long totalRows, int rowsInSinglePage) {
        this.totalRows = totalRows;
        this.rowsInSinglePage = rowsInSinglePage;
    }

    /**
     * @return true if next page available.
     */
    public boolean isNextPageAvailable() {
        return getTotalPages() != 0 && getTotalPages() > currentPage;
    }

    /**
     * @return true if previous page available.
     */
    public boolean isPreviousPageAvailable() {
        return getTotalPages() != 0 && currentPage > 1;
    }

    /**
     * @return the number of total pages.
     */
    public int getTotalPages() {
        double d = (double) totalRows / (double) rowsInSinglePage;
        return (int) Math.ceil(d);
    }

    /**
     * @return the first row number of the current page.
     */
    public long getCurrentRangeBegin() {
        if (totalRows == 0) {
            return 0;
        }
        return rowsInSinglePage * (currentPage - 1) + 1;
    }

    /**
     * @return the last row number of the current page.
     */
    public long getCurrentRangeEnd() {
        long end = getCurrentRangeBegin() + rowsInSinglePage - 1;
        if (end > totalRows) {
            return totalRows;
        }
        return end;
    }

    /**
     * @throws java.lang.IllegalStateException if next page not available.
     */
    public void next() {
        currentPage++;
    }

    /**
     * @throws java.lang.IllegalStateException if previous page not available.
     */
    public void previous() {
        currentPage--;
    }

    public void last() {
        currentPage = getTotalPages();
    }

    public void first() {
        currentPage = 1;
    }
}
