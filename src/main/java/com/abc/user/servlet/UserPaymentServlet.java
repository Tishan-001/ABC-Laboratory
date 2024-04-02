package com.abc.user.servlet;

import com.abc.dao.AppointmentDAO;
import com.abc.dao.PaymentDAO;
import com.abc.db.DBConnection;
import com.abc.entity.Appointment;
import com.abc.entity.Payment;
import com.abc.utility.MailMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/userPayment")
public class UserPaymentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            int appointmentId = Integer.parseInt(req.getParameter("appointmentId"));
            String amount = req.getParameter("amount");

            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedDate = currentDate.format(formatter);

            Payment payment = new Payment(appointmentId,amount, formattedDate , "Done");

            PaymentDAO paymentDAO = new PaymentDAO(DBConnection.getConn());

            HttpSession session = req.getSession();

            boolean f = paymentDAO.addPayment(payment);
            if (f == true) {

                AppointmentDAO appointmentDAO = new AppointmentDAO(DBConnection.getConn());
                Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);

                MailMessage.appointmentSuccess(appointment.getEmail(), appointment.getFullName(),
                        appointment.getId(), appointment.getAppointmentDate());
                session.setAttribute("successMsg", "Payment Successfully");
                resp.sendRedirect("view_appointment.jsp");//which page you want to show this msg

            } else {

                session.setAttribute("errorMsg", "Something went wrong!");
                resp.sendRedirect("payment.jsp?id=" + appointmentId);//which page you want to show this msg

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
