package com.hexaware.sis.dao.impl;

import com.hexaware.sis.dao.IStudentDAO;
import com.hexaware.sis.entity.Student;
import com.hexaware.sis.util.DbConnUtil; // Now using DbConnUtil for database connections
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements IStudentDAO {
    
    private static final String INSERT_STUDENT_SQL = "INSERT INTO students (student_id, first_name, last_name, email) VALUES (?, ?, ?, ?)";
    private static final String SELECT_STUDENT_BY_ID = "SELECT * FROM students WHERE student_id = ?";
    private static final String SELECT_ALL_STUDENTS = "SELECT * FROM students";

    @Override
    public void insertStudent(Student student) {
        try (Connection connection = DbConnUtil.getConnection(); 
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL)) {
            preparedStatement.setInt(1, student.getStudentId());
            preparedStatement.setString(2, student.getFirstName());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.setString(4, student.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Student getStudentById(int studentId) {
        try (Connection conn = DbConnUtil.getConnection()) {
            System.out.println("Attempting to find student with ID: " + studentId);
            String query = "SELECT * FROM students WHERE student_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Student found with ID: " + studentId);
                int id = rs.getInt("student_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                // Create student object with the fetched data
                return new Student(id, firstName, lastName, email);
            } else {
                System.out.println("No student found with ID: " + studentId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Return null if student not found
    }


    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = DbConnUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getInt("student_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDate("date_of_birth"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_number")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}
