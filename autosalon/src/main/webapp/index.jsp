<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer</title>
</head>
<body>

<h3>Add customer</h3>

<form method="post" action="${pageContext.request.contextPath}/customer/add">
    <table border="0">
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
        </tr>   <tr>
        <td width="50" align="right">Birthday:</td>
        <td><input type="date" name="birthday"></td>
    </tr>
        <tr>
            <td width="50" align="right">Funds:</td>
            <td><input type="text" name="funds"><br></td>
        </tr>
    </table>
    <input type="submit" value="Submit" name="submitButton">
    <input type="reset" value="Reset">
</form>

</body>
</html>
