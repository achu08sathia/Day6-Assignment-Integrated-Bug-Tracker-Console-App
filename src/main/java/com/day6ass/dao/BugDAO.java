package com.day6ass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.day6ass.models.Bug;
import com.day6ass.util.DBUtil;


public class BugDAO {

    public void insertBug(Bug bug) {
        String sql = "INSERT INTO bugs (title, severity, assigned_to, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, bug.getTitle());
            ps.setString(2, bug.getSeverity());
            ps.setString(3, bug.getAssignedTo());
            ps.setString(4, "Open");
            ps.executeUpdate();
            System.out.println("Bug saved to database.");
        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }

    public List<Bug> getAllBugs() {
        List<Bug> bugs = new ArrayList<>();
        String sql = "SELECT * FROM bugs";
        try (Connection conn = DBUtil.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Bug b = new Bug(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("assigned_to"),
                    rs.getString("severity")
                );
                bugs.add(b);
                System.out.println("[DB] ID: " + b.getId() +
                    " | " + b.getTitle() +
                    " | " + b.getSeverity() +
                    " | Status: " + rs.getString("status"));
            }
        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
        return bugs;
    }

    public void updateStatus(int id, String newStatus) {
        String sql = "UPDATE bugs SET status = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Status updated." : "Bug ID not found.");
        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }

    public void deleteBug(int id) {
        String sql = "DELETE FROM bugs WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Bug deleted." : "Bug ID not found.");
        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }
}