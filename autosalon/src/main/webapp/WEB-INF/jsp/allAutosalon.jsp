<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of Autosalon</title>
</head>
<body>
<div align="center">
    <h3>List of Autosalon</h3>
    <table border="2" cellpadding="5" cellspacing="5">
        <tr>
            <th>Id</th>
            <th>Autosalon Name</th>
            <th>Autosalon Address</th>
            <th>Autosalon Telephone</th>
        </tr>
        <c:forEach var="autosalon" items="${autosalon}">
            <tr>
                <td>${autosalon.id}</td>
                <td>${autosalon.name}</td>
                <td>${autosalon.address}</td>
                <td>${autosalon.telephone}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
