package com.hexaware.sis.dao;

import com.hexaware.sis.entity.Enrollment;
import com.hexaware.sis.entity.Student;
import java.util.List;

public interface IEnrollmentDAO {
    void insertEnrollment(Enrollment enrollment);
    List<Enrollment> getEnrollmentsByStudent(Student student);
}
