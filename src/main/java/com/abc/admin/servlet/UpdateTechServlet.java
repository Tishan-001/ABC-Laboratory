package com.abc.admin.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.abc.dao.TechDAO;
import com.abc.db.DBConnection;
import com.abc.entity.Technician;

@WebServlet("/updateTech")
public class UpdateTechServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			// get all data which is coming from technicians.jsp technician details
			String fullName = req.getParameter("fullName");
			String dateOfBirth = req.getParameter("dateOfBirth");
			String qualification = req.getParameter("qualification");
			String email = req.getParameter("email");
			String phone = req.getParameter("phone");
			String password = req.getParameter("password");
			
			//here need to get id also...for updating the technician details
			//doctors will update based on respective technician's id
			int id = Integer.parseInt(req.getParameter("id"));

			Technician technician = new Technician(id, fullName, dateOfBirth, qualification, email, phone, password);

			TechDAO docDAO = new TechDAO(DBConnection.getConn());

			boolean f = docDAO.updateTech(technician);

			HttpSession session = req.getSession();

			if (f == true) {
				session.setAttribute("successMsg", "Technician update Successfully");
				resp.sendRedirect("admin/view_technicians.jsp");

			} else {
				session.setAttribute("errorMsg", "Something went wrong on server!");
				resp.sendRedirect("admin/view_technicians.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
