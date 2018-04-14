<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
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
		<jsp:include page="_nav.jsp" />
		<div  class="container-fluid">
			<form class="form" method="POST" action="/subject/edit">
				<!-- id -->
				<div class="form-group col-6 mx-auto mt-5">
					<label class="control-label col-2 label-inline" >SubjectID</label>
					<div class="col-10">
						<input type="text" readonly class="form-control-plaintext" name="subjectId"
							   value="${subject.subjectId}">
					</div>
				</div>
				<!-- name -->
				<div class="form-group col-6 mx-auto">
					<label class="control-label col-9 text-center" >Subject name</label>
					<div class="col-10">
						<input type="text" class="form-control" name="name" value="${subject.name}"
							   placeholder="Input your name">
					</div>
				</div>
				<!-- description -->
				<div class="form-group col-6 mx-auto">
					<label class="control-label col-9 text-center" >Description</label>
					<div class="col-10">
						<textarea class="form-control" name="description">${subject.description}</textarea>
					</div> 
				</div>
				<!-- valid -->
				<div class="form-group col-6 mx-auto">
					<label class="control-label col-9 text-center">Valid</label>
					<div class="col-6 mx-auto">
						<label class="radio-inline">
							<input required type="radio" name="valid" value="true"
								   <c:if test="${subject.valid==true}">checked</c:if>> Active
						</label>
						<label class="radio-inline">
							<input required type="radio" name="valid" value="true"
								   <c:if test="${subject.valid==false}">checked</c:if>> Deprecated
						</label>
					</div>
				</div>
				<!-- date_of_creation -->
				<div class="form-group col-9 mx-auto">
					<label class="control-label col-10 text-center">Date of creation</label>
					<div class="form-inline col-10">
						<div class="col-4">
							<label class="control-label col-4 mx-auto">Date</label>
							<div class="col-4">
								<input type="date" class="form-control form-inline" name="dateOfCreation"
									   value="${subject.dateOfCreation}" />
							</div>
						</div>
					</div>
				</div>
				<br />
				<!-- button -->
				<div class="form-group col-10 mx-auto">
				<div class="col-4 mx-auto">
					<input type="submit" class="btn btn-primary" value="Update">
					<a href="/subject"><input type="button" class="btn btn-outline-danger" value="Cancel"></a>
				</div>
			</div>
			</form>
		</div>
    </body>
</html>