<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<html>
<head>
    <title>Success</title>
</head>
<body>
<div align="center">
    <h3>List of customers</h3>
    <jsp:useBean id="customers" scope="request" type="java.util.List"/>
    <table border="2" cellpadding="5" cellspacing="5">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Birhday</th>
            <th>Phone</th>
            <th>Funds</th>
        </tr>
        <c:forEach items="${customers}" var="currCustomer">
            <tr>
                <td><c:out value="${currCustomer.id}"/></td>
                <td><c:out value="${currCustomer.name}"/></td>
                <td><c:out value="${currCustomer.surname}"/></td>
                <td><c:out value="${currCustomer.dateOfBirth}"/></td>
                <td><c:out value="${currCustomer.phoneNumber}"/></td>
                <td><c:out value="${currCustomer.availableFunds}"/></td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/index.jsp">BACK TO MAIN PAGE</a>
</div>
</body>
</html>
