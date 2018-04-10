<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <li><a href="/addUniversity">New University</a></li>
                <li><a href="/getAllUniversities">All Universities</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="container text-center">
    <hr>

    <form class="form-horizontal" method="POST" action="/updateUniversity">
        <input type="hidden" readonly name="uniId" value="${university.uniId}"/>
        <div class="form-group">
            <label class="control-label col-md-3" for="nameOfUniversity">NameOfUniversity</label>
            <div class="col-md-7">
                <input type="text" class="form-control" id ="nameOfUniversity" name="nameOfUniversity" value="${university.nameOfUniversity}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3" for="address">Address</label>
            <div class="col-md-7">
                <input type="text" class="form-control" id="address" name="address" value="${university.address}" />
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3" for="specialization">Specialization</label>
            <div class="col-md-7">
                <input type="text" class="form-control" id="specialization" name="specialization" value="${university.specialization}"/>
            </div>
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-primary" value="Save"/>
        </div>
    </form>
</div>
</body>
</html>