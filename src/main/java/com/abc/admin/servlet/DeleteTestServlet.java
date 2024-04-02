package com.abc.admin.servlet;

import com.abc.dao.TestDAO;
import com.abc.db.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/deleteTest")
public class DeleteTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get id(which is coming as string value) and convert into int
        int id = Integer.parseInt(req.getParameter("id"));

        TestDAO testDAO = new TestDAO(DBConnection.getConn());
        HttpSession session = req.getSession();

        boolean f = testDAO.deleteTestById(id);

        if(f==true) {
            session.setAttribute("successMsg", "Technician Deleted Successfully.");
            resp.sendRedirect("admin/view_test.jsp");
        }
        else {
            session.setAttribute("errorMsg", "Something went wrong on server!");
            resp.sendRedirect("admin/view_test.jsp");
        }
    }
}
