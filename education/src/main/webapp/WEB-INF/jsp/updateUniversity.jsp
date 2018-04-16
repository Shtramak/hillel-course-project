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
<jsp:include page="in_nav.jsp" />

<div class="container col-6 text-center">
    <hr>

    <form class="form-horizontal" method="POST" action="/university/edit">
        <input type="hidden" readonly name="uniId" value="${university.uniId}"/>
        <div class="form-group">
            <label class="control-label col-md-3" for="nameOfUniversity">NameOfUniversity</label>
            <div class="col-md-12">
                <input type="text" class="form-control" id ="nameOfUniversity" name="nameOfUniversity" value="${university.nameOfUniversity}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3" for="address">Address</label>
            <div class="col-md-12">
                <input type="text" class="form-control" id="address" name="address" value="${university.address}" />
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3" for="specialization">Specialization</label>
            <div class="col-md-12">
                <input type="text" class="form-control" id="specialization" name="specialization" value="${university.specialization}"/>
            </div>
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-primary" value="  Save  "/>
        </div>
    </form>
</div>
</body>
</html>