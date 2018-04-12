<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Autosalon</title>
</head>
<body>
<div align="center">
    <h3>Autosalon</h3>
    <table border="2" cellpadding="5" cellspacing="5">
        <tr>
        <td><a href="/autosalon/autosalon/createautosalon">Add new autosalon</a></td>
        <td><a href="/autosalon/autosalon/allAutosalon">Show all autosalons</a></td>
        </tr>
        <tr>
        <td><a href="autosalon/customer/add">Add new customer</a></td>
        <td><a href="autosalon/customer/list">Show all customers</a></td>
        </tr>
        <tr>
        <td><a href="/autosalon/auto/createAuto">Add new auto</a></td>
        <td><a href="/autosalon/auto/listAuto">Show all auto</a></td>
        </tr>
    </table>
</div>
</body>
</html>
