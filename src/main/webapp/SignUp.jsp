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
<link rel="stylesheet" href="static/signUp.css" />
<link rel="stylesheet" href="static/navbar.css" />
<meta charset="ISO-8859-1">
<title>Sign Up</title>
</head>
<body style="font-family: 'Roboto', sans-serif;">
	<nav class="navbar px-3 bg-light"
		style="box-shadow: 1px 1px 3px 1px rgba(128, 128, 128, 0.233)">
		<a class="navbar-brand nav-hover" style="color: black;" href="#">Shop-Wijs</a>
		<ul class="nav">
			<li class="nav-item"><a class="nav-link nav-hover"
				style="color: black;" href="#">Sign Up</a></li>
			<li class="nav-item"><a class="nav-link nav-hover"
				style="color: black;" href="#">Login</a></li>
		</ul>
	</nav>
	<div class="container d-flex flex-column align-items-center">
		{{}}
		<p class="my-4 h1">Sign Up</p>
		<div class="sign-up-container">
			<form action="RegisterServlet" method="post">
				<div class="d-flex">
					<section class="aic-right d-flex flex-column">
						<div class="form-group mb-3">
							<label for="email">Email</label> <input type="text" name="email"
								class="form-control" />
						</div>
						<div class="form-group mb-3">
							<label for="password">Password</label> <input type="password" name="password"
								class="form-control" />
						</div>
						<div class="form-group mb-3">
							<label for="confirmPassword">Confirm Password</label> <input type="password" name="confirmPassword"
								class="form-control" />
						</div>
						<div class="form-group mb-3">
							<label for="displayName">Full Name</label> <input type="text" name="displayName"
								class="form-control" />
						</div>
						<div class="form-group mb-4">
							<label for="phoneNumber">Phone Number</label> <input type="text" name="phoneNumber"
								class="form-control" />
						</div>
						<button class="btn btn-primary"
							style="background: #FF5D73; color: white">Sign Up</button>
					</section>
				</div>
			</form>
		</div>
	</div>
</body>
</html>