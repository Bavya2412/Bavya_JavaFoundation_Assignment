package com.hexaware.sis.entity;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private int courseId;
    private String courseName;
    private List<Enrollment> enrollments;
    private static List<Course> allCourses = new ArrayList<>();

    public Course(int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.enrollments = new ArrayList<>();
        allCourses.add(this);
    }

   
    public static Course getCourseById(int courseId) {
        
        for (Course course : allCourses) {
            if (course.getCourseId() == courseId) {
                return course;
            }
        }
        return null;
    }

   
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
