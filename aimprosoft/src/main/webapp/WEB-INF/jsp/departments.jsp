<%--
  Created by IntelliJ IDEA.
  User: 210818
  Date: 11.02.2019
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Departments</title>
</head>
<body>
<center>
<h1>Departments</h1>
<table border="2">
    <tr>
        <th>Department Id</th>
        <th>Department Name</th>
    </tr>
    <c:forEach items="${departments}" var="department">
        <tr>
            <td>${department.departmentId}</td>
            <td>${department.departmentName}</td>
            <td>
                <a href="/deleteDepartment?departmentId=${department.departmentId}">
                    <input type="button" value="delete"/>
                </a>
            </td>
            <td>
                <a href="/updateDepartment?departmentId=${department.departmentId}&departmentName=${department.departmentName}">
                    <input type="button" value="update"/>
                </a>
            </td>
            <td>
                <a href="/employees?departmentId=${department.departmentId}&departmentName=${department.departmentName}">
                    <input type="button" value="employees"/>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="/newDepartment" >
    <input type="button" value="add new Department"/>
</a>
</center>
</body>
</html>
