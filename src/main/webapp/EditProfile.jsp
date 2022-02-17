<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Edit Profile</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
<link rel="stylesheet" href="static/editProfile.css" />
<link rel="stylesheet" href="static/navbar.css" />
</head>

<body style="font-family: 'Roboto', sans-serif;">
	<jsp:include page="/NavBar.jsp" />
	<div class="container d-flex flex-column align-items-center">
		<c:if test="${alert != null}">
			<div class="alert alert-dark mt-4" role="alert">${alert}</div>
		</c:if>
		<p class="my-4 h1">Edit Profile</p>
		<div class="edit-profile-container">
			<form action="EditProfileServlet" method="post">
				<div class="d-flex">
					<section class="aic-right d-flex flex-column">
						<div class="form-group mb-3">
							<label for="email">Email</label> <input type="text"
								class="form-control" name="email" value="${userEmail}" />
						</div>
						<div class="form-group mb-3">
							<label for="displayName">Display Name</label> <input type="text"
								class="form-control" name="displayName"
								value="${userDisplayName}" />
						</div>
						<div class="form-group mb-4">
							<label for="phoneNumber">Phone Number</label> <input type="text"
								class="form-control" name="phoneNumber"
								value="${userPhoneNumber}" />
						</div>
						<button id="submit" class="btn btn-secondary"
							style="background: #FF5D73; color: white">Save Changes</button>
					</section>
				</div>
			</form>
		</div>
	</div>
</body>
</html>