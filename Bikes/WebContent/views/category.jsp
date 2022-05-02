<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<title>${PRODUCT_LIST[0].category.name}</title>
</head>
<body>
	<h1>Category: ${PRODUCT_LIST[0].category.name}</h1>
	<h3>Products</h3>
	
	<div class="row row-cols-1 row-cols-md-3 g-4">
  
  <c:forEach var="product" items="${PRODUCT_LIST}">
  <div class="col">
    <div class="card">
      <img src="${product.image}" class="card-img-top" alt="${product.name}" style="height: 200px;">
      <div class="card-body">
        <h5 class="card-title">${product.name}</h5>
        <p class="card-text">${product.description}</p>
        <a href="#" class="btn btn-primary">Buy now</a>
      </div>
    </div>
  </div>
  </c:forEach>
</div>
	
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	
</body>
</html>