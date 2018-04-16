<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>EDUCATION - Universities</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="in_nav.jsp" />
<div class="container text-center col-10" id="universityDiv">
    <br>
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
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="university" items="${universityList}">
                <tr>
                    <td>${university.uniId}</td>
                    <td>${university.nameOfUniversity}</td>
                    <td>${university.address}</td>
                    <td>${university.specialization}</td>
                    <td><a href="/university/edit/${university.uniId}"><span class="fa fa-pencil text-center"></span></a></td>
                    <td><a href="/university/delete/${university.uniId}"><span class="fa fa-trash text-center"></span></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</div>

</body>
</html>