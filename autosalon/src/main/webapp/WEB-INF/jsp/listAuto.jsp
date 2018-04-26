<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of Auto</title>
</head>
<body>
    <div align="center">
        <h3>List of Auto</h3>
        <table border="2" cellpadding="5" cellspacing="5">
            <tr>
                <th>Id</th>
                <th>Auto Brand</th>
                <th>Auto Model</th>
                <th>Manufact Year</th>
                <th>Country</th>
                <th>Price</th>
            </tr>
            <c:forEach var="auto" items="${autoList}">
                <tr>
                    <td><a href="${pageContext.request.contextPath}/springmvc/autosalon/auto/id/${auto.id}">${auto.id}</a></td>
                    <td>${auto.brand}</td>
                    <td>${auto.model}</td>
                    <td>${auto.manufactYear}</td>
                    <td>${auto.producerCountry}</td>
                    <td>${auto.price}</td>
                    <td><a href="${pageContext.request.contextPath}/springmvc/autosalon/auto/${auto.id}">Delete</a></td>
                    <td><a href="${pageContext.request.contextPath}/springmvc/autosalon/auto/update/${auto.id}">Edit</a></td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <a href="${pageContext.request.contextPath}/index.jsp">GO TO MAIN PAGE</a>
    </div>
</body>
</html>
