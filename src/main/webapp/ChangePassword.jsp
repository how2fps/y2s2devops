<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div class="container d-flex flex-column align-items-center">
		<p class="my-4 h1">Change Password</p>
		<div class="change-password-container">
			<form action="ChangePasswordServlet" method="post">
				<div class="d-flex">
					<section class="aic-right d-flex flex-column">
						<div class="form-group mb-3">
							<label for="">Old Password</label> <input type="password"
								name="oldPassword" class="form-control" />
						</div>
						<div class="form-group mb-4">
							<label for="">New Password</label> <input type="password"
								name="newPassword" class="form-control" />
						</div>
						<div class="form-group mb-4">
							<label for="">Confirm New Password</label> <input type="password"
								name="confirmNewPassword" class="form-control" />
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