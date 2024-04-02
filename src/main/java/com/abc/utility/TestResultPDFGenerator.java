package com.abc.utility;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class TestResultPDFGenerator {

    public void generateTestResultReport(String fileName, String fullName, String doctorName, String phone, String testName, String result) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();


            // Add report title
            Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            Paragraph title = new Paragraph("Test Result Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" ")); // Empty line

            // Table for patient and test details
            PdfPTable table = new PdfPTable(2); // 2 columns.
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f); // Space before table
            table.setSpacingAfter(10f); // Space after table

            // Set Column widths
            float[] columnWidths = {1f, 2f};
            table.setWidths(columnWidths);

            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Font bodyFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

            table.addCell(new Phrase("Full Name", headerFont));
            table.addCell(new Phrase(fullName, bodyFont));

            table.addCell(new Phrase("Doctor Name", headerFont));
            table.addCell(new Phrase(doctorName, bodyFont));

            table.addCell(new Phrase("Phone", headerFont));
            table.addCell(new Phrase(phone, bodyFont));

            table.addCell(new Phrase("Test Name", headerFont));
            table.addCell(new Phrase(testName, bodyFont));

            document.add(table);

            // Test Result Section
            document.add(new Chunk("Result of Report", headerFont));
            document.add(new Paragraph(" ")); // Empty line
            Paragraph testResultParagraph = new Paragraph(result, bodyFont);
            testResultParagraph.setIndentationLeft(20);
            document.add(testResultParagraph);

            document.close();
            System.out.println("Test result PDF was created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

