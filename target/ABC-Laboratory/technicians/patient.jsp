<%@page import="com.abc.entity.Appointment"%>
<%@page import="java.util.List"%>
<%@page import="com.abc.entity.Technician"%>
<%@page import="com.abc.dao.AppointmentDAO"%>
<%@page import="com.abc.db.DBConnection"%>
<%@page import="com.abc.dao.TechDAO"%>
<%@ page import="com.abc.entity.Technician" %>
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
<title>Patient Page</title>


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

	<!-- if "techObj" is empty means no one is login. -->

	<c:if test="${empty techObj }">

		<c:redirect url="../technicians_login.jsp"></c:redirect>

	</c:if>

	<!-- check is technician is login or not -->


	<div class="container p-3">
		<div class="row">
			<div class="col-md-12">
				<div class="card my-card">
					<div class="card-body">
						<p class="text-center text-success fs-3">Patient Details</p>

						<!-- message print -->
						<!-- for success msg -->
						<c:if test="${not empty successMsg }">
							<p class="text-center text-success fs-5">${successMsg}</p>
							<c:remove var="successMsg" scope="session" />
						</c:if>

						<!-- for error msg -->
						<c:if test="${not empty errorMsg }">
							<p class="text-center text-danger fs-5">${errorMsg}</p>
							<c:remove var="errorMsg" scope="session" />
						</c:if>
						<!-- End of message print -->

						<!-- table for patient list -->

						<table class="table table-striped">
							<thead>
								<tr>
									<th scope="col" style="padding-right: 100px">Full Name</th>
									<th scope="col">Gender</th>
									<th scope="col">Doctor Name</th>
									<th scope="col">Appointment Date</th>
									<th scope="col">Email</th>
									<th scope="col">Phone</th>
									<th scope="col">Test name</th>
									<th scope="col">Status</th>
									<th scope="col">Action</th>
								</tr>
							</thead>
							<tbody>

								<%
								Technician technician = (Technician) session.getAttribute("techObj");

								//TechDAO docDAO = new TechDAO(DBConnection.getConn());
								AppointmentDAO appDAO = new AppointmentDAO(DBConnection.getConn());
									TestDAO testDAO = new TestDAO(DBConnection.getConn());
								List<Appointment> list = appDAO.getAllAppointmentByLoginTechnician(technician.getId());
								for (Appointment applist : list) {
									Test test = testDAO.getTestById(applist.getTestId());
								%>

								<tr>
									<th><%=applist.getFullName()%></th>
									<td><%=applist.getGender()%></td>
									<td><%=applist.getDoctorName()%></td>
									<td><%=applist.getAppointmentDate()%></td>
									<td><%=applist.getEmail()%></td>
									<td><%=applist.getPhone()%></td>
									<td><%=test.getTestName()%></td>
									<td><%=applist.getStatus()%></td>

									<td>
										<%
										if ("Pending".equals(applist.getStatus())) {
										%> <a href="comment.jsp?id=<%=applist.getId()%>"
										class="btn btn-success btn-sm">Comment / Prescription</a> 
										<%
 										} else {
 										%> 
 										 <a href="#!" class="btn btn-success btn-sm disabled"><i
											class="fa fa-comment"></i> Comment / Prescription</a>
											 
										<%
 										}
 										%>


									</td>
									
								</tr>



								<%
								}
								%>


							</tbody>
						</table>

						<!-- end table for technician list -->



					</div>
				</div>
			</div>

		</div>

	</div>

</body>
</html>