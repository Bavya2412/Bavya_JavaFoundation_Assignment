package com.hexaware.sis.dao;

import com.hexaware.sis.entity.Student;
import java.util.List;

public interface IStudentDAO {
    void insertStudent(Student student);
    Student getStudentById(int studentId);
    List<Student> getAllStudents();
}
