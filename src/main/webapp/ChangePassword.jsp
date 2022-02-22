<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
<link rel="stylesheet" href="static/changePassword.css" />
<link rel="stylesheet" href="static/navbar.css" />
<meta charset="ISO-8859-1">
<title>Change Password</title>
</head>
<body>
	<jsp:include page="/NavBar.jsp" />
	<div class="container d-flex flex-column align-items-center">
		<c:if test="${alert != null}">
			<div id="alert" class="alert alert-dark mt-4" role="alert">${alert}</div>
		</c:if>
		<p class="my-4 h1">Change Password</p>
		<div class="change-password-container">
			<form action="ChangePasswordServlet" method="post">
				<div class="d-flex">
					<section class="aic-right d-flex flex-column">
						<div class="form-group mb-3">
							<label for="">Old Password</label> <input type="password"
								name="oldPassword" class="form-control" required />
						</div>
						<div class="form-group mb-4">
							<label for="">New Password</label> <input type="password"
								name="newPassword" class="form-control" required />
						</div>
						<div class="form-group mb-4">
							<label for="">Confirm New Password</label> <input type="password"
								name="confirmNewPassword" class="form-control" required />
						</div>
						<button class="btn btn-primary" type="submit" id="submit"
							style="background: #FF5D73; color: white">Save Changes</button>
					</section>
				</div>
			</form>
		</div>
	</div>
</body>
</html>