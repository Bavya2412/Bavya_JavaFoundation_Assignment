package com.hexaware.sis.entity;

import java.util.ArrayList;
import java.util.List;
import com.hexaware.sis.exception.InvalidTeacherDataException;

public class Teacher {

    private int teacherId;
    private String firstName;
    private String lastName;
    private String email;

    private List<Course> assignedCourses;

   
    private static List<Teacher> allTeachers = new ArrayList<>();

    
    public Teacher(int teacherId, String firstName, String lastName, String email) {
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.assignedCourses = new ArrayList<>();
        
        allTeachers.add(this);
    }

   
    public void updateTeacherInfo(String firstName, String lastName, String email) throws InvalidTeacherDataException {
        if (firstName == null || lastName == null || email == null || !email.contains("@")) {
            throw new InvalidTeacherDataException("Invalid teacher data. Please check name and email.");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    
    public List<Course> getAssignedCourses() {
        return assignedCourses;
    }

    public void addAssignedCourse(Course course) {
        assignedCourses.add(course);
    }

    
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

    
    public void displayTeacherInfo() {
        System.out.println("Teacher ID: " + teacherId);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Email: " + email);
    }

    
    public static Teacher getTeacherById(int teacherId) {
        for (Teacher teacher : allTeachers) {
            if (teacher.getTeacherId() == teacherId) {
                return teacher;
            }
        }
        return null;  
    }

    
    public static List<Teacher> getAllTeachers() {
        return allTeachers;
    }
}
