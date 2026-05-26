package com.day6ass.models;

public class Bug extends Issue {
    private String severity; 

    public Bug(int id, String title, String assignedTo, String severity) {
        super(id, title, assignedTo);
        this.severity = severity;
    }

    public String getSeverity() { return severity; }

    @Override
    public String getType() { return "Bug"; }

    @Override
    public void displayDetails() {
        System.out.println("[BUG] ID: " + getId() +
            " | Title: " + getTitle() +
            " | Assigned: " + getAssignedTo() +
            " | Severity: " + severity);
    }
}