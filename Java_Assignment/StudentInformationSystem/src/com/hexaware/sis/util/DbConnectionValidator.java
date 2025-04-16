package com.hexaware.sis.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import com.hexaware.sis.exception.DatabaseConnectionException;

import com.hexaware.sis.util.DBPropertyUtil;
import com.hexaware.sis.exception.DatabaseConnectionException;

public class DbConnectionValidator {

    public static Connection getConnection() throws DatabaseConnectionException {
        String url = DBPropertyUtil.getConnectionString();
        String username = DBPropertyUtil.getUsername();
        String password = DBPropertyUtil.getPassword();

        if (url == null || username == null || password == null) {
            throw new DatabaseConnectionException("Database connection string could not be retrieved.");
        }

        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to connect to database: " + e.getMessage());
        }
    }
}
