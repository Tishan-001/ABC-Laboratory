package com.abc.technician.servlet;

import java.io.IOException;

import com.abc.entity.Technician;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.abc.dao.TechDAO;
import com.abc.db.DBConnection;

@WebServlet("/techEditProfile")
public class TechEditProfileServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			// get all data which is coming from technicians.jsp technician details
			String fullName = req.getParameter("fullName");
			String dateOfBirth = req.getParameter("dateOfBirth");
			String qualification = req.getParameter("qualification");
			String email = req.getParameter("email");
			String phone = req.getParameter("phone");
			//String password = req.getParameter("password");

			
			int id = Integer.parseInt(req.getParameter("doctorId"));

			Technician technician = new Technician(id, fullName, dateOfBirth, qualification, email, phone, "");

			TechDAO docDAO = new TechDAO(DBConnection.getConn());

			boolean f = docDAO.editTechProfile(technician);

			HttpSession session = req.getSession();

			if (f == true) {
				Technician updateTechnicianObj = docDAO.getTechById(id);
				session.setAttribute("successMsgForD", "Technician update Successfully");
				session.setAttribute("doctorObj", updateTechnicianObj); // over ride or update old session value to new updated technician value.
				resp.sendRedirect("technician/edit_profile.jsp");

			} else {
				session.setAttribute("errorMsgForD", "Something went wrong on server!");
				resp.sendRedirect("technician/edit_profile.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
