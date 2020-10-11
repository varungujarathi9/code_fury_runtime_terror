<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Login</title>
    <script type="text/javascript" src="./script/login.js"></script>

    <!-- BOOTSTRAP 4 LIBRARIES -->
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="styles/main.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="resources/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript"> window.history.forward(); function noBack() { alert("Back button is restricted"); window.history.forward(); } </script> 

</head>

<body>
<!-- Session handling -->
<% if(request.getParameter("param1")!=null)
{
	//out.println(request.getParameter("param1"));
}
if(request.getParameter("param0")!=null)
{
	//out.println(request.getParameter("param1"));
}%>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <a class="navbar-brand" href="/E-AssetManagement/">E-Asset Management</a>
        <!-- Links -->
        <!-- <ul class="navbar-nav">
              <li class="nav-item">
                <a class="nav-link" href="#">Link 1</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">Link 2</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">Link 3</a>
              </li>
            </ul> -->

    </nav>
    <br />
    <div class="form container" style="width:30%">
        <center>
            <header>
                <h2>Sign In</h2>
            </header>
        </center>
        <form name="login_form" id="login_form" method="POST" action="UserLoginController">

            <div class="form-group">
                <label for="userName"><b>Username</b></label>
                <input type="text" name="userName" class="form-control" placeholder="Enter username" required>
            </div>
            <div class="form-group">
                <label for="password"><b>Password</b></label>
                <input type="password" name="password" class="form-control" placeholder="Enter password" required>
            </div>

            <center><input type="button" class="btn" id="submit" onclick="login()" value="Login"></center>
            <p id="form_alert" style="color:red;"></p>
        </form>
    </div>
    <footer class="footer-menu">
        <ul>
            <li><a href="teamPage.html">About Us</a></li>
            <li><a href="teamPage.html">Contacts</a></li>
            <li><a href="#">Terms & Condition</a></li>
            <li><a href="#">Privacy Policy</a></li>
            <li class="copyright">
                <p>&copy; Copyright 2020. Runtime Terror (WFS-6 Team 1). All rights reserved.</p>
            </li>
        </ul>
    </footer>
</body>

</html>