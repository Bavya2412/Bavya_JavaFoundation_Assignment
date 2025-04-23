package com.hexaware.sis.entity;

import java.util.Date;
import com.hexaware.sis.exception.PaymentValidationException;

public class Payment {

    private int paymentId;
    private Student student;
    private double amount;
    private Date paymentDate;

    // Constructor with validation
    public Payment(int paymentId, Student student, double amount, Date paymentDate)
            throws PaymentValidationException {

        // Validation checks for payment
        if (student == null) {
            throw new PaymentValidationException("Student information is required for payment.");
        }

        if (amount <= 0) {
            throw new PaymentValidationException("Payment amount must be greater than 0.");
        }

        if (paymentDate == null) {
            throw new PaymentValidationException("Payment date cannot be null.");
        }

        this.paymentId = paymentId;
        this.student = student;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    // Method to display payment info
    public void displayPaymentInfo() {
        System.out.println("Payment ID: " + paymentId);
        System.out.println("Student: " + student.getFirstName() + " " + student.getLastName());
        System.out.println("Amount: $" + amount);
        System.out.println("Payment Date: " + paymentDate);
    }
}
