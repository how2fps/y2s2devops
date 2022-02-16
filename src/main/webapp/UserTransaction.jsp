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
<link rel="stylesheet" href="static/userTransaction.css" />
<title>Transaction</title>
</head>
<body>
	<jsp:include page="/NavBar.jsp" />
	<br>
	<div class="col d-flex justify-content-center in-title">
		<h1 class="fontcenterTransaction">Transaction</h1>
	</div>
	<br>
	<div class="col d-flex justify-content-center">
		<p class="fontcenterMessageTransaction">You have successfully made
			recent purchases today!</p>
	</div>
	<div class="divcenter">
		<img width=450 height=450 src="images/checkmarksuccesstransaction.png">
	</div>
	<div class="col-md-12 text-center">
		<a href="http://localhost:8090/devopsproject/ItemsShopServlet"
			class="btn btn-danger transactionitembutton">Go To Shop</a>
	</div>
</body>
</html>