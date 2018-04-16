<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Customer by id</title>
</head>
<body>
<div align="center">
    <jsp:useBean id="customer" scope="request" type="com.courses.tellus.autosalon.model.Customer"/>
    <h3>Customer with id = ${customer.id}</h3>
    <table border="2" cellpadding="5" cellspacing="5">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Birhday</th>
            <th>Phone</th>
            <th>Funds</th>
        </tr>
        <tr>
            <td><c:out value="${customer.id}"/></td>
            <td><c:out value="${customer.name}"/></td>
            <td><c:out value="${customer.surname}"/></td>
            <td><c:out value="${customer.dateOfBirth}"/></td>
            <td><c:out value="${customer.phoneNumber}"/></td>
            <td><c:out value="${customer.availableFunds}"/></td>
        </tr>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/index.jsp">BACK TO MAIN PAGE</a>
</div>

</body>
</html>