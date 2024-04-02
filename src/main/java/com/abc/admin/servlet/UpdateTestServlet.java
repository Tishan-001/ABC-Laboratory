package com.abc.admin.servlet;

import com.abc.dao.TestDAO;
import com.abc.db.DBConnection;
import com.abc.entity.Test;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/updateTest")
public class UpdateTestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String testName = req.getParameter("testName");
            String description = req.getParameter("description");
            String cost = req.getParameter("cost");

            int id = Integer.parseInt(req.getParameter("id"));

            Test test = new Test(id, testName, description, cost);

            TestDAO testDAO = new TestDAO(DBConnection.getConn());

            boolean f = testDAO.updateTest(test);

            HttpSession session = req.getSession();

            if (f == true) {
                session.setAttribute("successMsg", "Test update Successfully");
                resp.sendRedirect("admin/view_test.jsp");

            } else {
                session.setAttribute("errorMsg", "Something went wrong on server!");
                resp.sendRedirect("admin/view_test.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
