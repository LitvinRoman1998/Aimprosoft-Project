<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 210818
  Date: 11.02.2019
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Department</title>
</head>
<body>
<center>
    <c:import url="head.jsp"></c:import>
<h1>Adding Department</h1>
    ${errorMessage}
<form action="/newDepartment" method="post">
    <input name="departmentName" value="<%=(request.getParameter("departmentName")==null)?"":request.getParameter("departmentName")%>" type="text" placeholder="Department Name">
    <br>
    <br>
    <input type="submit">
</form>
</center>
</body>
</html>
