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
              <th>Item</th>
              <th>Quantity</th>
              <th>Date Bought</th>
              <th>Actions</th>
              </tr>
             </thead>
             <tbody>
               <c:forEach var="item" items="${listItems}">
               <tr>
                 <td>
                   <c:out value="${item.name}" />
                 </td>
                 <td>
                   <c:out value="${item.quantity}" />
                 </td>
                 <td>
                   <c:out value="${item.dateListed}" />
                 </td>
                 <td>
                   <a href="EditReviewServlet?itemId=${item.id}" class="btn btn-primary">View All Reviews</a>
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