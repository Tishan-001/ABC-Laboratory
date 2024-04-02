<%@page import="com.abc.db.DBConnection"%>
<%@page import="com.abc.dao.AppointmentDAO"%>
<%@ page import="com.abc.entity.Appointment" %>
<%@ page import="com.abc.entity.Test" %>
<%@ page import="com.abc.dao.TestDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="component/changes.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<div class="container mt-5 px-5">

    <%-- Retrieve the "id" parameter from the URL --%>
    <% int appointmentId = Integer.parseInt(request.getParameter("id")); %>
    <%
        AppointmentDAO appointmentDAO = new AppointmentDAO(DBConnection.getConn());
        Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);
        TestDAO testDAO = new TestDAO(DBConnection.getConn());
        Test test = testDAO.getTestById(appointment.getTestId());
    %>

    <div class="mb-4">
        <h2>Confirm Payment and Pay</h2>
        <span>please make the payment, after that you can enjoy all the features and benefits.</span>
    </div>

    <!-- Begin form section -->
    <form action="userPayment" method="post">
        <!-- Hidden field for appointment ID -->
        <input type="hidden" name="appointmentId" value="<%=appointmentId %>" />

        <!-- Payment Details Section -->
        <div class="col-md-8">
            <div class="card p-3">

                <h6 class="text-uppercase">Payment details</h6>
                <div class="inputbox mt-3"> <input type="text" name="name" class="form-control" required="required"> <span>Name on card</span> </div>
                <div class="row">

                    <div class="col-md-6">
                        <div class="inputbox mt-3 mr-2"> <input type="text" name="name" class="form-control" required="required"> <i class="fa fa-credit-card"></i> <span>Card Number</span>
                        </div>
                    </div>

                    <div class="col-md-6">

                        <div class="d-flex flex-row">
                            <div class="inputbox mt-3 mr-2"> <input type="text" name="name" class="form-control" required="required"> <span>Expiry</span> </div>
                            <div class="inputbox mt-3 mr-2"> <input type="text" name="name" class="form-control" required="required"> <span>CVV</span> </div>
                        </div>
                    </div>

                </div>


                <div class="mt-4 mb-4">
                    <h6 class="text-uppercase">Billing Address</h6>
                    <div class="row mt-3">
                        <div class="col-md-6">
                            <div class="inputbox mt-3 mr-2"> <input type="text" name="name" class="form-control" required="required"> <span>Street Address</span> </div>
                        </div>
                        <div class="col-md-6">
                            <div class="inputbox mt-3 mr-2"> <input type="text" name="name" class="form-control" required="required"> <span>City</span> </div>
                        </div>
                    </div>


                    <div class="row mt-2">
                        <div class="col-md-6">
                            <div class="inputbox mt-3 mr-2"> <input type="text" name="name" class="form-control" required="required"> <span>State/Province</span> </div>
                        </div>
                        <div class="col-md-6">
                            <div class="inputbox mt-3 mr-2"> <input type="text" name="name" class="form-control" required="required"> <span>Zip code</span> </div>
                        </div>
                    </div>

                </div>

            </div>

            <!-- Cost as a hidden field (or visible if you prefer) -->
            <input type="hidden" name="amount" value="<%=test.getCost()%>" />

            <div class="mt-4 mb-4 d-flex justify-content-between">
                <button type="submit" class="btn btn-success px-3">Pay Rs.<%=test.getCost() %></button>
            </div>
        </div>
    </form>
    <!-- End of form section -->

    <div class="col-md-4">
        <div class="card card-blue p-3 text-white mb-3">
            <span>You have to pay</span>
            <div class="d-flex flex-row align-items-end mb-3">
                <h1 class="mb-0 yellow">Rs.<%=test.getCost() %></h1>
            </div>
            <span>Enjoy all the features and perk after you complete the payment</span>
            <a href="#" class="yellow decoration">Know all the features</a>
        </div>
    </div>
</div>
</body>
</html>
