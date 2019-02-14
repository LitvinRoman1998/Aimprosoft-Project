<%--
  Created by IntelliJ IDEA.
  User: 210818
  Date: 14.02.2019
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<c:import url="head.jsp"></c:import>
<a href="employees?departmentId=<%=request.getParameter("departmentId")%>&departmentName=<%=request.getParameter("departmentName")%>">
    <input type="button" value="Employees"/>
</a>

</body>
</html>
