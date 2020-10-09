<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
</head>
<body>
<!-- Session handling -->
<% if(request.getParameter("param1")!=null)
{
	out.println(request.getParameter("param1"));
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
    <div class="form container">
        <center>
            <header>
                <h2>User Login</h2>
                <h3>E-Asset Management System</h3>
            </header>
        </center>
        <form id="userLogin" method="POST" action="UserLoginController">

            <div class="form-group">
                <label for="userName"><b>Username</b></label>
                <input type="text" name="userName" class="form-control" placeholder="Enter username" required>
            </div>
            <div class="form-group">
                <label for="password"><b>Password</b></label>
                <input type="password" name="password" class="form-control" placeholder="Enter password" required>
            </div>

            <center><input type="submit" id="submit" value="Login"></center>

        </form>
    </div>
<footer class="footer-menu">
<nav>
  <ul>
      <li><a href="#">About Us</a></li>
      <li><a href="#">Contacts</a></li>
      <li><a href="#">  Terms & Condition</a></li>
      <li><a href="#">Privacy Policy</a></li>  
  </ul>
</nav> 
<article>
      <p> � Copyright 2020. Runtime Terrors (WFS-6). All rights reserved.</p>
</article>
</footer>
</body>
</html>