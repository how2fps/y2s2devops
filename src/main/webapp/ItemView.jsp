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
	<div class="d-flex flex-row p-3">
		<section class="w-50 d-flex flex-column left-hand">
			<img src="aesthetically pleasing background.png" class="item-img" />
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
				<c:if test="${isListedUser == 'false'}"><button class="btn btn-primary mt-3">Add to Cart</button></c:if>
				<c:if test="${isListedUser == 'true'}"><a class="btn btn-warning mt-3" href="EditItemServlet?itemId=${item.id}">Edit Item Details</a></c:if>
				<c:if test="${isListedUser == 'true'}"><a class="btn btn-danger mt-3" href="DeleteItemServlet?itemId=${item.id}" onclick="return confirm('Are you sure you want to delete this item? THIS ACTION IS IRREVERSABLE!')">Delete Item</a></c:if>				
			</div>
			<p class="mt-3 ar-text">All Reviews:</p>
			<div class="review-container">
			</div>
		</section>
	</div>
</body>
</html>