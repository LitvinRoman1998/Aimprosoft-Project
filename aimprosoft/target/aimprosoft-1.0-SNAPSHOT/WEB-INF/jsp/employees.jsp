<%--
  Created by IntelliJ IDEA.
  User: 210818
  Date: 11.02.2019
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title> Employees</title>
</head>
<body>
<center>
    <c:import url="head.jsp"></c:import>
    <h1>Employees of <%= request.getParameter("departmentName")%> Department</h1>
    <c:choose>
    <c:when test="${employeeExists}">
    <table border="2">
        <tr>
            <th>Employee Id</th>
            <th>Employee Last Name</th>
            <th>Employee First Name</th>
            <th>Employee Middle Name</th>
            <th>Employee Birthday</th>
            <th>Employee Email</th>
            <th>Employee salary</th>
        </tr>
        <c:forEach items="${employees}" var="employee">
            <tr>
                <td>${employee.employeeId}</td>
                <td>${employee.lastName}</td>
                <td>${employee.firstName}</td>
                <td>${employee.middleName}</td>
                <td>${employee.birthday}</td>
                <td>${employee.email}</td>
                <td>${employee.salary}</td>

                <td>
                    <a href="/deleteEmployee?employeeId=${employee.employeeId}&departmentId=<%=request.getParameter("departmentId")%>&departmentName=<%= request.getParameter("departmentName")%>">
                        <input type="button" value="delete"/>
                    </a>
                </td>
                <td>
                    <a href="/updateEmployee?employeeId=${employee.employeeId}&employeeLastName=${employee.lastName}&employeeFirstName=${employee.firstName}&employeeMiddleName=${employee.middleName}&employeeBirthday=${employee.birthday}&employeeEmail=${employee.email}&employeeSalary=${employee.salary}&departmentId=<%=request.getParameter("departmentId")%>&departmentName=<%= request.getParameter("departmentName")%>">
                        <input type="button" value="update"/>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </c:when>
        <c:otherwise>
            <h2>This department has no employees</h2>
        </c:otherwise>
        </c:choose>
    </table>
    <br>
    <a href="/newEmployee?departmentId=<%=request.getParameter("departmentId")%>&departmentName=<%= request.getParameter("departmentName")%>">
        <input type="button" value="add new Employee"/>
    </a>
</center>
</body>
</html>
