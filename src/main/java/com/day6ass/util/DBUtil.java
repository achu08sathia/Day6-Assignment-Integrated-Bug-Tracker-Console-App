package com.day6ass.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL      = "jdbc:postgresql://localhost:5432/bugtracker";
    private static final String USER     = "postgres";   
    private static final String PASSWORD = "Akshaya2005"; 

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}