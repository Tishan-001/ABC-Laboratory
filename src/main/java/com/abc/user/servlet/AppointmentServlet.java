package com.abc.user.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.abc.dao.AppointmentDAO;
import com.abc.db.DBConnection;
import com.abc.entity.Appointment;

@WebServlet("/addAppointment")
public class AppointmentServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	int userId	= Integer.parseInt(req.getParameter("userId"));
	String fullName = req.getParameter("fullName");
	String gender = req.getParameter("gender");
	String appointmentDate = req.getParameter("appointmentDate");
	String email = req.getParameter("email");
	String phone = req.getParameter("phone");
	String doctor = req.getParameter("doctor");
	int testId = Integer.parseInt(req.getParameter("testNameSelect"));
	int techId = Integer.parseInt(req.getParameter("techNameSelect"));
	String address = req.getParameter("address");
	
	
	Appointment appointment = new Appointment(userId, fullName, gender, doctor, appointmentDate, email, phone, testId, techId, address, "Pending");
	
	AppointmentDAO appointmentDAO = new AppointmentDAO(DBConnection.getConn());
	int generatedId = appointmentDAO.addAppointment(appointment);

		// Get the session
		HttpSession session = req.getSession();

		if(generatedId != -1) {
			session.setAttribute("successMsg", "Appointment is recorded Successfully.");
			resp.sendRedirect("payment.jsp?id=" + generatedId); // Use the generated appointment ID here
		} else {
			session.setAttribute("errorMsg", "Something went wrong on server!");
			resp.sendRedirect("user_appointment.jsp");
		}


	}

	
}
