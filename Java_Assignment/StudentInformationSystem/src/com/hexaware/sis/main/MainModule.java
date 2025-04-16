package com.hexaware.sis.main;

import com.hexaware.sis.entity.*;
import com.hexaware.sis.exception.*;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class MainModule {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Enroll Student in Course");
            System.out.println("2. Record Payment for Student");
            System.out.println("3. Generate Student Enrollment Report");
            System.out.println("4. Exit");

            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    enrollStudentInCourse(sc);
                    break;
                case 2:
                    recordPayment(sc);
                    break;
                case 3:
                    generateEnrollmentReport(sc);
                    break;
                case 4:
                    System.out.println("Exiting the application...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option. Please select again.");
            }
        }
    }

    private static void enrollStudentInCourse(Scanner sc) {
        try {
            System.out.print("Enter Student ID: ");
            int studentId = sc.nextInt();
            sc.nextLine(); 

            System.out.print("Enter Course ID: ");
            int courseId = sc.nextInt();
            sc.nextLine(); 

            Student student = Student.getStudentById(studentId);
            Course course = Course.getCourseById(courseId);

            if (student == null || course == null) {
                System.out.println("Student or Course not found!");
                return;
            }

            System.out.print("Enter Enrollment Date (yyyy-mm-dd): ");
            String enrollmentDateStr = sc.nextLine();
            java.sql.Date enrollmentDate = java.sql.Date.valueOf(enrollmentDateStr); 

            sis SIS = new sis();
            SIS.addEnrollment(student, course, enrollmentDate);

        } catch (DuplicateEnrollmentException | CourseNotFoundException | InvalidEnrollmentDataException e) {
            System.out.println("Error enrolling student: " + e.getMessage());
        }
    }

    private static void recordPayment(Scanner sc) {
        try {
            System.out.print("Enter Student ID: ");
            int studentId = sc.nextInt();
            sc.nextLine(); 

            Student student = Student.getStudentById(studentId);

            if (student == null) {
                System.out.println("Student not found!");
                return;
            }

            System.out.print("Enter Payment Amount: ");
            double amount = sc.nextDouble();
            sc.nextLine(); 

            System.out.print("Enter Payment Date (yyyy-mm-dd): ");
            String paymentDateStr = sc.nextLine();
            java.sql.Date paymentDate = java.sql.Date.valueOf(paymentDateStr); 

            sis SIS = new sis();
            SIS.addPayment(student, amount, paymentDate);

        } catch (PaymentValidationException e) {
            System.out.println("Error recording payment: " + e.getMessage());
        }
    }

    private static void generateEnrollmentReport(Scanner sc) {
        try {
            System.out.print("Enter Student ID: ");
            int studentId = sc.nextInt();
            sc.nextLine(); 

            Student student = Student.getStudentById(studentId);

            if (student == null) {
                System.out.println("Student not found!");
                return;
            }

            sis SIS = new sis();
            List<Enrollment> enrollments = SIS.getEnrollmentsForStudent(student);

            System.out.println("\nEnrollments for " + student.getFirstName() + ":");
            for (Enrollment e : enrollments) {
                System.out.println("- " + e.getCourse().getCourseName());
            }

        } catch (Exception e) {
            System.out.println("Error generating enrollment report: " + e.getMessage());
        }
    }
}
