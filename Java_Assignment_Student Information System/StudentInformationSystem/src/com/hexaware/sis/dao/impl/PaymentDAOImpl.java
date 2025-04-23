package com.hexaware.sis.dao.impl;

import com.hexaware.sis.dao.IPaymentDAO;
import com.hexaware.sis.entity.Payment;
import java.util.List;
import java.util.ArrayList;

public class PaymentDAOImpl implements IPaymentDAO {
    private List<Payment> payments = new ArrayList<>();

    @Override
    public void insertPayment(Payment payment) {
        payments.add(payment);
    }

    @Override
    public List<Payment> getPaymentsByStudent(int studentId) {
        List<Payment> studentPayments = new ArrayList<>();
        for (Payment payment : payments) {
            if (payment.getStudent().getStudentId() == studentId) {
                studentPayments.add(payment);
            }
        }
        return studentPayments;
    }
}
