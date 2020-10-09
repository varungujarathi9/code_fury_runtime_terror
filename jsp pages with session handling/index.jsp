<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- Custom CSS -->
    <link rel="stylesheet" href="styles/main.css">
    <!-- BOOTSTRAP 4 LIBRARIES -->
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">


    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="resources/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="./script/popularAssets.js"></script>
</head>
<body>
<!-- Session handling -->
<% if(!session.isNew())
{%>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <a class="navbar-brand" href="/E-AssetManagement/">E-Asset Management</a>
        <!-- Links -->
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="/E-AssetManagement/login.html">User Login</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/E-AssetManagement/registration.html">User Registration</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/E-AssetManagement/importUsers.html">Import Users</a>
          </li>
        </ul>

    </nav>
    <br/>
    <div class="container jumbotron">
        <h3>Project Description</h3>
        <p>
            The E-Asset Management system is a web-based application to keep track of several assets that can be borrowed,
            their availability, their current location, the current borrower, and the asset history.
            <br/>
            This website is developed under <i>'Code Fury' project by team <i>'Runtime Terror'</i> under the guidance of HSBC India Technology Academy.

        </p>
        <!-- <a href="/E-AssetManagement/login.html">User Login</a>
        <br/>
        <a href="/E-AssetManagement/registration.html">User Registration</a>
        <br/>
        <a href="/E-AssetManagement/importUsers.html">Import Users</a> -->

        <!-- <section class="popularAssets">
            <h3>List of popular assets:-</h3>
            <aside id="assets">

            </aside>
        </section> -->
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
          <p> © Copyright 2020. Runtime Terrors (WFS-6). All rights reserved.</p>
    </article>
      

  </footer>
  
     <% }else
     {%><jsp:forward page="login.jsp">
     <jsp:param name="param1" value="<h1>Session timed out..... Please log back in!!!!</h1>"/>
     </jsp:forward>
     <% }%>
</body>
</html>