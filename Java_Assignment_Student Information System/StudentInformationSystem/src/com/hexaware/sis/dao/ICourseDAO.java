package com.hexaware.sis.dao;

import com.hexaware.sis.entity.Course;
import java.util.List;

public interface ICourseDAO {
    void insertCourse(Course course);
    Course getCourseById(int courseId);
    List<Course> getAllCourses();
}
