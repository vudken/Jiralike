package com.app.model.enums;

public enum Type {
    EPIC("Epic"),
    STORY("Story"),
    TASK("Task"),
    SUBTASK("Subtask"),
    BUG("Bug");

    private final String displayValue;

    private Type(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
