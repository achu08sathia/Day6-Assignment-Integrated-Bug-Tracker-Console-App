package com.day6ass.models;

public class Task extends Issue {
    private int estimatedHours;

    public Task(int id, String title, String assignedTo, int estimatedHours) {
        super(id, title, assignedTo);
        this.estimatedHours = estimatedHours;
    }

    public int getEstimatedHours() { return estimatedHours; }

    @Override
    public String getType() { return "Task"; }

    @Override
    public void displayDetails() {
        System.out.println("[TASK] ID: " + getId() +
            " | Title: " + getTitle() +
            " | Assigned: " + getAssignedTo() +
            " | Est. Hours: " + estimatedHours);
    }
}