<%@page import="java.util.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
<link rel="stylesheet" href="static/userCart.css" />
<link rel="stylesheet" href="static/navbar.css" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>User Cart</title>
</head>
<body>
	<jsp:include page="/NavBar.jsp"/>
	<br>
	<div class="col d-flex justify-content-center in-title">
		<h1 class="fontcenterUserCart">Your Cart</h1>
	</div>
	<br>
	<br>
	<table>
		<tr>
			<th>Item Photo</th>
			<th>Item Name</th>
			<th>Item Amount</th>
			<th>Add Delivery Charge</th>
			<th>Total Price of Item</th>
			<th>Remove Item</th>
		</tr>
		<c:forEach var="cartitem" items="${listCartItems}">
			<tr>
				<td><img width=150px height=150px src="${cartitem.image}"
					alt="Item Image"></td>
				<td><c:out value="${cartitem.name}" /></td>
				<td><c:out value="${cartitem.itemAmount}" /></td>
				<td><a
					href="<%=request.getContextPath()%>/UserCartServlet/edit?id=<c:out value='${cartitem.id}'/>"><button
							type="button" class="btn btn-info">Add $5</button></a></td>
				<td><c:out value="${cartitem.totalPrice}" /></td>
				<td><a
					href="<%=request.getContextPath()%>/UserCartServlet/delete?id=<c:out value='${cartitem.id}'/>"><button
							type="button" class="btn btn-danger">X</button></a></td>
			</tr>
		</c:forEach>
	</table>

	<div>
		<table class="tablemargin" width=100%>
			<tr class="thnoborder">
				<th>Total Amount: $ ${(total>0)?dcf.format(total):0}</th>
				<th><a
					href="<%=request.getContextPath()%>/UserCartServlet/wipe"><button
							type="button" class="btn buttoncentertable2">Checkout</button></a></th>
			</tr>
		</table>
	</div>
</body>
</html>