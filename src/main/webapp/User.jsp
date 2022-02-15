<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
<link rel="stylesheet" href="static/user.css" />
<link rel="stylesheet" href="static/navbar.css" />
<meta charset="ISO-8859-1">
<title>Shop-Wijs</title>
</head>
<body style="font-family: 'Roboto', sans-serif;">
	<jsp:include page="/NavBar.jsp"/>
	<div class="container d-flex flex-column align-items-center">
		<h1 class="mt-5 mb-4 h1">Welcome back, ${userDisplayName}</h1>
		<div class="buttons-container d-flex flex-row w-75">
			<div class="d-flex flex-column w-100 align-items-center">
				<a class="btn btn-primary m-3 w-75" style="background: #FF5D73; color: white; font-size: 18px;" href="http://localhost:8090/devopsproject/ItemsSoldServlet">
					Items Sold</a>
				<a class="btn btn-primary m-3 w-75"
					style="background: #FF5D73; color: white; font-size: 18px;" href="http://localhost:8090/devopsproject/ItemsBoughtServlet">
					Items Bought</a>
				<a class="btn btn-primary m-3 w-75"
					style="background: #FF5D73; color: white; font-size: 18px;" href="http://localhost:8090/devopsproject/AddItem.jsp">
					List New Item</a>
				<a class="btn btn-primary m-3 w-75"
					style="background: #FF5D73; color: white; font-size: 18px;">
					Manage Reviews</a>
				<a class="btn btn-primary m-3 w-75"
					style="background: #FF5D73; color: white; font-size: 18px;" href="http://localhost:8090/devopsproject/ItemsListedServlet">
					Manage Items</a>
			</div>
			<div class="d-flex flex-column w-100 align-items-center">
				<button class="btn btn-primary m-3 w-75"
					style="background: #EF6F6C; color: white; font-size: 18px;">
					Edit Profile</button>
			</div>
		</div>
	</div>
</body>
</html>