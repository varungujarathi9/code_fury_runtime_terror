<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    import="com.hsbc.easset.models.*" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script type="text/javascript" src="./script/importUsers.js"></script>
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
<%!User user=null; %>
<!-- Session handling -->
<% if(!session.isNew())
{
	//passing user info to js
	user=(User)session.getAttribute("userSession");%>
	<input type="text" id="jspName" value=<%=user.getName()%>>
	<input type="text" id="jspTelephoneNumber" value=<%=user.getTelphoneNumber()%>>
	<input type="text" id="jspRole" value=<%=user.getRole()%>>
	<input type="text" id="jspEmailId" value=<%=user.getEmailId()%>>
	<input type="text" id="jspUsername" value=<%=user.getUsername()%>>
	<input type="text" id="jspPassword" value=<%=user.getPassword()%>>
	<input type="text" id="jspLastLogin" value=<%=user.getLastLogin()%>>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <a class="navbar-brand" href="/E-AssetManagement/"  >E-Asset Management</a>

    </nav>
    <br />
    <br />
    <div class="form container" style="width:33%">
        <center>
            <header>
                <h2>Import Users</h2>
            </header>
        </center>
        <div class="instructions" style="padding: 10px;">
            <h5 align="center">(select json/xml file) :</h5>
          </div>
        <form id="importUser" method="POST" action="FileUpload"enctype="multipart/form-data">
            <div class="form-group1 ">
                <label for="myfile">Select a file:</label>
                <input type="file" id="myfile" name="myfile" accept=".xml,.json,|text/*"><br><br>
                <input type="submit" id="submit"  value="Import">
            </div>
        </form>
        </div>
        <footer class="footer-menu1">
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

     <% }else
     {%><jsp:forward page="login.jsp">
     <jsp:param name="param1" value="<h1>Session timed out..... Please log back in!!!!</h1>"/>
     </jsp:forward>
     <% }%>
</body>
</html>