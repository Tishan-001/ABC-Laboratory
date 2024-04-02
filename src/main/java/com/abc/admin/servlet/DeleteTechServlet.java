package com.abc.admin.servlet;

import java.io.IOException;

import com.abc.dao.TechDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.abc.db.DBConnection;

@WebServlet("/deleteTech")
public class DeleteTechServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//get id(which is coming as string value) and convert into int	
		int id = Integer.parseInt(req.getParameter("id"));
		
		TechDAO docDAO = new TechDAO(DBConnection.getConn());
		HttpSession session = req.getSession();
		
		boolean f = docDAO.deleteTechById(id);
		
		if(f==true) {
			session.setAttribute("successMsg", "Technician Deleted Successfully.");
			resp.sendRedirect("admin/view_technicians.jsp");
		}
		else {
			session.setAttribute("errorMsg", "Something went wrong on server!");
			resp.sendRedirect("admin/view_technicians.jsp");
		}
	}
	
	

}
