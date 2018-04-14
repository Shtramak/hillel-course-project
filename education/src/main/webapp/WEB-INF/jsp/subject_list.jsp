<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
    <head>
        <meta charset="utf-8">
        <title>EDUCATION - Subject list</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <jsp:include page="_nav.jsp" />
        </div>
            <div class="container text-center">
                <h3>Subjects list</h3>
                <p class="text-warning">${emptydb}</p>
                <p class="text-danger">${error}</p>
                <hr>
                <div class="table-responsive">
                    <table class="table table-striped table-bordered text-left">
                        <thead class="text-center">
                        <tr>
                            <th>Subject id</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Valid</th>
                            <th>Date of creation</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="subject" items="${subjectList}">
                            <tr>
                                <td>${subject.subjectId}</td>
                                <td>${subject.name}</td>
                                <td>${subject.description}</td>
                                <td>${subject.valid}</td>
                                <td>${subject.dateOfCreation}</td>
                                <td><a href="subject/edit/${subject.subjectId}">
                                    <span class="fa fa-pencil text-center"></span></a>
                                </td>
                                <td><a href="subject/delete/${subject.subjectId}">
                                    <span class="fa fa-trash text-center"></span></a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>