<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="ISO-8859-1">
<title>Item View</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
<link rel="stylesheet" href="itemView.css" />
</head>
<body>
	<jsp:include page="/NavBar.jsp" />
	<div class="d-flex flex-row p-3">
		<section class="w-50 d-flex flex-column left-hand">
			<img src="aesthetically pleasing background.png" class="item-img" />
		</section>
		<section class="w-50 right-hand">
			<div class="item-name">Very good Keyboard</div>
			<div class="item-pricing d-flex flex-row">
				$
				<p class="item-pricing-number">45.90</p>
			</div>
			<div class="stocks-left d-flex flex-row">
				<p>Stocks left:</p>
				<div>40</div>
			</div>
			<div class="item-description">Lorem ipsum dolor sit, amet
				consectetur adipisicing elit. Repellendus quos hic magni eaque
				quisquam sapiente harum ipsum architecto. Suscipit minus odio
				aliquid quidem provident exercitationem magnam dignissimos ex ad
				aut.</div>

			<div class="button-container d-flex flex-row justify-content-around">
				<button class="btn btn-primary mt-3">Add to Cart</button>
				<button class="btn btn-warning mt-3">Edit Item Details</button>
				<c:if test="${isListedUser}"><button class="btn btn-danger mt-3">Delete Item</button></c:if>
				
			</div>
			<p class="mt-3">Reviews:</p>
			<div class="review-container">
				<article class="review-card border rounded">
					<p class="review-username mr-2">user</p>
					<p class="review-time">23/11/21</p>
					<p class="review-content">Lorem ipsum dolor sit amet
						consectetur adipisicing elit. Magni aspernatur, obcaecati est
						quia, iusto a voluptas molestiae maiores non vero dignissimos</p>
				</article>
				<article class="review-card border rounded">
					<p class="review-username mr-2">user</p>
					<p class="review-time">23/11/21</p>
					<p class="review-content">Lorem ipsum dolor sit amet
						consectetur adipisicing elit. Magni aspernatur, obcaecati est
						quia, iusto a voluptas molestiae maiores non vero dignissimos</p>
				</article>
				<article class="review-card border rounded">
					<p class="review-username mr-2">user</p>
					<p class="review-time">23/11/21</p>
					<p class="review-content">Lorem ipsum dolor sit amet
						consectetur adipisicing elit. Magni aspernatur, obcaecati est
						quia, iusto a voluptas molestiae maiores non vero dignissimos</p>
				</article>
				<article class="review-card border rounded">
					<p class="review-username mr-2">user</p>
					<p class="review-time">23/11/21</p>
					<p class="review-content">Lorem ipsum dolor sit amet
						consectetur adipisicing elit. Magni aspernatur, obcaecati est
						quia, iusto a voluptas molestiae maiores non vero dignissimos</p>
				</article>
				<article class="review-card border rounded">
					<p class="review-username mr-2">user</p>
					<p class="review-time">23/11/21</p>
					<p class="review-content">Lorem ipsum dolor sit amet
						consectetur adipisicing elit. Magni aspernatur, obcaecati est
						quia, iusto a voluptas molestiae maiores non vero dignissimos</p>
				</article>
				<article class="review-card border rounded">
					<p class="review-username mr-2">user</p>
					<p class="review-time">23/11/21</p>
					<p class="review-content">Lorem ipsum dolor sit amet
						consectetur adipisicing elit. Magni aspernatur, obcaecati est
						quia, iusto a voluptas molestiae maiores non vero dignissimos</p>
				</article>
				<article class="review-card border rounded">
					<p class="review-username mr-2">user</p>
					<p class="review-time">23/11/21</p>
					<p class="review-content">Lorem ipsum dolor sit amet
						consectetur adipisicing elit. Magni aspernatur, obcaecati est
						quia, iusto a voluptas molestiae maiores non vero dignissimos</p>
				</article>
				<article class="review-card border rounded">
					<p class="review-username mr-2">user</p>
					<p class="review-time">23/11/21</p>
					<p class="review-content">Lorem ipsum dolor sit amet
						consectetur adipisicing elit. Magni aspernatur, obcaecati est
						quia, iusto a voluptas molestiae maiores non vero dignissimos</p>
				</article>
			</div>
		</section>
	</div>
</body>
</html>