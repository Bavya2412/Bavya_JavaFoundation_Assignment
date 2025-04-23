package com.hexaware.sis.dao.impl;

import com.hexaware.sis.dao.ICourseDAO;
import com.hexaware.sis.entity.Course;
import java.util.List;
import java.util.ArrayList;

public class CourseDAOImpl implements ICourseDAO {
    private List<Course> courses = new ArrayList<>();

    @Override
    public void insertCourse(Course course) {
        courses.add(course);
    }

    @Override
    public Course getCourseById(int courseId) {
        for (Course course : courses) {
            if (course.getCourseId() == courseId) {
                return course;
            }
        }
        return null;
    }

    @Override
    public List<Course> getAllCourses() {
        return courses;
    }
}
