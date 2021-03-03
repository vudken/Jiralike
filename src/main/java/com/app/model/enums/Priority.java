package com.app.model.enums;

public enum Priority {
    CRITICAL ("Critical"),
    MAJOR ("Major"),
    MINOR ("Minor"),
    TRIVIAL ("Trivial");

    private final String displayValue;

    private Priority(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
