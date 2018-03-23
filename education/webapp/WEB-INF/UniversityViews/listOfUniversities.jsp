<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>Form to setting university</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
<body>
<div role="navigation">
    <div class="navbar navbar-inverse">
        <a href="/" class="navbar-brand">Universities</a>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="UniversityServlet?action=addUniversity">New University</a></li>
                <li><a href="UniversityServlet?action=listOfUniversities">All Universities</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container text-center" id="universDiv">
    <h3>Universities</h3>
    <hr>
    <div class="table-responsive">
        <table class="table table-striped table-bordered text-left">
            <thead>
            <tr>
                <th>Id</th>
                <th>NameOfUniversity</th>
                <th>Address</th>
                <th>Specialization</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="university" items="${university}">
                <tr>
                    <td>${university.id}</td>
                    <td>${university.nameOfUniversity}</td>
                    <td>${university.address}</td>
                    <td>${university.specialization}</td>
                    <td><a href="UniversityServlet?action=updateUniversity=${university.id}"><span class="glyphicon glyphicon-pencil"></span></a></td>
                    <td><a href="UniversityServlet?action=deleteUniversity=${university.id}"><span class="glyphicon glyphicon-trash"></span></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</div>
</body>
</html>