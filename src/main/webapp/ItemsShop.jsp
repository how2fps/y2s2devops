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
<link rel="stylesheet" href="static/itemsShop.css" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Items Shop</title>
</head>
<body>
	<nav class="navbar px-3 bg-light"
		style="box-shadow: 1px 1px 3px 1px rgba(128, 128, 128, 0.233)">
		<a class="navbar-brand nav-hover" style="color: black;" href="#">Shop-Wijs</a>
		<ul class="nav">
			<li class="nav-item"><a class="nav-link nav-hover"
				style="color: black;">Shop</a></li>
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
		<h1 class="fontcenterTransaction">Shop</h1>
	</div>
	<br>
	<br>
	<form action="ItemsShopServlet" method="post">
	<div class="cards d-flex justify-content-center">
		<c:forEach var="item" items="${itemsShopList}">
			<div class="card">
				<img class="card-img-top" width=100px height=200px
					src="${item.image}" alt="Item Image">
				<div class="card-body">
					<h5 class="shopitemtitle">
						<c:out value="${item.name}" />
					</h5>
					<p class="card-text shoppricemargin">
						<c:out value="${item.pricing}" />
					<div class="col-md-12 text-center">
						<a href="#" class="btn btn-danger shopitembutton">Reviews</a>
					</div>
					<div class="col-md-12 text-center">
						<div style="display:none">
						<%-- <input type="text" name="id" value="${item.id}" style="display_none"> --%>
						<input type="text" name="shoppingcartid" value="${item.id}" style="display_none">
						<input type="text" name="itemid" value="${item.id}" style="display_none">
						<input type="text" name="pricing" value="${item.pricing}" style="display_none">
						<input type="text" name="totalamount" value="${item.pricing}" style="display_none">
						</div>
						<input type="submit" class="btn btn-danger shopitembutton2" value="Add to Cart"/>
					</div>
				</div>
				<br>
			</div>
		</c:forEach>
		<br>
	</div>
	</form>
</body>
</html>