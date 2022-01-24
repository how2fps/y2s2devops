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
<link rel="stylesheet" href="static/itemsSold.css" />
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body class="d-flex flex-column align-items-center">
	<p class="items-sold-title">Items Sold</p>
	<section
		class="items-sold-container container d-flex flex-column justify-content-center">
		<article class="d-flex flex-row">
			<div class="col d-flex justify-content-center in-title">
				<p>Item Name</p>
			</div>
			<div class="col d-flex justify-content-center dt-title">
				<p>DateTime Sold</p>
			</div>
			<div class="col d-flex justify-content-center a-title">
				<p>Amount</p>
			</div>
		</article>
		<p class="d-flex align-self-center row">You have no items sold
			yet!</p>
		<div
			class="item-block d-flex flex-row align-self-center align-items-center">
			<div class="item-info d-flex flex-row align-items-center">
				<img class="item-pic" />
				<p class="amount-item-sold">100</p>
				<p class="item-name">Mini Film Camera</p>
			</div>
			<div
				class="datetime-sold d-flex justify-content-center align-items-center">
				<p>21 December 2021</p>
			</div>
			<div
				class="amount-sold d-flex justify-content-center align-items-center">
				<p>13.50</p>
			</div>
		</div>
	</section>
</body>
</html>