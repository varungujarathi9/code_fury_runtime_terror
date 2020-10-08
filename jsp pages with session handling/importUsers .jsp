<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<p id="status"></p>
    <script type="text/javascript" src="./script/importUsers.js"></script>
</head>
<body>
<!-- Session handling -->
<% if(!session.isNew())
{%>
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