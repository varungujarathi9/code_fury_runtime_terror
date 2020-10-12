<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    import="com.hsbc.easset.models.*" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- <script src="script/getUserInfo.js"></script> -->
    <!-- <script src="script/getBorrowedAssets.js"></script> -->
    <title>Home Page</title>

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

<body style="background-color:white">
<%!User user=null; %>
<!-- Session handling -->
<% if(!session.isNew())
{
	//passing user info to js
	user=(User)session.getAttribute("userSession");
	if( user != null && user.getRole().equals(RoleType.ADMIN))
	{%>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <a class="navbar-brand" href="/E-AssetManagement/">E-Asset Management</a>
        <!-- Links -->
        <ul class="navbar-nav">
            <li class="nav-item" onclick="changeSection(1)">
                <a class="nav-link" href="#userInfo">User Info</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/E-AssetManagement/addAsset.jsp">Add Asset</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/E-AssetManagement/addCategory.jsp">Add Category</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/E-AssetManagement/overdueAsset.jsp">Over-due Assets</a>
            </li>
            <li class="nav-item" onclick="changeSection(2)">
                <a class="nav-link" href="#reports">Report</a>
            </li>
        </ul>

    </nav>
    <br />
    <div class="container">
        <center>
            <!-- shows user information assets -->
            <div id="userInfo">
                <div class="card" style="width:400px">
                    <img class="card-img-top" src="images/img_avatar1.png" alt="Card image">
                    <div class="card-body">
                        <h4 class="card-title" id="userName">No name</h4>
                        <p class="card-text">No info</p>
                        <!-- <a href="#" class="btn btn-primary">See Profile</a> -->
                    </div>
                </div>
            </div>

            <!-- shows all overdue messages -->
            <div id="reports" style="display: none;">
                <div class="card bg-danger text-white">
                    <div class="card-body">No report to display</div>
                </div>
            </div>
        </center>
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
<script lang="javascript">
    function changeSection(sectionId) {
        var userInfoDiv = document.getElementById("userInfo");
        var reportsDiv = document.getElementById("reports");

        userInfoDiv.style.display = "none";
        reportsDiv.style.display = "none";

        switch (sectionId) {
            case 1:
                userInfoDiv.style.display = "block";
                break;
            case 2:
                reportsDiv.style.display = "block";
                break;
            default:
                alert("Msg to developer: Update javascript code base");
                break;
        }
    }

<%}
     else
     {
     	session.invalidate();
%>
		<script lang="javascript">alert("Unauthorized Access")</script>
    	<jsp:forward page="login.jsp">
        <jsp:param name="param0" value="<h1>Unauthorized Access..... Please log back in!!!!</h1>" />
    	</jsp:forward>
    <%
    }
}
     else
     {%>
     <script lang="javascript">alert("Session Timed Out")</script>
     	<jsp:forward page="login.jsp">
       	<jsp:param name="param1" value="<h1>Session timed out..... Please log back in!!!!</h1>" />
    	</jsp:forward>
    <%}%>
     </script>
</html>