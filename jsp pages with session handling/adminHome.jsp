<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    import="com.hsbc.easset.models.*" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
</head>

<body style="background-color:white">
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
        <a class="navbar-brand" href="/E-AssetManagement/">E-Asset Management</a>
        <!-- Links -->
        <ul class="navbar-nav">
            <li class="nav-item" onclick="changeSection(1)">
                <a class="nav-link" href="#userInfo">User Info</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/E-AssetManagement/addAsset.html">Add Asset</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/E-AssetManagement/addCategory.html">Add Category</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/E-AssetManagement/overdueAsset.html">Over-due Assets</a>
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
</script>
     <% }else
     {%><jsp:forward page="login.jsp">
     <jsp:param name="param1" value="<h1>Session timed out..... Please log back in!!!!</h1>"/>
     </jsp:forward>
     <% }%>
</html>