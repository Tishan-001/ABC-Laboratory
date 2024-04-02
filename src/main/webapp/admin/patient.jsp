<%@page import="com.abc.entity.Technician"%>
<%@page import="com.abc.entity.Appointment"%>
<%@page import="java.util.List"%>
<%@page import="com.abc.db.DBConnection"%>
<%@page import="com.abc.dao.AppointmentDAO"%>
<%@ page import="com.abc.dao.TechDAO" %>
<%@ page import="com.abc.dao.TestDAO" %>
<%@ page import="com.abc.entity.Test" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- for jstl tag -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- end of jstl tag -->

<%@page isELIgnored="false"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Patient Details | Admin</title>

<%@include file="../component/allcss.jsp"%>

<!-- customs css for this page -->
<style type="text/css">
.my-card {
	box-shadow: 0px 0px 10px 1px maroon;
	/*box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.3);*/
}
</style>
<!-- end of customs css for this page -->

</head>
<body>
	<%@include file="navbar.jsp"%>

	<div class="col-md-12 p-5">
		<div class="card my-card">
			<div class="card-body">
				<p class="text-center text-danger fs-3">Patient Details</p>

				<table class="table table-success table-striped">
					<thead>
						<tr class="table">
							<th scope="col" style="padding-right:70px">Full Name</th>
							<th scope="col">Gender</th>
							<th scope="col">Doctor Name</th>
							<th scope="col">Appointment</th>
							<th scope="col">Email</th>
							<th scope="col">Phone</th>
							<th scope="col" style="padding-right:50px">Test Name</th>
							<th scope="col" style="padding-right:70px">Technician Name</th>
							<th scope="col">Address</th>
							<th scope="col" style="padding-right:100px">Status</th>

						</tr>
					</thead>
					<tbody>

						<%
						AppointmentDAO appDAO = new AppointmentDAO(DBConnection.getConn());
						TechDAO docDAO = new TechDAO(DBConnection.getConn());
						TestDAO testDAO = new TestDAO(DBConnection.getConn());
						List<Appointment> list = appDAO.getAllAppointment();
						for(Appointment appList : list)
						{
							Technician technician =	docDAO.getTechById(appList.getTechnicianId());
							Test test = testDAO.getTestById(appList.getTestId());
						%>
						
						<tr>
							<th><%= appList.getFullName() %></th>
							<td><%= appList.getGender() %></td>
							<td><%= appList.getDoctorName() %></td>
							<%-- <td><%= appList.getFullName() %></td> --%>
							<td><%= appList.getAppointmentDate()%></td>
							<td><%= appList.getEmail()%></td>
							<td><%= appList.getPhone()%></td>
							<td><%= test.getTestName()%></td>
							<td><%= technician.getFullName()%></td>
							<td><%= appList.getAddress()%></td>
							<td><%= appList.getStatus()%></td>

						</tr>
						
						
						<%
						}
						%>

						
					</tbody>

				</table>

			</div>


		</div>

	</div>




</body>
</html>