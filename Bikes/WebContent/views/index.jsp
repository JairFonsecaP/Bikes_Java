<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="styles/style.css" type="text/css">
<title>Bikes</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="carousel-container">
	<div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
	  <div class="carousel-indicators">
	    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
	    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
	    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
	  </div>
	  <div class="carousel-inner">
	  
	  <c:forEach var="product" items="${TOP3_LIST}">
	  
	    <div class="carousel-item <c:if test="${product.id eq TOP3_LIST[0].id}">active</c:if>">
	      <img src="${product.image}" class="d-block w-100" alt="${product.name}">
	      <div class="carousel-caption d-none d-md-block">
	        <h5 class="description-carousel">${product.name}</h5>
	        <p class="description-carousel">${product.description}</p>
	      </div>
	    </div>
	   </c:forEach>
	  </div>
	  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
	    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	    <span class="visually-hidden">Previous</span>
	  </button>
	  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
	    <span class="carousel-control-next-icon" aria-hidden="true"></span>
	    <span class="visually-hidden">Next</span>
	  </button>
	</div>
	</div>
	<div class="m-3">
	<div class="row row-cols-1 row-cols-md-3 g-4">
	<c:forEach var="category" items="${CATEGORIES_LIST}">
	  <div class="col">
	    <div class="card h-100">
	      <img src="${category.image}" class="card-img-top" alt="${category.image}">
	      <div class="card-body">
	        <h5 class="card-title">${category.name}</h5>
	      </div>
	      <div class="card-footer" >
	      	<c:url var="tempLink" value="BikesControllerServlet">
					<c:param name="command" value="CATEGORIES" />
					<c:param name="categoryId" value="${category.id}" />
			</c:url>
	        <a href="${tempLink}" class="btn btn-primary">See all products for ${category.name} category</a>
	      </div>
	    </div>
	  </div>
  	</c:forEach>
</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

</body>
</html>