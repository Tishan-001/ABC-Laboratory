package com.abc.technician.servlet;

import java.io.IOException;

import com.abc.dao.ResultDAO;
import com.abc.entity.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.abc.dao.AppointmentDAO;
import com.abc.db.DBConnection;

@WebServlet("/updateStatus")
public class UpdateStatus extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
		 int 	id = Integer.parseInt(req.getParameter("id"));
		 int 	techId = Integer.parseInt(req.getParameter("techId"));
		 String comment = req.getParameter("comment");
		 
		 AppointmentDAO appDAO = new AppointmentDAO(DBConnection.getConn());
		 boolean f = appDAO.updateDrAppointmentCommentStatus(id, techId, comment);

			ResultDAO resultDAO = new ResultDAO(DBConnection.getConn());
			Result result = new Result(id, comment);
			boolean q = resultDAO.addResult(result);
		 
		 HttpSession session = req.getSession();

		 if(q && f) {
			 session.setAttribute("successMsg", "Comment updated");
			 resp.sendRedirect("technicians/patient.jsp");
			 
		 }else {
			 
			 session.setAttribute("errorMsg", "Something went wrong on server!");
			 resp.sendRedirect("technicians/patient.jsp");
			 
		 }
		 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
}
