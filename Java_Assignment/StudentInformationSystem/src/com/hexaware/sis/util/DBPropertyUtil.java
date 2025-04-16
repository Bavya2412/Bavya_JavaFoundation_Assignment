package com.hexaware.sis.util;



import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBPropertyUtil {

    private static final String PROPERTIES_FILE = "db.properties";

    public static String getConnectionString() {
        Properties props = new Properties();
        try (InputStream input = DBPropertyUtil.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + PROPERTIES_FILE);
                return null;
            }

            props.load(input);
            return props.getProperty("db.url");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getUsername() {
        return getProperty("db.username");
    }

    public static String getPassword() {
        return getProperty("db.password");
    }

    private static String getProperty(String key) {
        Properties props = new Properties();
        try (InputStream input = DBPropertyUtil.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input != null) {
                props.load(input);
                return props.getProperty(key);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
