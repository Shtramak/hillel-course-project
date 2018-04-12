<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>Free Cars-Online</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href='http://fonts.googleapis.com/css?family=Playball' rel='stylesheet' type='text/css'>
    <!--slider-->
    <script src="js/jquery.min.js"></script>
    <script src="js/script.js" type="text/javascript"></script>
    <script src="js/superfish.js"></script>
</head>
<body>
<div class="about_wrapper"><h1></h1>
</div>
<div class="section group">
    <div class="col span_2_of_c">
        <div class="contact-form">
            <h3>Login Form</h3>
            <form method="post" name= "autosalonAutorization" action="">
                <div>
                    <span><label>Login</label></span>
                    <span><input name="login" type="text" class="textbox"></span>
                    ${autosalon.name}
                </div>
                <div>
                    <span><label>Password</label></span>
                    <span><input name="password" type="password" class="textbox"></span>
                    ${autosalon.telephone}
                </div>
                </div>
                <div>
                    <span><input type="submit" value="Submit"></span>
                </div>
            </form>
        </div>
    </div>
    <div class="clear"></div>
</div>
</body>
</html>