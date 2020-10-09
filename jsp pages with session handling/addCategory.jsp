<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="./script/addCategory.js"></script>

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
<% if(!session.isNew())
{%>
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
                <h2>Add Category</h2>
                <h3>E-Asset Management System</h3>
            </header>
        </center>
        <div class="instructions">
            <strong>
                <h3 align="center">Instructions for filling form</h3>
            </strong>
            <ol type="1">
                <!-- TODO -->
                <li>ADD INSTRUCTION ACCORDING TO BUSINESS RULE</li>
            </ol>
        </div>
        <form name="addCategoryForm" id="addCategoryForm" method="POST" action="AddCategoryController">

            <div class="form-group">
                <label for="name">Category Name<p style="color:red;display:inline">*</p></label>
                <input type="text" class="form-control" name="name" placeholder="Enter category name" id="name"
                    required>
                <p id="name_alert" style="color:red;"></p>
            </div>

            <div class="form-group">
                <label for="lendingPeriod">Lending Period (in days)<p style="color:red;display:inline">*</p></label>
                <input type="number" class="form-control" name="lendingPeriod" placeholder="Enter the lending period"
                    id="lendingPeriod" min="1" required>
                <p id="lendingPeriod_alert" style="color:red;"></p>
            </div>

            <div class="form-group">
                <label for="lateFees">Late Fees (per day)<p style="color:red;display:inline">*</p></label>
                <input type="number" class="form-control" name="lateFees"
                    placeholder="Enter the late fees for the category" id="lateFees" required>
                <p id="lateFees_alert" style="color:red;"></p>
            </div>

            <center><input type="submit" id="submitBtn" class="btn" value="Add Category"></center>
            <p id="form_alert" style="color:red;"></p>

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
     <% }else
     {%><jsp:forward page="login.jsp">
     <jsp:param name="param1" value="<h1>Session timed out..... Please log back in!!!!</h1>"/>
     </jsp:forward>
     <% }%>
</body>
</html>