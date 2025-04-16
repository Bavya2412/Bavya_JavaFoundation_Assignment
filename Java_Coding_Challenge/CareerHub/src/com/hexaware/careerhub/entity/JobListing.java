package com.hexaware.careerhub.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JobListing {
    private int jobID;
    private int companyID;
    private String jobTitle;
    private String jobDescription;
    private String jobLocation;
    private double salary;
    private String JobType;
    private LocalDateTime postedDate;

    // List of applicants who applied for the job
    private List<Applicant> applicants = new ArrayList<>();

    // Constructor
    public JobListing(int jobID, int companyID, String jobTitle, String jobDescription,
                      String jobLocation, double salary, String JobType, LocalDateTime postedDate) {
        this.jobID = jobID;
        this.companyID = companyID;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobLocation = jobLocation;
        this.salary = salary;
        this.JobType = JobType;
        this.postedDate = postedDate;
    }

    // Getters
    public int getJobID() {
        return jobID;
    }

    public int getCompanyID() {
        return companyID;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public double getSalary() {
        return salary;
    }

    public String getJobType() {
        return JobType;
    }

    public LocalDateTime getPostedDate() {
        return postedDate;
    }

    // Add a full applicant profile to this job listing
    public void apply(Applicant applicant) {
        applicants.add(applicant);
    }

    // Get all applicants for this job
    public List<Applicant> getApplicants() {
        return applicants;
    }
}
