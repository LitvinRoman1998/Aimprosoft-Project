<%--
  Created by IntelliJ IDEA.
  User: 210818
  Date: 11.02.2019
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Department Update</title>
</head>
<body>
<center>
    <c:import url="head.jsp"></c:import>
<h1>Updating Department</h1>
<table border="2">
    <tr>
        <th>Old Department Name</th>
    </tr>
    <tr>
        <td>${updatingDepartment.departmentName}</td>
    </tr>
</table>
<br>
    ${errorMessage}
<form action="/updateDepartment?departmentId=${updatingDepartment.departmentId}&oldDepartmentName=${updatingDepartment.departmentName}" method="post">
    <input name="newDepartmentName" value="<%=(request.getParameter("newDepartmentName")==null?"":request.getParameter("newDepartmentName"))%>" type="text" placeholder="New Department Name">
    <br>
    <br>
    <input type="submit">
</form>
</center>
</body>
</html>
