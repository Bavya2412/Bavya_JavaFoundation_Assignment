package com.hexaware.careerhub.entity;


import java.time.LocalDateTime;

public class JobApplication {
    private int applicationId;
    private int jobId;
    private int applicantId;
    private LocalDateTime applicationDate;
    private String coverLetter;

    public JobApplication(int applicationId, int jobId, int applicantId,
                          LocalDateTime applicationDate, String coverLetter) {
        this.applicationId = applicationId;
        this.jobId = jobId;
        this.applicantId = applicantId;
        this.applicationDate = applicationDate;
        this.coverLetter = coverLetter;
    }

    // Getters
    public int getApplicationId() {
        return applicationId;
    }

    public int getJobId() {
        return jobId;
    }

    public int getApplicantId() {
        return applicantId;
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public String getCoverLetter() {
        return coverLetter;
    }
}


