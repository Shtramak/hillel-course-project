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
                <li><a href="/create/university">New University</a></li>
                <li><a href="/list/universities">All Universities</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="container text-center">
    <hr>
    <form class="form-horizontal" action="/create/university" method="POST">
        <div class="form-group">
            <label class="control-label col-md-3">NameOfUniversity</label>
            <div class="col-md-7">
                <input type="text" class="form-control" name="nameOfUniversity"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3">Address</label>
            <div class="col-md-7">
                <input type="text" class="form-control" name="address" />
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3">Specialization</label>
            <div class="col-md-7">
                <input type="text" class="form-control" name="specialization"/>
            </div>
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-primary" value="Save"/>
        </div>
    </form>
</div>
</body>
</html>