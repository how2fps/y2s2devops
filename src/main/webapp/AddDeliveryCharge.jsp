<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Add Delivery Charge</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<body>
	<div class="container col-md-6">
		<div class="card">
			<div class="card-body">
				<c:if test="${existingUserCart != null}">
					<form action="update" method="post">
				</c:if>
				<caption>
					<h2>
						<c:if test="${existingUserCart != null}">
Add Delivery Charge
</c:if>
					</h2>
				</caption>

				<c:if test="${existingUserCart != null}">
					<input type="hidden" name="id"
						value="<c:out
value='${existingUserCart.id}' />" />
				</c:if>

				<c:if test="${existingUserCart != null}">
					<input type="hidden" name="id"
						value="<c:out
value='${existingUserCart.id}' />" />
				</c:if>
				<c:if test="${existingUserCart != null}">
					<input type="hidden" name="shoppingCartId"
						value="<c:out
value='${existingUserCart.shoppingCartId}' />" />
				</c:if>
				<c:if test="${existingUserCart != null}">
					<input type="hidden" name="itemId"
						value="<c:out
value='${existingUserCart.itemId}' />" />
				</c:if>
				<fieldset class="form-group">
					<label>Item Amount</label> <input type="text"
						value="<c:out
value='${existingUserCart.itemAmount}' />"
						class="form-control" name="itemAmount" disabled>
				</fieldset>
				<fieldset class="form-group">
					<label>Total Price of Item</label> <input type="text"
						value="<c:out
value='${existingUserCart.totalPrice}' />"
						class="form-control" name="totalPrice">
				</fieldset>
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>