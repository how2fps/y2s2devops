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
<link rel="stylesheet" href="static/addItem.css" />
<link rel="stylesheet" href="static/navbar.css" />
</head>
<body>
	<nav class="navbar px-3 bg-light"
		style="box-shadow: 1px 1px 3px 1px rgba(128, 128, 128, 0.233)">
		<a class="navbar-brand nav-hover" style="color: black;" href="#">Shop-Wijs</a>
		<ul class="nav">
			<li class="nav-item"><a class="nav-link nav-hover"
				style="color: black;" href="#">Shop</a></li>
			<li class="nav-item"><a class="nav-link nav-hover"
				style="color: black;" href="#">Your Cart</a></li>
			<li class="nav-item"><a class="nav-link nav-hover"
				style="color: black;" href="#">Profile</a></li>
			<li class="nav-item"><a class="nav-link nav-hover"
				style="color: black;" href="#">Logout</a></li>
		</ul>
	</nav>
	<div class="d-flex flex-column align-items-center">
		<p class="lni-title mt-5">List New Item</p>
		<div class="add-item-container">
			<form action="/AddItemServlet" method="post">
				<div class="d-flex">
					<section class="aic-left d-flex flex-column">
						<label for="">Add Image</label> <input type="file" name="itemImage"/>
					</section>
					<section class="aic-right d-flex flex-column">
						<div class="form-group">
							<label for="">Item Name</label> <input type="text" name="itemName"
								class="form-control" />
						</div>
						<div class="form-group">
							<label for="">Item Description</label> <input type="text" name="itemDescription"
								class="form-control" />
						</div>
						<div class="form-group">
							<label for="">Pricing</label> <input type="number" step="any"
								placeholder="12.55" class="form-control" name="itemPricing" />
						</div>
						<div class="form-group">
							<label for="">Quantity</label> <input type="number" name="itemQuantity"
								class="form-control" />
						</div>
						<button class="btn add-item-btn mt-5 w-50 align-self-end">
							Confirm Listing</button>
					</section>
				</div>
			</form>
		</div>
	</div>
</body>
</html>