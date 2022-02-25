<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
<link rel="stylesheet" href="static/login.css" />
<link rel="stylesheet" href="static/navbar.css" />
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<nav class="navbar px-3 bg-light"
		style="box-shadow: 1px 1px 3px 1px rgba(128, 128, 128, 0.233)">
		<a class="navbar-brand nav-hover" style="color: black;" href="#">Shop-Wijs</a>
		<ul class="nav">
			<li class="nav-item" id="navbar-signup"><a
				class="nav-link nav-hover" style="color: black;" href="./SignUp.jsp">Sign
					Up</a></li>
			<li class="nav-item" id="navbar-login"><a
				class="nav-link nav-hover" style="color: black;" href="./Login.jsp">Login</a></li>
		</ul>
	</nav>
	<div class="container d-flex flex-column align-items-center">
		<c:if test="${alert != null}">
			<div id="alert" class="alert alert-dark mt-4" role="alert">${alert}</div>
		</c:if>
		<p class="my-4 h1">Login</p>
		<div class="login-container">
			<form action="LoginServlet" method="post">
				<div class="d-flex">
					<section class="aic-right d-flex flex-column">
						<div class="form-group mb-3">
							<label for="">Email</label> <input type="text"
								class="form-control" name="email" required />
						</div>
						<div class="form-group mb-4">
							<label for="">Password</label> <input type="password"
								class="form-control" name="password" required />
						</div>
						<button id="submit" class="btn btn-primary mt-2 mb-0"
							style="background: #FF5D73; color: white">Log In</button>
					</section>
				</div>
			</form>
		</div>
	</div>
</body>
</html>