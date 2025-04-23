package com.hexaware.sis.service;

import java.sql.Date;
import java.util.List;

import com.hexaware.sis.dao.*;
import com.hexaware.sis.dao.impl.CourseDAOImpl;
import com.hexaware.sis.dao.impl.EnrollmentDAOImpl;
import com.hexaware.sis.dao.impl.PaymentDAOImpl;
import com.hexaware.sis.dao.impl.StudentDAOImpl;
import com.hexaware.sis.dao.impl.TeacherDAOImpl;
import com.hexaware.sis.entity.*;
import com.hexaware.sis.exception.*;

public class Sis {

    private IStudentDAO studentDAO = new StudentDAOImpl();
    private ICourseDAO courseDAO = new CourseDAOImpl();
    private IEnrollmentDAO enrollmentDAO = new EnrollmentDAOImpl();
    private IPaymentDAO paymentDAO = new PaymentDAOImpl();
    private ITeacherDAO teacherDAO = new TeacherDAOImpl();

    public void addEnrollment(Student student, Course course, Date enrollmentDate)
            throws DuplicateEnrollmentException, CourseNotFoundException, InvalidEnrollmentDataException {

        // Optional: Check if already enrolled (you can implement that in EnrollmentDAO)
        List<Enrollment> enrollments = enrollmentDAO.getEnrollmentsByStudent(student);
        for (Enrollment e : enrollments) {
            if (e.getCourse().getCourseId() == course.getCourseId()) {
                throw new DuplicateEnrollmentException("Student is already enrolled in this course.");
            }
        }

        Enrollment enrollment = new Enrollment(0, student, course, enrollmentDate);
        enrollmentDAO.insertEnrollment(enrollment);
        System.out.println("Enrollment successful and stored in database.");
    }

    public void addPayment(Student student, double amount, Date paymentDate) throws PaymentValidationException {
        if (amount <= 0) {
            throw new PaymentValidationException("Payment amount must be greater than zero.");
        }

        Payment payment = new Payment(0, student, amount, paymentDate);
        paymentDAO.insertPayment(payment);
        System.out.println("Payment successful and stored in database.");
    }

    public List<Enrollment> getEnrollmentsForStudent(Student student) {
        return enrollmentDAO.getEnrollmentsByStudent(student);
    }

    public List<Course> getCoursesForTeacher(Teacher teacher) {
        return teacherDAO.getCoursesByTeacherId(teacher.getTeacherId());  // assuming this method exists
    }

    public Student fetchStudentById(int id) {
        return studentDAO.getStudentById(id);
    }

    public Course fetchCourseById(int id) {
        return courseDAO.getCourseById(id);
    }
}
