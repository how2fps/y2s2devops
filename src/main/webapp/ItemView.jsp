<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="ISO-8859-1">
<title>Item View</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
<link rel="stylesheet" href="static/itemView.css" />
</head>
<body>
	<jsp:include page="/NavBar.jsp" />
	<!-- If current user is the one listing the item, then he can edit or delete the item. -->
	<c:if test="${isListedUser == 'true'}">
		<div class="d-flex flex-row p-3">
			<section class="w-50 d-flex flex-column left-hand">
				<img src="DisplayImageServlet?path=${item.image}" class="item-img" />
			</section>
			<section class="w-50 right-hand">
				<div class="item-name">${item.name}</div>
				<div class="item-pricing d-flex flex-row">
					<p>$</p>
					<p class="item-pricing-number">${item.pricing}</p>
				</div>
				<div class="stocks-left d-flex flex-row">
					<p>Stocks left:</p>
					<p>${item.quantity}</p>
				</div>
				<p class="item-description">${item.description}</p>
				<div class="button-container d-flex flex-row justify-content-around">
					<a class="btn btn-warning mt-3"
						href="EditItemServlet?itemId=${item.id}">Edit Item Details</a> <a
						class="btn btn-danger mt-3"
						href="DeleteItemServlet?itemId=${item.id}"
						onclick="return confirm('Are you sure you want to delete this item? THIS ACTION IS IRREVERSIBLE!')">Delete
						Item</a>
				</div>
				<p class="mt-3 ar-text">All Reviews:</p>
				<div class="review-container"></div>
			</section>
		</div>
	</c:if>
	<!--If current user is not the one listing the item, then he can buy the item  -->
	<c:if test="${isListedUser == 'false'}">
		<div class="d-flex flex-row p-3">
			<section class="w-50 d-flex flex-column left-hand">
				<img src="DisplayImageServlet?path=${item.image}" class="item-img" />
			</section>
			<section class="w-50 right-hand">
				<form action="ItemViewServlet?itemId=${item.id}" method="post">
					<div style="display: none">
						<!-- Fetch user ID of current user logged in of their shopping cart -->
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
						<input type="text" name="itemid" value="${item.id}"> <input
							type="text" name="pricing" value="${item.pricing}">
						<!-- ---------------------------------------- -->
					</div>
					<p class="item-name">${item.name}</p>
					<div class="item-pricing d-flex flex-row">
						<p>$</p>
						<p class="item-pricing-number">${item.pricing}</p>
					</div>
					<div class="stocks-left d-flex flex-row">
						<p>Stocks left:</p>
						<p>${item.quantity}</p>
					</div>
					<p class="item-description">${item.description}</p>
					<div
						class="button-container d-flex flex-row justify-content-around align-items-center">
						<label for="additemamount">How Many? </label> <input type="number"
							min="0" max="99" class="shopadditemamount"
							name="additemquantityofuser" required> <input
							type="submit" class="btn btn-primary" value="Add to Cart" onclick="return confirm('Do you wish to add the item to your cart?')">
					</div>
				</form>
				<p class="mt-3 ar-text">All Reviews:</p>
				<div class="review-container"></div>
			</section>
		</div>
	</c:if>
</body>
</html>