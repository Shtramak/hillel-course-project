<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<nav class="navbar navbar-expand bg-dark navbar-dark">
    <a href="/" class="navbar-brand">EDUCATION</a>
    <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav">
            <div class="nav-item dropdown">
                <a class="nav-link dropdown-toggle text-light" href="#" data-toggle="dropdown">Create</a>
                <div class="dropdown-menu">
                    <li><a class="dropdown-item" href="/spring/mvc/university/add">University</a></li>
                    <li><a class="dropdown-item" href="#">Student</a></li>
                    <li><a class="dropdown-item" href="/spring/mvc/subject/add">Subject</a></li>
                </div>
            </div>
            <div class="nav-item dropdown">
                <a class="nav-link dropdown-toggle text-light" href="#" data-toggle="dropdown">List</a>
                <div class="dropdown-menu">
                    <li><a class="dropdown-item" href="/spring/mvc/university/list">University</a></li>
                    <li><a class="dropdown-item" href="#">Student</a></li>
                    <li><a class="dropdown-item" href="/spring/mvc/subject">Subject</a></li>
                </div>
            </div>
        </ul>
    </div>
</nav>
</body>
</html>

