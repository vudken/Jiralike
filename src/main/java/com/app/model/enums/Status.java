package com.app.model.enums;

public enum Status {
    OPEN("Open"),
    IN_PROGRESS("In Progress"),
    TO_DO("To Do"),
    DONE("Done");

    private final String displayValue;

    private Status(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}