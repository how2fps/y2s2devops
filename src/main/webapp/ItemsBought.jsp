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
<link rel="stylesheet" href="static/itemsBought.css" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Items Bought</title>
</head>
<body class="d-flex flex-column align-items-center">
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
		<!--<c:forEach var="item" items"${listItems}">
			<div
				class="item-block d-flex flex-row align-self-center align-items-center">
				<div class="item-info d-flex flex-row align-items-center">
					<img class="item-pic" />
					<p class="amount-item-bought">100</p>
					<p class="item-name"></p>
				</div>
				<div
					class="datetime-bought d-flex justify-content-center align-items-center">
					<p>21 December 2021</p>
				</div>
				<div
					class="amount-bought d-flex justify-content-center align-items-center">
					<p>13.50</p>
				</div>
			</div>
		</c:forEach>-->
		<div
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
		</div>
	</section>
</body>
</html>