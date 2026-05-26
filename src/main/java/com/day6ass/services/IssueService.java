package com.day6ass.services;

import java.util.ArrayList;
import java.util.List;

import com.day6ass.exceptions.EmptyTitleException;
import com.day6ass.exceptions.InvalidSeverityException;
import com.day6ass.models.Bug;
import com.day6ass.models.Issue;

public class IssueService {
    private List<Issue> issues = new ArrayList<>();

    public void addIssue(Issue issue)
            throws EmptyTitleException, InvalidSeverityException {

        if (issue.getTitle() == null || issue.getTitle().isBlank()) {
            throw new EmptyTitleException("Title cannot be empty.");
        }

        if (issue instanceof Bug) {
            String sev = ((Bug) issue).getSeverity();
            if (!sev.equals("Low") && !sev.equals("Medium") && !sev.equals("High")) {
                throw new InvalidSeverityException(
                    "Severity must be Low, Medium, or High. Got: " + sev);
            }
        }

        issues.add(issue);
        System.out.println("Issue added: " + issue.getTitle());
    }

    public void displayAllIssues() {
        if (issues.isEmpty()) {
            System.out.println("No issues found.");
            return;
        }
        issues.forEach(Issue::displayDetails);
    }

    public List<Issue> getIssuesByType(String type) {
        List<Issue> result = new ArrayList<>();
        for (Issue i : issues) {
            if (i.getType().equalsIgnoreCase(type)) result.add(i);
        }
        return result;
    }
}