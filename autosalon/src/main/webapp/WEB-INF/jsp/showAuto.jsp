<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of Auto</title>
</head>
<body>
<div align="center">
    <h3>Auto by id ${auto.id}</h3>
    <table border="2" cellpadding="5" cellspacing="5">
        <tr>
            <th>Id</th>
            <th>Auto Brand</th>
            <th>Auto Model</th>
            <th>Manufact Year</th>
            <th>Country</th>
            <th>Price</th>
        </tr>
        <tr>
            <td>${auto.id}</td>
            <td>${auto.brand}</td>
            <td>${auto.model}</td>
            <td>${auto.manufactYear}</td>
            <td>${auto.producerCountry}</td>
            <td>${auto.price}</td>
        </tr>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/springmvc/autosalon/auto/list">GO TO LIST OF AUTO</a>
</div>
</body>
</html>
