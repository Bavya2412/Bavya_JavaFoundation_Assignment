package com.hexaware.sis.entity;
import com.hexaware.sis.exception.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student {

    private int studentId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String email;
    private String phoneNumber;

    private List<Enrollment> enrollments;
    private List<Payment> payments;

    
    private static List<Student> allStudents = new ArrayList<>();

    
    public Student(int studentId, String firstName, String lastName, Date dateOfBirth, String email, String phoneNumber) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.enrollments = new ArrayList<>();
        this.payments = new ArrayList<>();
       
        allStudents.add(this);
    }

   
    public static Student getStudentById(int studentId) {
        for (Student student : allStudents) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null; 
    }

    
    public void enrollInCourse(Course course, Date date) throws DuplicateEnrollmentException, CourseNotFoundException, InvalidEnrollmentDataException  {
        if (course == null) {
            throw new CourseNotFoundException("Course not found.");
        }

        
        for (Enrollment e : enrollments) {
            if (e.getCourse().equals(course)) {
                throw new DuplicateEnrollmentException("Student is already enrolled in this course.");
            }
        }

        Enrollment enrollment = new Enrollment(0, this, course, date);  
        enrollments.add(enrollment);
        course.getEnrollments().add(enrollment); 
    }

   
    public void updateStudentInfo(String firstName, String lastName, Date dateOfBirth, String email, String phoneNumber)
            throws InvalidStudentDataException {

        if (firstName == null || lastName == null || email == null || !email.contains("@") || phoneNumber == null) {
            throw new InvalidStudentDataException("Invalid student data. Please provide all required fields.");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    
    public Payment makePayment(double amount, Date paymentDate) throws PaymentValidationException {
        if (amount <= 0 || paymentDate == null) {
            throw new PaymentValidationException("Invalid payment amount or date.");
        }

        Payment payment = new Payment(0, this, amount, paymentDate);
        payments.add(payment);
        return payment;
    }

    
    public void displayStudentInfo() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("DOB: " + dateOfBirth);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
    }

    
    public List<Course> getEnrolledCourses() {
        List<Course> courses = new ArrayList<>();
        for (Enrollment e : enrollments) {
            courses.add(e.getCourse());
        }
        return courses;
    }

    
    public List<Payment> getPaymentHistory() {
        return payments;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

   
    public static List<Student> getAllStudents() {
        return allStudents;
    }

    
}
