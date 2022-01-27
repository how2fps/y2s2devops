<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>hi</h1>
	<h1>Hello JSP and Servlet!</h1>
	
	<form action="UserServlet" method="post">
		<label>Enter your name: </label>
		<input type="text" name="yourName" size="20">
		<input type="submit" value="Call Servlet" />
	</form>
</body>
</html>