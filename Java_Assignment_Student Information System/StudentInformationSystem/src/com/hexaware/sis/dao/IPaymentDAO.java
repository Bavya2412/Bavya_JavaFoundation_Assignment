package com.hexaware.sis.dao;

import com.hexaware.sis.entity.Payment;
import java.util.List;

public interface IPaymentDAO {
    void insertPayment(Payment payment);
    List<Payment> getPaymentsByStudent(int studentId);
}
