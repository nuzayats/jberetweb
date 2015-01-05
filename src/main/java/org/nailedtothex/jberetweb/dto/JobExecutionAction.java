package org.nailedtothex.jberetweb.dto;

public enum JobExecutionAction {
    SELECT(""),
    RE_EXECUTE("Re-execute as new instance"),
    RESTART("Restart"),
    STOP("Stop"),
    ABANDON("Abandon");

    private String label;

    JobExecutionAction(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
