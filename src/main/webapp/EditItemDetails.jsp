<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Item Details</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
<link rel="stylesheet" href="static/editItemDetails.css" />
</head>
<body class="d-flex flex-column align-items-center">
	<p class="ei-title mt-5">Edit Item Details</p>
	<div class="edit-item-container">
		<form action="">
			<div class="d-flex">
				<section class="aic-left d-flex flex-column">
					<label for="">Add Image</label> <input type="file" />
				</section>
				<section class="aic-right d-flex flex-column">
					<div class="form-group">
						<label for="">Item Name</label> <input type="text"
							class="form-control" />
					</div>
					<div class="form-group">
						<label for="">Item Description</label> <input type="text"
							class="form-control" />
					</div>
					<div class="form-group">
						<label for="">Pricing</label> <input type="number" step="any"
							placeholder="12.55" class="form-control" />
					</div>
					<div class="form-group">
						<label for="">Quantity</label> <input type="number"
							class="form-control" />
					</div>
					<button class="btn edit-item-btn mt-5 w-50 align-self-end">
						Confirm Changes</button>
				</section>
			</div>
		</form>
	</div>
</body>
</html>