package com.hexaware.sis.dao;

import com.hexaware.sis.entity.Course;
import com.hexaware.sis.entity.Teacher;
import java.util.List;

public interface ITeacherDAO {
    void insertTeacher(Teacher teacher);
    Teacher getTeacherById(int teacherId);
    List<Teacher> getAllTeachers();
    List<Course> getCoursesByTeacherId(int teacherId); 
}
