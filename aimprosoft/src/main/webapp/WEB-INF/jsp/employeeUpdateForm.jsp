<%--
  Created by IntelliJ IDEA.
  User: 210818
  Date: 12.02.2019
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Employee Update</title>
</head>
<body>
<center>
    <c:import url="employeeHead.jsp"></c:import>
    <h1>Updating Employee</h1>
    <table border="2">
        <tr>
            <th>Old Employee Last Name</th>
            <th>Old Employee First Name</th>
            <th>Old Employee Middle Name</th>
            <th>Old Employee Birthday</th>
            <th>Old Employee Email</th>
            <th>Old Employee salary</th>
        </tr>
        <tr>
            <td>${updatingEmployee.lastName}</td>
            <td>${updatingEmployee.firstName}</td>
            <td>${updatingEmployee.middleName}</td>
            <td>${updatingEmployee.birthday}</td>
            <td>${updatingEmployee.email}</td>
            <td>${updatingEmployee.salary}</td>
        </tr>
    </table>
    <br>
    ${errorMessage}
    <form action="/updateEmployee?employeeId=${updatingEmployee.employeeId}&oldEmployeeLastName=${updatingEmployee.lastName}&oldEmployeeFirstName=${updatingEmployee.firstName}&oldEmployeeMiddleName=${updatingEmployee.middleName}&oldEmployeeBirthday=${updatingEmployee.birthday}&oldEmployeeEmail=${updatingEmployee.email}&oldEmployeeSalary=${updatingEmployee.salary}&departmentId=<%=request.getParameter("departmentId")%>&departmentName=<%= request.getParameter("departmentName")%>" method="post">
        <input name="newLastName" value="<%=(request.getParameter("newLastName")==null?"":request.getParameter("newLastName"))%>" type="text" placeholder="New Last Name">
        <br>
        <input name="newFirstName" value="<%=(request.getParameter("newFirstName")==null?"":request.getParameter("newFirstName"))%>" type="text" placeholder="New First Name">
        <br>
        <input name="newMiddleName" value="<%=(request.getParameter("newMiddleName")==null?"":request.getParameter("newMiddleName"))%>" type="text" placeholder="New Middle Name">
        <br>
        <input name="newBirthday" value="<%=(request.getParameter("newBirthday")==null?"":request.getParameter("newBirthday"))%>" type="date" placeholder="New Birthday">
        <br>
        <input name="newEmail" value="<%=(request.getParameter("newEmail")==null?"":request.getParameter("newEmail"))%>" type="email" placeholder="New Email">
        <br>
        <input name="newSalary" value="<%=(request.getParameter("newSalary")==null?"":request.getParameter("newSalary"))%>" type="number" placeholder="New Salary">
        <br>
        <br>
        <input type="submit">
    </form>
</center>
</body>
</html>
