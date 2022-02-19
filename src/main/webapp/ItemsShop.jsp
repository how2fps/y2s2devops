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
<link rel="stylesheet" href="static/navbar.css" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Items Shop</title>
</head>
<body>
	<jsp:include page="/NavBar.jsp" />
	<br>
	<div class="col d-flex justify-content-center in-title">
		<h1 class="fontcenterTransaction">Shop</h1>
	</div>
	<br>
	<br>
	<div class="cards d-flex justify-content-center">
		<c:forEach var="item" items="${itemsShopList}">
			<div class="card">
				<img class="card-img-top" width=100px height=200px
					src="${item.image}" alt="Item Image">
				<div class="card-body">
					<h5 class="shopitemtitle">
						<c:out value="${item.name}" />
					</h5>
					<p class="shopitemquantity">
						Quantity:
						<c:out value="${item.quantity}" />
					</p>
					<p class="card-text shoppricemargin">
						<c:out value="${item.pricing}" />
					<div class="col-md-12 text-center">
						<a href="ItemViewServlet?itemId=${item.id}" class="btn btn-danger shopitembutton">See Item Details</a>
					</div>
					<br>
					<div class="col-md-12 text-center">
						<form action="ItemsShopServlet?id=<c:out value='${item.id}'/>"
							method="post">
							<div style="display: none">
								<!-- Fetch user ID of current user logged in -->
								<input type="text" name="shoppingcartid" value="${item.userId}">
								<!-- --------------------------------------- -->
								<!-- Fetch current item information to update the quantity-->
								<input type="text" name="oriId" value="${item.id}"> <input
									type="text" name="id" value="${item.id}"> <input
									type="text" name="name" value="${item.name}"> <input
									type="text" name="description" value="${item.description}">
								<input type="text" name="image" value="${item.image}"> <input
									type="text" name="pricing" value="${item.pricing}"> <input
									type="text" name="quantity" value="${item.quantity}"> <input
									type="text" name="userId" value="${item.userId}"> <input
									type="text" name="dateListed" value="${item.dateListed}">
								<!-- ---------------------------------------- -->
								<!-- Insert current item information to cart_item table -->
								<input type="text" name="sellinguserid" value="${item.userId}">
								<input type="text" name="itemid" value="${item.id}"> 
								<input type="text" name="pricing" value="${item.pricing}"> 
							</div>
							<label for="additemamount">How Many? </label>
							<input type="number" min="0" max="99" class="shopadditemamount" name="additemquantityofuser" required>
							<br>
								<!-- ---------------------------------------- -->
							<input type="submit" class="btn btn-danger shopitembutton2" value="Add to Cart" />
						</form>
					</div>
				</div>
				<br>
			</div>
		</c:forEach>
		<br>
	</div>
</body>
</html>