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
	<!-- If current user logged in has cart items that they added to their shopping cart, they can see their cart items -->
	<table>
		<tr>
			<th>Item Photo</th>
			<th>Item Name</th>
			<th>Item Amount</th>
			<th>Price of Item</th>
			<th>Remove Item</th>
		</tr>
		<c:if test="${isShoppingCartUser == 'true'}">
		<c:forEach var="cartitem" items="${listCartItems}">
			<tr>
				<td><img width=150px height=150px src="DisplayImageServlet?path=${cartitem.image}"
					alt="Item Image"></td>
				<td><c:out value="${cartitem.name}" /></td>
				<td><c:out value="${cartitem.itemAmount}" /></td>
				<td><c:out value="${cartitem.totalPrice}" /></td>
				<td>
				<!-- To add the quantity back returned to the original stock of an item -->
				<form action="UserCartServlet/delete?id=<c:out value='${cartitem.id}'/>" method="post">
				<button type="submit" class="btn btn-danger" onclick="return confirm('Do you wish to removed the item from your cart? Note: The reserved quantity will be returned.')">X</button>
				<div style="display:none">
					<input type="text" name="oriId" value="${cartitem.itemId}">
					<input type="text" name="id" value="${cartitem.itemId}">
					<input type="text" name="itemname" value="${cartitem.name}">
					<input type="text" name="itemdescription" value="${cartitem.description}">
					<input type="text" name="itemimage" value="${cartitem.image}">
					<input type="text" name="itempricing" value="${cartitem.pricing}">
					<input type="text" name="itemquantity" value="${cartitem.itemAmount}">
					<input type="text" name="itemuserId" value="${cartitem.userId}">
					<input type="text" name="itemdateListed" value="${cartitem.dateListed}">
					<input type="text" name="currentstockitemquantity" value="${cartitem.quantity}">
				</div>
				</form>
				<!-- ------------------------------------------------------------------- -->		
				</td>
			</tr>
			<form action="UserCartServlet/wipe" method="post">
			<div style="display:none">
				<input type="text" name="buyinguserid" value="${cartitem.shoppingCartId}">
				<input type="text" name="sellinguserid" value="${cartitem.userId}">
				<input type="text" name="itemid" value="${cartitem.itemId}">
				<input type="text" name="itemname" value="${cartitem.name}">
				<input type="text" name="itemquantity" value="${cartitem.itemAmount}">
				<input type="text" name="itemimage" value="${cartitem.image}">
				<input type="text" name="totalamount" value="${cartitem.totalPrice}">
				<!-- <button type="submit">CHECKOUT</button> -->
			</div>
			</form>
		</c:forEach>
		</c:if>
	</table>
	<!-- If current user logged in has no cart items that they added to their shopping cart, they cannot see the cart items from other users shopping carts -->
	<c:if test="${isShoppingCartUser == 'false'}">
	<!-- No cart items -->
	</c:if>
	<div>
		<table class="tablemargin" width=100%>
			<tr class="thnoborder">
				<th>Total Amount: $ ${(total>0)?dcf.format(total):0}</th>
				<%-- <th><a href="<%=request.getContextPath()%>/UserCartServlet/wipe"><button
							type="button" class="btn buttoncentertable2">Checkout</button></a>	
				</th> --%>
			</tr>
		</table>
		<p>
		
		</p>
	</div>
</body>
</html>