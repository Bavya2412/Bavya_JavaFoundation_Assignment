package com.hexaware.careerhub.entity;

public class Applicant {
    private int applicantID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String resume;
    private String coverLetter;

    // Constructor
    public Applicant(int applicantID, String firstName, String lastName, String email, String phone, String resume) {
        this.applicantID = applicantID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.resume = resume;
    }

    // Method to create a profile
    public void createProfile(String email, String firstName, String lastName, String phone) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    
    public void applyForJob(int jobID, String coverLetter) {
        this.coverLetter = coverLetter;
        System.out.println("Applicant " + firstName + " applied for Job ID " + jobID + " with cover letter:\n" + coverLetter);
        
    }

    
    public int getApplicantID() {
        return applicantID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getResume() {
        return resume;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    
    public void setApplicantID(int applicantID) {
        this.applicantID = applicantID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }
}
