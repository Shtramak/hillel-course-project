<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
    <head>
		<meta charset="utf-8">
		<title>EDUCATION - Subject edit</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </head>
    <body>
		<jsp:include page="in_nav.jsp" />
		<p style="color: red">${error}</p>
		<div  class="container-fluid">
			<form class="form" method="POST" action="/update/subject">
				<!-- id -->
				<div class="form-group col-6 mx-auto mt-5">
					<label class="control-label col-2 label-inline" for="subjectId">SubjectID</label>
					<div class="col-10">
						<input type="text" readonly class="form-control-plaintext" id="subjectId" name="subjectId"
							   value="${subjectId}">
					</div>
				</div>
				<!-- name -->
				<div class="form-group col-6 mx-auto">
					<label class="control-label col-9 text-center" for="name">Subject name</label>
					<div class="col-10">
						<input type="text" class="form-control" id="name" name="name" value="${name}"
							   placeholder="Input your name">
					</div>
				</div>
				<!-- description -->
				<div class="form-group col-6 mx-auto">
					<label class="control-label col-9 text-center" for="description">Description</label>
					<div class="col-10">
						<textarea class="form-control" id="description" name="description">${description}</textarea>
					</div> 
				</div>
				<!-- valid -->
				<div class="form-group col-6 mx-auto">
					<label class="control-label col-9 text-center">Valid</label>
					<div class="col-6 mx-auto">
						<label class="radio-inline">
							<input required type="radio" name="activeRadios" value="Y"
								   <c:if test="${valid==true}">checked</c:if>> Active
						</label>
						<label class="radio-inline">
							<input required type="radio" name="activeRadios" value="N"
								   <c:if test="${valid==false}">checked</c:if>> Deprecated
						</label>
					</div>
				</div>
				<!-- date_of_creation -->
				<div class="form-group col-9 mx-auto">
					<label class="control-label col-10 text-center">Date of creation</label>
					<div class="form-inline col-10">
							<div class="col-4">
								<label class="control-label col-4 mx-auto" for="day">Day</label>
								<div class="col-4">
									<input type="text" class="form-control form-inline" id="day" name="day"
										   value="${day}" placeholder="DD">
								</div>
							</div>
							<div class="col-4">
								<label class="control-label col-4 mx-auto" for="month">Month</label>
								<div class="col-4">
									<input type="text" class="form-control" id="month" name="month" value="${month}"
										   placeholder="MM">
								</div>
							</div>
							<div class="col-4">
								<label class="control-label col-4 mx-auto" for="year">Year</label>
								<div class="col-4">
									<input type="text" class="form-control" id="year" name="year" value="${year}"
										   placeholder="YYYY">
							</div>
						</div>						
					</div>
				</div>
				<br />
				<!-- button -->
				<div class="form-group col-10 mx-auto">
				<div class="col-4 mx-auto">
					<input type="submit" class="btn btn-primary" value="Update">
					<a href="/list/subject"><input type="button" class="btn btn-outline-danger" value="Cancel"></a>
				</div>
			</div>
			</form>
		</div>
    </body>
</html>