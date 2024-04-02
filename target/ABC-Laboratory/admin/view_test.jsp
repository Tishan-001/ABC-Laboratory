<%@page import="com.abc.entity.Test"%>
<%@page import="com.abc.dao.TestDAO"%>
<%@page import="java.util.List"%>
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

        <div class="col-md-12">
            <div class="card my-card">
                <div class="card-body">
                    <p class="fs-3 text-center text-danger">List of Tests</p>

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

                    <!-- table for technician list -->

                    <table class="table table-striped">
                        <thead>
                        <tr class="table-info">
                            <!-- <th scope="col">ID</th> -->
                            <th scope="col">Test Name</th>
                            <th scope="col">Description</th>
                            <th scope="col">Cost</th>
                            <th colspan="2" class="text-center" scope="col">Action</th>
                        </tr>
                        </thead>
                        <tbody>

                        <%
                            TestDAO testDAO = new TestDAO(DBConnection.getConn());
                            List<Test> listOfDoc = testDAO.getAllTests();
                            for (Test testList : listOfDoc) {
                        %>
                        <tr>
                            <%-- <th scope="row"><%= technicianLst.getId()%></th> --%>
                            <th><%=testList.getTestName()%></th>
                            <td><%=testList.getDescription()%></td>
                            <td><%=testList.getCost()%></td>


                            <td><a class="btn btn-sm btn-primary"
                                   href="edit_test.jsp?id=<%=testList.getId()%>">Edit</a></td>
                            <td><a class="btn btn-sm btn-danger" href="../deleteTest?id=<%= testList.getId() %>">Delete</a></td>



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