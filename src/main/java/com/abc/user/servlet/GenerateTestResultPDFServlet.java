package com.abc.user.servlet;

import com.abc.utility.TestResultPDFGenerator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;

@WebServlet("/generateTestResultPDF")
public class GenerateTestResultPDFServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Assuming you are receiving these attributes from the session or request
        String fullName = request.getParameter("fullName");
        String doctorName = request.getParameter("doctorName");
        String phone = request.getParameter("phone");
        String testName = request.getParameter("testName");
        String result = request.getParameter("result");

        // Specify the path for the PDF
        String filePath = getServletContext().getRealPath("/") + "testResult.pdf";

        TestResultPDFGenerator pdfGenerator = new TestResultPDFGenerator();
        pdfGenerator.generateTestResultReport(filePath, fullName, doctorName, phone, testName, result);

        // Setting up response to download the PDF file
        File file = new File(filePath);
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "attachment; filename=testResult.pdf");
        response.setContentLength((int) file.length());

        java.nio.file.Files.copy(java.nio.file.Paths.get(filePath), response.getOutputStream());
    }

}
