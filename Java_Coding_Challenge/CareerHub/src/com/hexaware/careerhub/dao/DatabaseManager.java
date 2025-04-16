package com.hexaware.careerhub.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import com.hexaware.careerhub.entity.*;
import com.hexaware.careerhub.util.DBPropertyUtil;

public class DatabaseManager {

 
    private String dbUrl = DBPropertyUtil.getConnectionString();
    private String dbUsername = DBPropertyUtil.getUsername();
    private String dbPassword = DBPropertyUtil.getPassword();

    

    // connecting db
    private Connection getConnection() throws SQLException {
        try {
            // Ensure MySQL driver is loaded
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establishing connection 
            return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
    }

    // Method to insert a Job Listing into the database
    public void insertJobListing(JobListing job) {
        String sql = "INSERT INTO Jobs (JobID, CompanyID, JobTitle, JobDescription, JobLocation, Salary, JobType, PostedDate) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, job.getJobID());
            stmt.setInt(2, job.getCompanyID());
            stmt.setString(3, job.getJobTitle());
            stmt.setString(4, job.getJobDescription());
            stmt.setString(5, job.getJobLocation());
            stmt.setDouble(6, job.getSalary());
            stmt.setString(7, job.getJobType());
            stmt.setTimestamp(8, java.sql.Timestamp.valueOf(job.getPostedDate()));

            stmt.executeUpdate();
            System.out.println("Job inserted successfully!");

        } catch (SQLException e) {
            System.out.println("Error inserting job: " + e.getMessage());
        }
    }

    // Method to insert a Company into the database
    public void insertCompany(Company company) {
        String sql = "INSERT INTO Companies (CompanyID, CompanyName, Location) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, company.getCompanyId());
            stmt.setString(2, company.getCompanyName());
            stmt.setString(3, company.getlocation());

            stmt.executeUpdate();
            System.out.println("Company inserted successfully!");

        } catch (SQLException e) {
            System.out.println("Error inserting company: " + e.getMessage());
        }
    }

    // Method to insert an Applicant into the database
    public void insertApplicant(Applicant applicant) {
        String sql = "INSERT INTO Applicants (ApplicantID, FirstName, LastName, Email, Phone, Resume) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, applicant.getApplicantID());
            stmt.setString(2, applicant.getFirstName());
            stmt.setString(3, applicant.getLastName());
            stmt.setString(4, applicant.getEmail());
            stmt.setString(5, applicant.getPhone());
            stmt.setString(6, applicant.getResume());

            stmt.executeUpdate();
            System.out.println("Applicant inserted successfully!");

        } catch (SQLException e) {
            System.out.println("Error inserting applicant: " + e.getMessage());
        }
    }

    // Method to insert a Job Application into the database
    public void insertJobApplication(JobApplication application) {
        String sql = "INSERT INTO Applications (ApplicationID, JobID, ApplicantID, ApplicationDate, CoverLetter) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, application.getApplicationId());
            stmt.setInt(2, application.getJobId());
            stmt.setInt(3, application.getApplicantId());
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(application.getApplicationDate()));
            stmt.setString(5, application.getCoverLetter());

            stmt.executeUpdate();
            System.out.println("Application inserted successfully!");

        } catch (SQLException e) {
            System.out.println("Error inserting application: " + e.getMessage());
        }
    }

    // Method to get all Job Listings
    public List<JobListing> getJobListings() {
        List<JobListing> jobs = new ArrayList<>();
        String sql = "SELECT * FROM Jobs";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                JobListing job = new JobListing(
                    rs.getInt("JobID"),
                    rs.getInt("CompanyID"),
                    rs.getString("JobTitle"),
                    rs.getString("JobDescription"),
                    rs.getString("JobLocation"),
                    rs.getDouble("Salary"),
                    rs.getString("JobType"),
                    rs.getTimestamp("PostedDate").toLocalDateTime()
                );
                jobs.add(job);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching jobs: " + e.getMessage());
        }

        return jobs;
    }

    // Method to get all Companies
    public List<Company> getCompanies() {
        List<Company> companies = new ArrayList<>();
        String sql = "SELECT * FROM Companies";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Company company = new Company(
                    rs.getInt("CompanyID"),
                    rs.getString("CompanyName"),
                    rs.getString("Location")
                );
                companies.add(company);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching companies: " + e.getMessage());
        }

        return companies;
    }

    // Method to get all Applicants
    public List<Applicant> getApplicants() {
        List<Applicant> applicants = new ArrayList<>();
        String sql = "SELECT * FROM Applicants";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Applicant applicant = new Applicant(
                    rs.getInt("ApplicantID"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("Email"),
                    rs.getString("Phone"),
                    rs.getString("Resume")
                );
                applicants.add(applicant);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching applicants: " + e.getMessage());
        }

        return applicants;
    }

    // Method to get all Applications for a specific Job
    public List<JobApplication> getApplicationsForJob(int jobID) {
        List<JobApplication> applications = new ArrayList<>();
        String sql = "SELECT * FROM Applications WHERE JobID = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, jobID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                JobApplication application = new JobApplication(
                    rs.getInt("ApplicationID"),
                    rs.getInt("JobID"),
                    rs.getInt("ApplicantID"),
                    rs.getTimestamp("ApplicationDate").toLocalDateTime(),
                    rs.getString("CoverLetter")
                );
                applications.add(application);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching job applications: " + e.getMessage());
        }

        return applications;
    }
}
