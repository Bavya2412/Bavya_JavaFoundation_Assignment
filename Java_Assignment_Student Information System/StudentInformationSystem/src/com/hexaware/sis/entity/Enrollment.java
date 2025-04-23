package com.hexaware.sis.entity;

import java.util.Date;

public class Enrollment {

    private int enrollmentId;
    private Student student;
    private Course course;
    private Date enrollmentDate;

    public Enrollment(int enrollmentId, Student student, Course course, Date enrollmentDate) {
        this.enrollmentId = enrollmentId;
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
    }

    // Getters and Setters
    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    // You can also include utility methods for better management, such as:
    public void displayEnrollmentDetails() {
        System.out.println("Enrollment ID: " + enrollmentId);
        System.out.println("Student: " + student.getFirstName() + " " + student.getLastName());
        System.out.println("Course: " + course.getCourseName());
        System.out.println("Enrollment Date: " + enrollmentDate);
    }

}
