<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Autosalon</title>
</head>
<body>
<div align="center">
    <h3>Hello from Autosalon</h3>
    <table border="2" cellpadding="5" cellspacing="5">
        <td><a href="/autosalon/autosalon/CreateAutosalon">Add New Autosalon</a></td>
        <td><a href="/autosalon/autosalon/allAutosalon">Show All Autosalon</a></td>
        <a href="autosalon/customer/add">ADD CUSTOMER</a><p>
        <a href="autosalon/customer/list">LIST OF CUSTOMERS</a>
    </table>
</div>
</body>
</html>
