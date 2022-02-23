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
<link rel="stylesheet" href="static/reviewsPage.css">
<link rel="stylesheet" href="static/navbar.css" />
<title>Reviews Management</title>
</head>
<body>
  <jsp:include page="/NavBar.jsp"/>
  <div class="row"> 
    <div class="container">
      <h3 class="text-center">Reviews Management</h3>
      <hr>
        <div class="container text-left">
          <table class="table">
            <thead>
              <tr>
              <th>Review</th>
              <th>Date Posted</th>
              <th>Actions</th>
              </tr>
             </thead>
             <tbody>
               <c:forEach var="review" reviews="${listReviews}">
               <tr>
                 <td>
                   <c:out value="${review.content}" />
                 </td>
                 <td>
                   <c:out value="${review.time}" />
                 </td>
                 <td>
                   <a href="#" class="btn btn-primary">Edit Review</a>
                   <a href="#" class="btn btn-danger">Delete Review</a>
                 </td>
               </tr>
               </c:forEach>
             </tbody>
           </table>
         </div>
       </div> 
     </div>
</body>
</html>