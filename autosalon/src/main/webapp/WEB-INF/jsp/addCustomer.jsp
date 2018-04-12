<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<html>
<head>
    <title>Customer</title>
</head>
<body>

<div align="center">
    <h3>Add customer</h3>
    <form method="post" action="${pageContext.request.contextPath}/springmvc/autosalon/customer/add">
        <table border="2" cellpadding="5" cellspacing="5">
            <tr>
                <td width="50" align="right">id:</td>
                <td><input type="text" name="id"></td>
            </tr>
            <tr>
                <td width="50" align="right">Name:</td>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <td width="50" align="right">Surname:</td>
                <td><input type="text" name="surname"></td>
            </tr>
            <tr>
                <td width="50" align="right">Phone:</td>
                <td><input type="text" name="phone"><br></td>
            </tr>
            <tr>
                <td width="50" align="right">Birthday:</td>
                <td><input type="date" name="birthday"></td>
            </tr>
            <tr>
                <td width="50" align="right">Funds:</td>
                <td><input type="text" name="funds"><br></td>
            </tr>
        </table>
        <br>
        <input type="submit" value="Submit" name="submitButton">
        <input type="reset" value="Reset">
    </form>
    <br>
    <a href="${pageContext.request.contextPath}/index.html">BACK TO MAIN PAGE</a>
</div>
</body>
</html>
