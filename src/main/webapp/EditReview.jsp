<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
<link rel="stylesheet" href="static/reviewForm.css">
<title>Edit Review</title>
</head>
<body>
<h3 class="text-center">Edit Review</h3>
<div class="container col-md-6">
<div class="card review-form-card">
<div class="card-body">
     <form action="EditReviewServlet/update">
       <fieldset class="form-group">
         <label for="content">Comment</label>
         <textarea name="content" class="form-control" required><c:out value='${review.content}'/></textarea>
       </fieldset>
       <div style="text-align:center;">
         <button type="submit" class="btn review-form-btn">Save Changes</button>
         <p><a href="http://localhost:8090/devopsproject/ReviewsManagementServlet">Back</a></p>
       </div>
     </form>
   </div>
   </div>
   </div>
</body>
</html>