<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Autosalon</title>
</head>
<body>
<div align="center">
    <h3>Spring MVC Autosalon</h3>
    <table border="2" cellpadding="5" cellspacing="5">
        <tr>
            <td><a href="${pageContext.request.contextPath}/springmvc/autosalon/autosalon/createautosalon">Add new autosalon</a></td>
            <td><a href="${pageContext.request.contextPath}/springmvc/autosalon/autosalon/allAutosalon">Show all autosalons</a></td>
        </tr>
        <tr>
            <td><a href="${pageContext.request.contextPath}/springmvc/autosalon/auto/create">Add new auto</a></td>
            <td><a href="${pageContext.request.contextPath}/springmvc/autosalon/auto/list">Show all auto</a></td>
        </tr>
        <tr>
            <td><a href="${pageContext.request.contextPath}/springmvc/autosalon/customer/add">Add new customer</a></td>
            <td><a href="${pageContext.request.contextPath}/springmvc/autosalon/customer/list">Show all customers</a></td>
        </tr>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/index-servlets.jsp">GO TO SERVLET MAIN PAGE</a>
</div>
</body>
</html>
