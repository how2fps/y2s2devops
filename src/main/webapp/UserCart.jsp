<%@page import="java.util.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);
/* ArrayList<UserCart> cartitems = (ArrayList<UserCart>) session.getAttribute("cart_items");
List<UserCart> cartProduct = null;
if (cartitems != null) {
	Connection connection = new ProductDao(DbCon.getConnection());
	cartProduct = pDao.getCartProducts(cartitems);
	double total = pDao.getTotalCartPrice(cartitems);
	request.setAttribute("total", total);
	request.setAttribute("cart_items", cartitems);
} */
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
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>User Cart</title>
</head>
<body>
	<br>
	<br>
	<br>
<body>
	<div class="col d-flex justify-content-center in-title">
		<h1 class="fontcenterUserCart">Your Cart</h1>
	</div>
	<br>
	<br>
	<table>
		<tr>
			<th>Item Photo</th>
			<th>Item Name</th>
			<th>Amount</th>
			<th>Remove Item</th>
		</tr>
		<c:forEach var="cartitem" items="${listCartItems}">
		<tr>
			<td><c:out value="${cartitem.Image}" /></td>
			<td><c:out value="${cartitem.Name}" /></td>
			<td><c:out value="${cartitem.ItemAmount}" /></td>
			<td><button type="button" class="btn btn-danger">X</button></td>
		
		</tr>
		</c:forEach>
	</table>
	
	<div>
	<table class="tablemargin" width=100%>
		<tr class="thnoborder">
			<th>Total Amount: $ ${(total>0)?dcf.format(total):0}</th>
			<th><button class="buttoncentertable2">Checkout</button></th>
		</tr>
	</table>
	</div>
</body>
</html>