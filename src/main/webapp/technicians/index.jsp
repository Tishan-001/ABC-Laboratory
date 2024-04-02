<%@page import="com.abc.entity.Technician"%>
<%@page import="com.abc.db.DBConnection"%>
<%@page import="com.abc.dao.TechDAO"%>
<%@ page import="com.abc.dao.TechDAO" %>
<%@ page import="com.abc.entity.Technician" %>
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
<title>Index Page | Technician</title>
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


	<!-- check is technician is login or not if login then only he can access techs dashboard -->
	<!-- otherwise redirect him to technician login page for log in -->
	<!-- if "techObj" is empty means no one is login. -->

	<c:if test="${empty techObj }">

		<c:redirect url="../technicians_login.jsp"></c:redirect>

	</c:if>

	<!-- check is technician is login or not -->


	<div class="container p-5">
		<p class="text-center text-success fs-3">Technician DashBoard</p>

		<%
		
		
		TechDAO docDAO = new TechDAO(DBConnection.getConn());
		int totalNumberOfTechnician = docDAO.countTotalTech();
		
		//get current login technician object from session
		Technician currentLoginTechnician = (Technician)session.getAttribute("techObj");
		
		
		%>

		<div class="row">
			<div class="col-md-4 offset-md-2">
				<div class="card my-card">
					<div class="card-body text-center text-success">
						<i class="fa-solid fa-user-doctor fa-3x"></i><br>
						<p class="fs-4 text-center">
							Technician <br><%= totalNumberOfTechnician %>
						</p>
					</div>
				</div>

			</div>

			<div class="col-md-4">
				<div class="card my-card">
					<div class="card-body text-center text-success">
						<i class="fa-solid fa-calendar-check fa-3x"></i><br>
						<p class="fs-4 text-center">
							Total Appointment <br> <%= docDAO.countTotalAppointmentByTechId(currentLoginTechnician.getId()) %>
						</p>
					</div>
				</div>

			</div>
		</div>


	</div>


</body>
</html>