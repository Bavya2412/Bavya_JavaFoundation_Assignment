package com.hexaware.careerhub.main;

import com.hexaware.careerhub.util.DbConnectionValidator;
import com.hexaware.careerhub.exception.DatabaseConnectionException;

import java.sql.*;
import java.util.Scanner;

public class MainModule {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Retrieve Job Listings");
            System.out.println("2. Create Applicant Profile");
            System.out.println("3. Apply for a Job");
            System.out.println("4. Post a Job");
            System.out.println("5. Search Jobs by Salary Range");
            System.out.println("6. Exit");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    retrieveJobListings();
                    break;
                case 2:
                    createApplicantProfile(sc);
                    break;
                case 3:
                    applyForJob(sc);
                    break;
                case 4:
                    postJob(sc);
                    break;
                case 5:
                    searchJobsBySalaryRange(sc);
                    break;
                case 6:
                    System.out.println("Exiting the application...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option. Please select again.");
            }
        }
    }

    private static void retrieveJobListings() {
        try (Connection conn = DbConnectionValidator.getConnection();
             Statement stmt = conn.createStatement();
        		ResultSet rs = stmt.executeQuery(
        		         "SELECT jl.JobTitle, c.CompanyName, jl.Salary " +
        		         "FROM joblistings jl " +
        		         "JOIN companies c ON jl.CompanyId = c.CompanyId")) {

        		    System.out.println("\nJob Listings:");
        		    while (rs.next()) {
        		        String jobTitle = rs.getString("JobTitle");
        		        String companyName = rs.getString("CompanyName");
        		        double salary = rs.getDouble("Salary");

        		        System.out.println("Job Title: " + jobTitle + " , Company: " + companyName + " , Salary: " + salary);
            }

        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println("Error retrieving job listings: " + e.getMessage());
        }
    }

    private static void createApplicantProfile(Scanner sc) {
    	
    	    // Updated query to insert firstName, lastName, email, and phone
    	   // String query = "INSERT INTO Applicants (FirstName, LastName, email, phone) VALUES (?, ?, ?, ?)";
    	    
    	    try (Connection conn = DbConnectionValidator.getConnection()) {
    	        System.out.print("Enter Applicant First Name: ");
    	        String firstName = sc.nextLine();

    	        System.out.print("Enter Applicant Last Name: ");
    	        String lastName = sc.nextLine();

    	        System.out.print("Enter Applicant Email: ");
    	        String email = sc.nextLine();

    	        System.out.print("Enter Applicant Phone: ");
    	        String phone = sc.nextLine();

    	        // Adding a default value for the Resume column ("No experience")
    	        String defaultResume = "No experience"; // You can change this default value to whatever you prefer.

    	        // Updated query to insert firstName, lastName, email, phone, and resume
    	        String query = "INSERT INTO Applicants (FirstName, LastName, email, phone, Resume) VALUES (?, ?, ?, ?, ?)";
    	        
    	        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
    	            pstmt.setString(1, firstName);
    	            pstmt.setString(2, lastName);  // Setting the last name
    	            pstmt.setString(3, email);
    	            pstmt.setString(4, phone);
    	            pstmt.setString(5, defaultResume); // Setting the default value for the Resume column

    	            int count = pstmt.executeUpdate();
    	            System.out.println(count + " record(s) inserted into Applicants table.");
    	        }

    	    } catch (SQLException | DatabaseConnectionException e) {
    	        System.out.println("Error creating applicant profile: " + e.getMessage());
    	    }


    }

    private static void applyForJob(Scanner sc) {
        Connection conn = null;
        try {
            System.out.print("Enter Applicant ID: ");
            int applicantID = sc.nextInt();

            System.out.print("Enter Job ID to Apply: ");
            int jobID = sc.nextInt();
            sc.nextLine(); // Consume newline

            // Ask user for a Cover Letter (one-line text)
            System.out.print("Enter Cover Letter (one line text): ");
            String coverLetter = sc.nextLine();  // User input for cover letter

            conn = DbConnectionValidator.getConnection();

            // Updated query to include CoverLetter
            String query = "INSERT INTO Applications (applicantID, jobID, coverLetter) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, applicantID);
                pstmt.setInt(2, jobID);
                pstmt.setString(3, coverLetter);  // Setting cover letter text

                int count = pstmt.executeUpdate();
                System.out.println(count + " job application(s) submitted.");
            }

        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println("Error submitting job application: " + e.getMessage());
        } 
    }


    private static void postJob(Scanner sc) {
        try (Connection conn = DbConnectionValidator.getConnection()) {
            System.out.print("Enter Job Title: ");
            String jobTitle = sc.nextLine();

            System.out.print("Enter Company ID: ");
            int companyID = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Job Description: ");
            String jobDescription = sc.nextLine();

            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();
            sc.nextLine();

            System.out.print("Enter Job Location: ");
            String jobLocation = sc.nextLine();

            String query = "INSERT INTO joblistings (jobTitle, companyID, jobDescription, salary, jobLocation) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, jobTitle);
                pstmt.setInt(2, companyID);
                pstmt.setString(3, jobDescription);
                pstmt.setDouble(4, salary);
                pstmt.setString(5, jobLocation);

                int count = pstmt.executeUpdate();
                System.out.println(count + " job listing(s) posted.");
            }

        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println("Error posting job: " + e.getMessage());
        }
    }

    private static void searchJobsBySalaryRange(Scanner sc) {
        try (Connection conn = DbConnectionValidator.getConnection()) {
            System.out.print("Enter minimum salary: ");
            double minSalary = sc.nextDouble();

            System.out.print("Enter maximum salary: ");
            double maxSalary = sc.nextDouble();
            sc.nextLine();

            // SQL query with JOIN between joblistings and companies to fetch company name
            String query = "SELECT j.jobTitle, c.companyName, j.salary " +
                           "FROM joblistings j " +
                           "JOIN companies c ON j.companyID = c.companyID " +
                           "WHERE j.salary BETWEEN ? AND ?";

            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setDouble(1, minSalary);
                pstmt.setDouble(2, maxSalary);

                try (ResultSet rs = pstmt.executeQuery()) {
                    System.out.println("\nJob Listings in Salary Range:");
                    while (rs.next()) {
                        String jobTitle = rs.getString("jobTitle");
                        String companyName = rs.getString("companyName");
                        double salary = rs.getDouble("salary");

                        System.out.println("Job Title: " + jobTitle + ", Company: " + companyName + ", Salary: " + salary);
                    }
                }
            }

        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println("Error searching jobs: " + e.getMessage());
        }
    }

}
