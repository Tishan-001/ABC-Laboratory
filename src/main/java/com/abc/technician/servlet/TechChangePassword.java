package com.abc.technician.servlet;

import java.io.IOException;

import com.abc.dao.TechDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.abc.db.DBConnection;

@WebServlet("/techChangePassword")
public class TechChangePassword extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int doctorId = Integer.parseInt(req.getParameter("doctorId"));
		String newPassword = req.getParameter("newPassword");
		String oldPassword = req.getParameter("oldPassword");

		TechDAO techDAO = new TechDAO(DBConnection.getConn());

		HttpSession session = req.getSession();

		if (techDAO.checkOldPassword(doctorId, oldPassword)) {

			if (techDAO.changePassword(doctorId, newPassword)) {
				
				session.setAttribute("successMsg", "Password change successfully.");
				resp.sendRedirect("technician/edit_profile.jsp");

			} else {
				
				session.setAttribute("errorMsg", "Something went wrong on server!");
				resp.sendRedirect("technician/edit_profile.jsp");

			}

		} else {
			session.setAttribute("errorMsg", "Old Password not match");
			resp.sendRedirect("technician/edit_profile.jsp");

		}
	}

}
