<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Create Autosalon</title>
</head>
<body>
<div align="center">
    <h3>Create Autosalon</h3>
    <form method="POST" action="/CreateAutosalon">
        <table border="2" cellpadding="5" cellspacing="5">
            <tr>
                <td>salon Name: </td>
                <td><input type="text" name="name" value="${autosalon.name}"></td>
            </tr>
            <tr>
                <td>Autosalon Address:</td>
                <td><input type="text" name="address" value="${autosalon.address}"></td>
            </tr>
            <tr>
                <td>Autosalon Telephone:</td>
                <td><input type="text" name="telephone" value="${autosalon.telephone}"></td>
            </tr>
            <tr>
                <td colspan="3" align="center">
                    <input type="submit" value="Submit">
                    <a href="/productList">Cancel</a>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
