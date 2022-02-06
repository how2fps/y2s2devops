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
<br>
<br>
<br>
<body>
	<div class="col d-flex justify-content-center in-title">
		<h1 class="fontcenterTransaction">Shop</h1>
	</div>
	<br>
	<br>
	<div class="card" style="width: 20rem;">
		<c:forEach var="item" items="${listItems}">
			<img class="card-img-top" width=100px height=200px src="${item.Image}"
				alt="Item Image">
			<div class="card-body">
				<h5 class="shopitemtitle">
					<c:out value="${item.Name}" />
				</h5>
				<p class="card-text shoppricemargin">
					<c:out value="${item.Pricing}" />
				</p>
				<div class="col-md-12 text-center">
					<a href="#" class="btn btn-danger shopitembutton">Reviews</a>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>