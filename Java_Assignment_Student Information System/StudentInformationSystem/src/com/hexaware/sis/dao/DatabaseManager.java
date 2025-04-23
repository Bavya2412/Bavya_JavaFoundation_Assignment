package com.hexaware.sis.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.hexaware.sis.exception.DatabaseConnectionException; 
import com.hexaware.sis.entity.Student;
import com.hexaware.sis.entity.Enrollment;
import com.hexaware.sis.entity.Course;
import com.hexaware.sis.entity.Teacher;
import com.hexaware.sis.entity.Payment;
import com.hexaware.sis.util.DbConnectionValidator;

public class DatabaseManager {

  
    public void insertStudent(Student student) {
        String query = "INSERT INTO students (student_id, first_name, last_name, dob, email, phone_number) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = DbConnectionValidator.getConnection();  
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, student.getStudentId());
                stmt.setString(2, student.getFirstName());
                stmt.setString(3, student.getLastName());
                stmt.setDate(4, new java.sql.Date(student.getDateOfBirth().getTime()));
                stmt.setString(5, student.getEmail());
                stmt.setString(6, student.getPhoneNumber());
                stmt.executeUpdate();
                System.out.println("Student added successfully!");
            }
        } catch (DatabaseConnectionException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Error inserting student: " + e.getMessage());
        }
    }


    public void insertEnrollment(Enrollment enrollment) {
        String query = "INSERT INTO enrollments (enrollment_id, student_id, course_id, enrollment_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DbConnectionValidator.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            
            stmt.setInt(1, enrollment.getEnrollmentId());  
            stmt.setInt(2, enrollment.getStudent().getStudentId());  
            stmt.setInt(3, enrollment.getCourse().getCourseId()); 
            stmt.setDate(4, new java.sql.Date(enrollment.getEnrollmentDate().getTime()));  

            stmt.executeUpdate();
            System.out.println("Enrollment added successfully!");
        } catch (SQLException e) {
            System.out.println("Error inserting enrollment: " + e.getMessage());
        }catch (DatabaseConnectionException e) {
            System.out.println("Database connection error: " + e.getMessage());}
    }


    public void insertCourse(Course course) {
        String query = "INSERT INTO courses (course_id, course_name, course_code) VALUES (?, ?, ?)";
        try {
            Connection conn = DbConnectionValidator.getConnection(); 
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, course.getCourseId());
                stmt.setString(2, course.getCourseName());
                stmt.setInt(3, course.getCourseId());
                
                stmt.executeUpdate();
                System.out.println("Course added successfully!");
            }
        } catch (DatabaseConnectionException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Error inserting course: " + e.getMessage());
        }
    }

    
    public void insertTeacher(Teacher teacher) {
        String query = "INSERT INTO teachers (teacher_id, first_name, last_name, email) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = DbConnectionValidator.getConnection();  
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, teacher.getTeacherId());
                stmt.setString(2, teacher.getFirstName());
                stmt.setString(3, teacher.getLastName());
                stmt.setString(4, teacher.getEmail());
                stmt.executeUpdate();
                System.out.println("Teacher added successfully!");
            }
        } catch (DatabaseConnectionException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Error inserting teacher: " + e.getMessage());
        }
    }

    
    public void insertPayment(Payment payment) {
        String query = "INSERT INTO payments (payment_id, student_id, amount, payment_date) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = DbConnectionValidator.getConnection();  
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, payment.getPaymentId());
                stmt.setInt(2, payment.getStudent().getStudentId()); 
                stmt.setDouble(3, payment.getAmount());
                stmt.setDate(4, new java.sql.Date(payment.getPaymentDate().getTime()));
                stmt.executeUpdate();
                System.out.println("Payment added successfully!");
            }
        } catch (DatabaseConnectionException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Error inserting payment: " + e.getMessage());
        }
    }
}
