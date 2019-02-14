<%--
  Created by IntelliJ IDEA.
  User: 210818
  Date: 12.02.2019
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>New Employee</title>
</head>
<body>
<center>
    <c:import url="employeeHead.jsp"></c:import>
    <h1>Adding Employee</h1>
    ${errorMessage}
    <form action="/newEmployee?departmentId=<%=request.getParameter("departmentId")%>&departmentName=<%=request.getParameter("departmentName")%>" method="post">
        <input name="lastName" value="<%=(request.getParameter("lastName")==null?"":request.getParameter("lastName"))%>" type="text" placeholder="Last Name">
        <br>
        <input name="firstName" value="<%=(request.getParameter("firstName")==null?"":request.getParameter("firstName"))%>" type="text" placeholder="First Name">
        <br>
        <input name="middleName" value="<%=(request.getParameter("middleName")==null?"":request.getParameter("middleName"))%>" type="text" placeholder="Middle Name">
        <br>
        <input name="birthday" value="<%=(request.getParameter("birthday")==null?"":request.getParameter("birthday"))%>" type="date" placeholder="Birthday">
        <br>
        <input name="email" value="<%=(request.getParameter("email")==null?"":request.getParameter("email"))%>" type="email" placeholder="Email">
        <br>
        <input name="salary" value="<%=(request.getParameter("salary")==null?"":request.getParameter("salary"))%>" type="number" placeholder="Salary">
        <br>
        <br>
        <input type="submit">
    </form>
</center>
</body>
</html>
