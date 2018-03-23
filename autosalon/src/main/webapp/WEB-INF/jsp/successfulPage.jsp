<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success</title>
</head>
<body>
<jsp:useBean id="customer" scope="request" type="com.courses.tellus.autosalon.model.Customer"/>
<h3>Customer ${customer.name} ${customer.surname} successfully added</h3>

<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Birhday</th>
        <th>Phone</th>
        <th>Funds</th>
    </tr>
    <tr>
        <td>
            <c:out value="${customer.id}"/>
        </td>
        <td>
            <c:out value="${customer.name}"/>
        </td>
        <td>
            <c:out value="${customer.surname}"/>
        </td>
        <td>
            <c:out value="${customer.dateOfBirth}"/>
        </td>
        <td>
            <c:out value="${customer.phoneNumber}"/>
        </td>
        <td>
            <c:out value="${customer.availableFunds}"/>
        </td>
    </tr>
</table>
<br>
<jsp:useBean id="allCustomersAfter" scope="request" type="java.util.List"/>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Birhday</th>
        <th>Phone</th>
        <th>Funds</th>
    </tr>
    <c:forEach items="${allCustomersAfter}" var="currCustomer">
        <tr>
            <td>
                <c:out value="${currCustomer.id}"/>
            </td>
            <td>
                <c:out value="${currCustomer.name}"/>
            </td>
            <td>
                <c:out value="${currCustomer.surname}"/>
            </td>
            <td>
                <c:out value="${currCustomer.dateOfBirth}"/>
            </td>
            <td>
                <c:out value="${currCustomer.phoneNumber}"/>
            </td>
            <td>
                <c:out value="${currCustomer.availableFunds}"/>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
