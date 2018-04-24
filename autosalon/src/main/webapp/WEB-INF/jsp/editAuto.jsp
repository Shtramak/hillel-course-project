<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Create Auto</title>
</head>
<body>
<div align="center">
    <h3>Edit Auto By Id ${auto.id}</h3>
    <form method="post" action="${pageContext.request.contextPath}/springmvc/autosalon/auto/update">
        <table border="2" cellpadding="5" cellspacing="5">
            <tr>
                <td>Id: </td>
                <td><input type="text" name="id" value="${auto.id}"></td>
            </tr>
            <tr>
                <td>Auto Brend: </td>
                <td><input type="text" name="brand" value="${auto.brand}"></td>
            </tr>
            <tr>
                <td>Auto Model:</td>
                <td><input type="text" name="model" value="${auto.model}"></td>
            </tr>
            <tr>
                <td>Manufact Year:</td>
                <td><input type="text" name="manufactYear" value="${auto.manufactYear}"></td>
            </tr>
            <tr>
                <td>Produce Country:</td>
                <td><input type="text" name="producerCountry" value="${auto.producerCountry}"></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><input type="text" name="price" value="${auto.price}"></td>
            </tr>
            <tr>
                <td colspan="3" align="center">
                    <input type="submit" value="Submit">
                    <a href="${pageContext.request.contextPath}/springmvc/autosalon/auto/list">Cancel</a>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
