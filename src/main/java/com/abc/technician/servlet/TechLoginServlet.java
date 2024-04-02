package com.abc.technician.servlet;

import java.io.IOException;

import com.abc.dao.TechDAO;
import com.abc.entity.Technician;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.abc.db.DBConnection;


@WebServlet("/techLogin")
public class TechLoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//get email and password which is coming from technicians_login.jsp page
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		//create session
		HttpSession session = req.getSession();

		//create DB connection
		TechDAO docDAO = new TechDAO(DBConnection.getConn());
		
		//call loginDoctor() method for technician login which method declared in TechDAO
		Technician technician = docDAO.loginTech(email, password);

		if (technician != null) {
			//means technician is valid or exist
			//then store particular logged in technician object in session
			session.setAttribute("techObj", technician);
			//and redirect the particular technician index page which is reside technician folder
			resp.sendRedirect("technicians/index.jsp");//technician index means dashboard of doctors
		} else {
			session.setAttribute("errorMsg", "Invalid email or password");
			resp.sendRedirect("technicians_login.jsp");
		}

	}

}
