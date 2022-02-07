<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
<link rel="stylesheet" href="static/userTransaction.css" />
<title>Transaction</title>
</head>
<body>
<nav class="navbar px-3 bg-light"
		style="box-shadow: 1px 1px 3px 1px rgba(128, 128, 128, 0.233)">
		<a class="navbar-brand nav-hover" style="color: black;" href="#">Shop-Wijs</a>
		<ul class="nav">
			<li class="nav-item"><a class="nav-link nav-hover"
				style="color: black;" href="http://localhost:8090/devopsproject/ItemsShopServlet">Shop</a></li>
			<li class="nav-item"><a class="nav-link nav-hover"
				style="color: black;" href="http://localhost:8090/devopsproject/UserCartServlet">Your Cart</a></li>
			<li class="nav-item"><a class="nav-link nav-hover"
				style="color: black;" href="#">Profile</a></li>
			<li class="nav-item"><a class="nav-link nav-hover"
				style="color: black;" href="#">Logout</a></li>
		</ul>
	</nav>
	<br>
	<div class="col d-flex justify-content-center in-title">
		<h1 class="fontcenterTransaction">Transaction</h1>
	</div>
	<br>
	<div class="col d-flex justify-content-center">
		<p class="fontcenterMessageTransaction">You have successfully made
			recent purchases today!</p>
	</div>
		<div class="divcenter">
			<img width=450 height=450 src="images/checkmarksuccesstransaction.png">
		</div>
	<div class="col-md-12 text-center">
		<a href="http://localhost:8090/devopsproject/ItemsShopServlet" class="btn btn-danger transactionitembutton">Go To
			Shop</a>
	</div>
</body>
</html>