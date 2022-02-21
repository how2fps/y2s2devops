<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Listed</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
	<link rel="stylesheet" href="static/itemsListed.css" />
	<link rel="stylesheet" href="static/navbar.css" />
</head>
<body>
	<jsp:include page="/NavBar.jsp"/>
	<div class="d-flex flex-column align-items-center">
		<p class="il-title">Items you've listed</p>
		<section
			class="items-listed-container container d-flex flex-column justify-content-center">
			<article class="d-flex flex-row">
				<div class="col d-flex justify-content-center i-title">
					<p>Item Name</p>
				</div>
				<div class="col d-flex justify-content-center dl-title">
					<p>Date Listed</p>
				</div>
				<div class="col d-flex justify-content-center q-title">
					<p>Quantity</p>
				</div>
				<div class="col d-flex justify-content-center a-title">
					<p>Amount</p>
				</div>
			</article>
			<c:forEach var="item" items="${itemsListedList}">
				<div
					class="item-block d-flex flex-row align-self-center align-items-center">
					<div class="item-info d-flex flex-row align-items-center">
						<img class="item-pic" src="DisplayImageServlet?path=${item.image}"/>
						<c:out value="${item.name}" />
					</div>
					<div class="datetime-bought d-flex justify-content-center align-items-center">
						<a class="btn btn-primary" href="ItemViewServlet?itemId=${item.id}" id="${item.name}seeDetailsBtn">See Item Details</a>
					</div>
					<div
						class="datetime-bought d-flex justify-content-center align-items-center">
						<c:out value="${item.dateListed}" />
					</div>
					<div
						class="datetime-bought d-flex justify-content-center align-items-center">
						<c:out value="${item.quantity}" />
					</div>
					<div
						class="amount-bought d-flex justify-content-center align-items-center">
						<c:out value="${item.pricing}" />
					</div>
				</div>
			</c:forEach>
		</section>
	</div>
</body>
</html>