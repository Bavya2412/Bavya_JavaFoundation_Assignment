package com.hexaware.sis.entity;

import com.hexaware.sis.exception.InvalidTeacherDataException;
import java.util.ArrayList;
import java.util.List;

public class Teacher {

    private int teacherId;
    private String firstName;
    private String lastName;
    private String email;

    private List<Course> assignedCourses;

    private static List<Teacher> allTeachers = new ArrayList<>();

    // Constructor
    public Teacher(int teacherId, String firstName, String lastName, String email) {
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.assignedCourses = new ArrayList<>();
        
        allTeachers.add(this);
    }

    // Method to update teacher information with validation
    public void updateTeacherInfo(String firstName, String lastName, String email) throws InvalidTeacherDataException {
        if (firstName == null || lastName == null || email == null || !email.contains("@")) {
            throw new InvalidTeacherDataException("Invalid teacher data. Please check name and email.");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Get list of assigned courses
    public List<Course> getAssignedCourses() {
        return assignedCourses;
    }

    // Add assigned course to the teacher's list of courses
    public void addAssignedCourse(Course course) {
        assignedCourses.add(course);
    }

    // Getter and Setter methods for teacher fields
    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Method to display teacher information
    public void displayTeacherInfo() {
        System.out.println("Teacher ID: " + teacherId);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Email: " + email);
    }

    // Static method to get teacher by ID
    public static Teacher getTeacherById(int teacherId) {
        for (Teacher teacher : allTeachers) {
            if (teacher.getTeacherId() == teacherId) {
                return teacher;
            }
        }
        return null;  // Return null if teacher not found
    }

    // Static method to get all teachers
    public static List<Teacher> getAllTeachers() {
        return allTeachers;
    }
   
}
