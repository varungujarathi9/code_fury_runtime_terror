<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    import="com.hsbc.easset.models.*" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="./script/addAsset.js"></script>

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
	user=(User)session.getAttribute("userSession");
	if(user.getRole().equals(RoleType.ADMIN))
		{%>
	
	<!--  passing user info to js-->
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
                <h2>Add Asset</h2>
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
        <form name="addAssetForm" id="addAssetForm" method="POST" action="./AddAssetController">
            <div class="form-group">
                <label for="name">Asset Name<p style="color:red;display:inline">*</p></label>
                <input type="text" name="name" class="form-control" id="name" placeholder="Enter Asset Name" required>
                <p id="name_alert" style="color:red;"></p>
            </div>

            <div class="form-group">
                <label for="category">Asset Category<p style="color:red;display:inline">*</p></label>
                <select id="category" class="form-control" name="category" required>
                    <option value="">-Select Category-</option>
                    <option value="Laptop">Laptop</option>
                    <option value="Mobile">Mobile</option>
                    <option value="Other">Other</option>
                    <p id="category_alert" style="color:red;"></p>
                </select>
            </div>

            <div class="form-group">
                <label for="description">Description<p style="color:red;display:inline">*</p></label>
                <textarea class="form-control" rows="5" name="description" class="form-control" id="description"
                    placeholder="Enter Description about Asset" required></textarea>
                <p id="description_alert" style="color:red;"></p>
            </div>

            <div id="addCategory" style="display: none">
                <!-- <submit>Add Category</submit>-->
                <input type="button" id="submitBtn" class="btn1" value="Add Category">
                <p id="addCategory_alert" style="color:red;"></p>
            </div>

            <center><input type="submit" id="submitBtn" class="btn" onclick="addAsset()" value="Add Asset"></center>
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
              <p> © Copyright 2020. Runtime Terrors (WFS-6). All rights reserved.</p>
        </article>
          
        
     </footer>
     <%}
     else
     {%>
    	 <jsp:forward page="login.jsp">
         <jsp:param name="param0" value="<h1>Unauthorized Access..... Please log back in!!!!</h1>"/>
         </jsp:forward>
     <%}
      }else
     {%><jsp:forward page="login.jsp">
     <jsp:param name="param1" value="<h1>Session timed out..... Please log back in!!!!</h1>"/>
     </jsp:forward>
     <% }%>
</body>
</html>