package com.hexaware.sis.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnUtil {

    // Load the JDBC driver for MySQL (or the appropriate database you're using)
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Change to the appropriate driver for your DB
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = DBPropertyUtil.getConnectionString();
        String username = DBPropertyUtil.getUsername();
        String password = DBPropertyUtil.getPassword();
        
        if (url == null || username == null || password == null) {
            throw new SQLException("Unable to retrieve database connection details.");
        }
        
        return DriverManager.getConnection(url, username, password);
    }
}
