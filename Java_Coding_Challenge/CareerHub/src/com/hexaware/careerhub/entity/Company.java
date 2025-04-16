package com.hexaware.careerhub.entity;


import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Company {
    private int companyId;
    private String companyName;
    private String location;

    private List<JobListing> jobListings = new ArrayList<>();

    public Company(int companyId, String companyName, String location) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.location = location;
    }

    public void postJob(String jobTitle, String jobDescription, String jobLocation,
                        double salary, String jobType) {
        JobListing job = new JobListing(jobListings.size() + 1, this.companyId, jobTitle,
                jobDescription, jobLocation, salary, jobType, LocalDateTime.now());
        jobListings.add(job);
    }

    public List<JobListing> getJobs() {
        return jobListings;
    }

    
    public int getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }
    public String getlocation() {
        return location;
    }
}

