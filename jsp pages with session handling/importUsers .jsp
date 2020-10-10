<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    import="com.hsbc.easset.models.*" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<p id="status"></p>
    <script type="text/javascript" src="./script/importUsers.js"></script>
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
    <form id="importUsers" method="POST" action="FileUpload"enctype="multipart/form-data">
        <fieldset>
            <legend>Import Users (select json/xml file):-</a></legend>
                <label for="myfile">Select a file:</label>
                <input type="file" id="myfile" name="myfile" accept=".xml,.json,|text/*"><br><br>
                <input type="submit" id="submit"  value="Import">
        </fieldset>
      </form>
</body>
     <% }else
     {%><jsp:forward page="login.jsp">
     <jsp:param name="param1" value="<h1>Session timed out..... Please log back in!!!!</h1>"/>
     </jsp:forward>
     <% }%>
</html>