<%@page import="com.abc.entity.Test"%>
<%@page import="com.abc.dao.TestDAO"%>
<%@page import="com.abc.db.DBConnection"%>
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
    <title>Test page</title>
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


<div class="container-fluid p-3">
    <div class="row">
        <div class="col-md-4 offset-4">
            <div class="card my-card">
                <div class="card-body">
                    <p class="fs-3 text-center text-danger">Edit Test Details</p>

                    <!-- message print -->
                    <!-- for success msg -->
                    <c:if test="${not empty successMsg }">
                        <p class="text-center text-success fs-3">${successMsg}</p>
                        <c:remove var="successMsg" scope="session" />
                    </c:if>

                    <!-- for error msg -->
                    <c:if test="${not empty errorMsg }">
                        <p class="text-center text-danger fs-3">${errorMsg}</p>
                        <c:remove var="errorMsg" scope="session" />
                    </c:if>
                    <!-- End of message print -->



                    <%
                        //get specific technician id with the help of request.getParameter()
                        //which is coming from url for technician editing(which i pass through url when press edit technician)...
                        int id = Integer.parseInt(request.getParameter("id")); //this "id" is prefix name in URL
                        TestDAO testDAO = new TestDAO(DBConnection.getConn());
                        Test test = testDAO.getTestById(id);//call getDoctorById(id) which return technician of specific id
                    %>

                    <!-- boostrap form -->

                    <!-- doctors details form with specific id's doctors submitted data/details with editable field -->

                    <form action="../updateTest" method="post">
                        <div class="mb-3">
                            <label class="form-label">Test Name</label> <input
                                name="testName" type="text" placeholder="Enter test name"
                                class="form-control" value="<%=test.getTestName()%>">

                        </div>
                        <div class="mb-3">
                            <label class="form-label">Description</label> <input
                                name="description" type="text" placeholder="Enter description"
                                class="form-control" value="<%=test.getDescription()%>">

                        </div>
                        <div class="mb-3">
                            <label class="form-label">Cost</label> <input
                                name="cost" type="text"
                                placeholder="Enter cost" class="form-control"
                                value="<%=test.getCost()%>">
                        </div>


                        <!-- for update data of specific user's id used "hidden" id -->
                        <div class="mb-3">
                            <input name="id" type="hidden" class="form-control"
                                   value="<%=test.getId()%>">
                        </div>

                        <button type="submit" class="btn btn-danger text-white col-md-12">Update</button>
                    </form>
                    <!-- <br>Don't have an account? <a href="#!"
                        class="text-decoration-none">create one</a> -->
                    <!-- end of boostrap form -->

                </div>

            </div>
        </div>

    </div>
</div>





</body>
</html>
