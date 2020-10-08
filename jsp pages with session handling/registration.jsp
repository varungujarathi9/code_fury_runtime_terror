<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="./script/registration.js"></script>

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
</head>
<body>
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
    <br/>
    <div class="form container">
        <center>
            <header>
              <h2>User Registration</h2>
              <h3>E-Asset Management System</h3><!--TODO:text colored background black-->
            </header>
          </center>
          <div class="instructions">
            <strong><h3 align="center">Instructions for filling form</h3></strong>
            <ol type="1">
                <li>All fields are mandatory *</li>
                <li>Email & Phone should be valid & working</li>
                <li>Password should be strong enough</li>
                <li>You can your email as username</li>
            </ol>
          </div>
        <form name="reg_form" id="reg_form" method="POST" action="UserRegistrationController">

                <div class="form-group">
                    <label for="name">Full Name<p style="color:red;display:inline">*</p></label>
                    <input type="text" name="name" class="form-control" id="name" placeholder="Enter your full name" required>
                    <p id="name_alert" style="color:red;"></p>
                </div>

                <div class="form-group">
                    <label for="telephone">Mobile Number<p style="color:red;display:inline">*</p></label>
                    <input type="tel" name="telephone" class="form-control" id="number" placeholder="Enter your mobile Number (10 Digits)" maxlength=10 size=10 pattern="[0-9]{10}" required>
                    <p id="telephone_alert" style="color:red;"></p>
                </div>

                <div class="form-group">
                    <label for="email">Email Address<p style="color:red;display:inline">*</p></label>
                    <input type="email" name="email" class="form-control" id="email" placeholder="Enter your email address" required>
                    <p id="email_alert" style="color:red;"></p>
                </div>

                <div class="form-group">
                    <label for="username">Username<p style="color:red;display:inline">*</p>
                      <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="custom-control-input" id="customCheck" name="useEmail" value="useEmail" onclick="usernameSelector()">
                        <label class="custom-control-label" for="customCheck">Select this to use email as username</label>
                      </div>
                    </label>
                    <input type="text" name="username" class="form-control" id="username" placeholder="Enter your username" required>
                    <p id="username_alert" style="color:red;"></p>
                </div>

                <div class="form-group">
                    <label for="password">Password<p style="color:red;display:inline">*</p></label>
                    <input type="password" name="password" class="form-control" placeholder="Enter a strong password" id="password" placeholder="" required>
                    <p id="password_alert" style="color:red;"></p>
                </div>

                <div class="form-group">
                    <label for="conf_password">Confirm Password<p style="color:red;display:inline">*</p></label>
                    <input type="password" name="conf_password" class="form-control" id="conf_password" placeholder="Re-enter password to confirm" required>
                    <p id="conf_password_alert" style="color:red;"></p>
                </div>

                <center><input type="submit" id="submitBtn" class="btn" value="Register"></center>

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
</body>
</html>