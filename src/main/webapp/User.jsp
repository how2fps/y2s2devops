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
<title>Login</title>
</head>
<body style="font-family: 'Roboto', sans-serif;">
	<nav class="navbar px-3 bg-light"
		style="box-shadow: 1px 1px 3px 1px rgba(128, 128, 128, 0.233)">
		<a class="navbar-brand nav-hover" style="color: black;" href="#">Shop-Wijs</a>
		<ul class="nav">
			<li class="nav-item"><a class="nav-link nav-hover"
				style="color: black;" href="#">Shop</a></li>
			<li class="nav-item"><a class="nav-link nav-hover"
				style="color: black;" href="#">Your Cart</a></li>
			<li class="nav-item"><a class="nav-link nav-hover"
				style="color: black;" href="#">Profile</a></li>
			<li class="nav-item"><a class="nav-link nav-hover"
				style="color: black;" href="#">Logout</a></li>
		</ul>
	</nav>
	<div class="container d-flex flex-column align-items-center">
		<h1 class="mt-5 mb-4 h1">Welcome back, User1</h1>
		<div class="buttons-container d-flex flex-row w-75">
			<div class="d-flex flex-column w-100 align-items-center">
				<button class="btn btn-primary m-3 w-75"
					style="background: #FF5D73; color: white; font-size: 18px;">
					Items Sold</button>
				<button class="btn btn-primary m-3 w-75"
					style="background: #FF5D73; color: white; font-size: 18px;">
					Items Bought</button>
				<button class="btn btn-primary m-3 w-75"
					style="background: #FF5D73; color: white; font-size: 18px;">
					List New Item</button>
				<button class="btn btn-primary m-3 w-75"
					style="background: #FF5D73; color: white; font-size: 18px;">
					Manage Reviews</button>
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