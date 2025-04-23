package com.hexaware.sis.dao.impl;

import com.hexaware.sis.dao.ITeacherDAO;
import com.hexaware.sis.entity.Course;
import com.hexaware.sis.entity.Teacher;
import java.util.List;
import java.util.ArrayList;

public class TeacherDAOImpl implements ITeacherDAO {
    private List<Teacher> teachers = new ArrayList<>();

    @Override
    public void insertTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    @Override
    public Teacher getTeacherById(int teacherId) {
        for (Teacher teacher : teachers) {
            if (teacher.getTeacherId() == teacherId) {
                return teacher;
            }
        }
        return null;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teachers;
    }
    @Override
    public List<Course> getCoursesByTeacherId(int teacherId) {
        Teacher teacher = getTeacherById(teacherId);
        if (teacher != null) {
            return teacher.getAssignedCourses();  // Assuming this method exists in Teacher class
        }
        return null;  // If teacher not found
    }
}
