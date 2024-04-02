package com.abc.dao;

import com.abc.entity.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {
    private Connection conn;

    public PaymentDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean addPayment(Payment payment) {
        boolean flag = false;
        try {
            String sql = "INSERT INTO payments (appointmentId, amount, payment_date, payment_status) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, payment.getAppointmentId());
            pstmt.setString(2, payment.getAmount());
            pstmt.setString(3, payment.getPaymentDate());
            pstmt.setString(4, payment.getPaymentStatus());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        try {
            String sql = "SELECT * FROM payments";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setId(rs.getInt("id"));
                payment.setAppointmentId(rs.getInt("appointmentId"));
                payment.setAmount(rs.getString("amount"));
                payment.setPaymentDate(rs.getString("payment_date"));
                payment.setPaymentStatus(rs.getString("payment_status"));
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    public Payment getPaymentByAppointmentId(int appointmentId) {
        Payment payment = null;
        try {
            String sql = "SELECT * FROM payments WHERE appointmentId = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, appointmentId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String amount = rs.getString("amount");
                String paymentDate = rs.getString("payment_date");
                String paymentStatus = rs.getString("payment_status");

                payment = new Payment();
                payment.setId(id);
                payment.setAppointmentId(appointmentId);
                payment.setAmount(amount);
                payment.setPaymentDate(paymentDate);
                payment.setPaymentStatus(paymentStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payment;
    }
}
