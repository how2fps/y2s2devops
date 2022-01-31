<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Bought</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
<link rel="stylesheet" href="static/itemsBought.css" />
<link rel="stylesheet" href="static/navbar.css" />

</head>
<body>
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
	<div class="d-flex flex-column align-items-center">
		<p class="items-bought-title">Items Bought</p>
		<section
			class="items-bought-container container d-flex flex-column justify-content-center">
			<article class="d-flex flex-row">
				<div class="col d-flex justify-content-center in-title">
					<p>Item Name</p>
				</div>
				<div class="col d-flex justify-content-center dt-title">
					<p>DateTime Bought</p>
				</div>
				<div class="col d-flex justify-content-center a-title">
					<p>Amount</p>
				</div>
			</article>
			<p class="d-flex align-self-center row">You have not bought any
				items yet!</p>
			<c:forEach var="item" items="${itemsBoughtList}">
				<div
					class="item-block d-flex flex-row align-self-center align-items-center">
					<div class="item-info d-flex flex-row align-items-center">
						<img class="item-pic" />
						<div class="amount-item-bought">
							<c:out value="${item.quantity}" />
						</div>
						<c:out value="${item.name}" />
					</div>
					<div
						class="datetime-bought d-flex justify-content-center align-items-center">
						<c:out value="${item.dateListed}" />
					</div>
					<div
						class="amount-bought d-flex justify-content-center align-items-center">
						<c:out value="${item.price}" />
					</div>
				</div>
			</c:forEach>

			<!--<div
				class="item-block d-flex flex-row align-self-center align-items-center">
				<div class="item-info d-flex flex-row align-items-center">
					<img class="item-pic" />
					<p class="amount-item-bought">100</p>
					<p class="item-name">Mini Film Camera</p>
				</div>
				<div
					class="datetime-bought d-flex justify-content-center align-items-center">
					<p>21 December 2021</p>
				</div>
				<div
					class="amount-bought d-flex justify-content-center align-items-center">
					<p>13.50</p>
				</div>
			</div>-->
		</section>
	</div>
</body>
</html>