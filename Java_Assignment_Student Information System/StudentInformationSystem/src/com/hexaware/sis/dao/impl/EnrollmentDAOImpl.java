package com.hexaware.sis.dao.impl;

import com.hexaware.sis.dao.IEnrollmentDAO;
import com.hexaware.sis.entity.Enrollment;
import com.hexaware.sis.entity.Student;
import java.util.List;
import java.util.ArrayList;

public class EnrollmentDAOImpl implements IEnrollmentDAO {
    private List<Enrollment> enrollments = new ArrayList<>();

    @Override
    public void insertEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudent(Student student) {
        List<Enrollment> studentEnrollments = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudent().equals(student)) {
                studentEnrollments.add(enrollment);
            }
        }
        return studentEnrollments;
    }
}
