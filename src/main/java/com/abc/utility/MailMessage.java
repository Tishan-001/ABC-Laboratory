package com.abc.utility;

import jakarta.mail.MessagingException;

public class MailMessage {

    public static void appointmentSuccess(String recipientEmail, String name, int appointmentId, String appointmentDate) {
        String recipient = recipientEmail;
        String subject = "Appointment Confirmation - ABC Laboratory";
        String htmlTextMessage = "<html>" +
                "  <body>" +
                "    <p>" +
                "      Dear " + name + ",<br/><br/>" +
                "      Thank you for choosing ABC Laboratory for your medical diagnostics needs.<br/><br/>" +
                "      Your appointment has been successfully scheduled and is confirmed." +
                "      <br/><h6>Please note that this email is part of a demo project, and no real appointment has been made.</h6>" +
                "      <br/>" +
                "      Here are your Appointment Details:<br/>" +
                "      <br/>" +
                "      <font style=\"color:red;font-weight:bold;\">Appointment ID:</font>" +
                "      <font style=\"color:green;font-weight:bold;\">" + appointmentId + "</font><br/>" +
                "      <br/>" +
                "      <font style=\"color:red;font-weight:bold;\">Appointment Date:</font> <font style=\"color:green;font-weight:bold;\">" +
                appointmentDate + "</font>" +
                "      <br/><br/>" +
                "      Thank you for your trust in us.<br/><br/>" +
                "      We look forward to serving you.<br/><br/> <font style=\"color:green;font-weight:bold;\">ABC Laboratory.</font>" +
                "    </p>" +
                "  </body>" +
                "</html>";

        try {
            JavaMailUtil.sendMail(recipient, subject, htmlTextMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static String sendMessage(String toEmailId, String subject, String htmlTextMessage) {
        try {
            JavaMailUtil.sendMail(toEmailId, subject, htmlTextMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            return "FAILURE";
        }
        return "SUCCESS";
    }
}
