package com.day6ass;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.day6ass.dao.BugDAO;
import com.day6ass.exceptions.EmptyTitleException;
import com.day6ass.exceptions.InvalidSeverityException;
import com.day6ass.models.Bug;
import com.day6ass.models.Task;
import com.day6ass.services.IssueService;
public class App{

    static IssueService service = new IssueService();
    static BugDAO bugDAO = new BugDAO();
    static Scanner sc = new Scanner(System.in);
    static int idCounter = 1;

    public static void main(String[] args) {
        int choice = 0;
        while (choice != 6) {
            printMenu();
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
                continue;
            }
            switch (choice) {
                case 1 -> addBug();
                case 2 -> addTask();
                case 3 -> viewAll();
                case 4 -> updateStatus();
                case 5 -> deleteBug();
                case 6 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid option.");
            }
        }
    }

    static void printMenu() {
        System.out.println("\n===== Bug Tracker Menu =====");
        System.out.println("1. Add Bug");
        System.out.println("2. Add Task");
        System.out.println("3. View All Issues");
        System.out.println("4. Update Bug Status");
        System.out.println("5. Delete Bug");
        System.out.println("6. Exit");
        System.out.print("Choose: ");
    }

    static void addBug() {
        try {
            System.out.print("Title: ");
            String title = sc.nextLine();
            System.out.print("Assigned To: ");
            String assigned = sc.nextLine();
            System.out.print("Severity (Low/Medium/High): ");
            String severity = sc.nextLine();

            Bug bug = new Bug(idCounter++, title, assigned, severity);
            service.addIssue(bug);  
            bugDAO.insertBug(bug);  

        } catch (EmptyTitleException | InvalidSeverityException e) {
            System.out.println("Validation Error: " + e.getMessage());
            logError(e);
        }
    }

    static void addTask() {
        try {
            System.out.print("Title: ");
            String title = sc.nextLine();
            System.out.print("Assigned To: ");
            String assigned = sc.nextLine();
            System.out.print("Estimated Hours: ");
            int hours = Integer.parseInt(sc.nextLine().trim());

            Task task = new Task(idCounter++, title, assigned, hours);
            service.addIssue(task);

        } catch (EmptyTitleException | InvalidSeverityException e) {
            System.out.println("Validation Error: " + e.getMessage());
            logError(e);
        }
    }

    static void viewAll() {
        System.out.println("\n-- In-Memory Issues --");
        service.displayAllIssues();
        System.out.println("\n-- Bugs from Database --");
        bugDAO.getAllBugs();
    }

    static void updateStatus() {
        System.out.print("Bug ID to update: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        System.out.print("New Status (Open/In Progress/Resolved): ");
        String status = sc.nextLine();
        bugDAO.updateStatus(id, status);
    }

    static void deleteBug() {
        System.out.print("Bug ID to delete: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        bugDAO.deleteBug(id);
    }

    static void logError(Exception e) {
        try (FileWriter fw = new FileWriter("error.log", true)) {
            fw.write("[ERROR] " + java.time.LocalDateTime.now() +
                " | " + e.getClass().getSimpleName() +
                " | " + e.getMessage() + "\n");
        } catch (IOException ioEx) {
            System.out.println("Could not write to error.log: " + ioEx.getMessage());
        }
    }
}