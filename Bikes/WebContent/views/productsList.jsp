<%@page import="com.isi.data.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<title>Products</title>
</head>
<body>
	<div class="container mt-5 pt-5">
		<form action="AdminController" method="get">
			<div class="row">
				<c:choose>
					<c:when test="${productsList.size() > 0}">
						<table class="table table-sm table-hover">
							<thead class="table-dark">
								<tr>
									<th>Id</th>
									<th>Name</th>
									<th>Price</th>
									<th>Brand</th>
									<th>Category</th>
									<th>Action</th>
								</tr>
							</thead>
							<c:forEach var="product" items="${productsList}">
							<c:url var="updateLink" value="AdminController">
								<c:param name="command" value="UPDATE" />
								<c:param name="productId" value="${product.id}" />
								<c:param name="logged" value="${logged}" />
								<c:param name="name" value="${name}" />
							</c:url>
							<c:url var="deleteLink" value="AdminController">
								<c:param name="command" value="DELETE" />
								<c:param name="productId" value="${product.id}" />
								<c:param name="logged" value="${logged}" />
								<c:param name="name" value="${name}" />
							</c:url>
								<tr>
									<th scope="row">${product.id}</th>
									<td>${product.name}</td>
									<td>${product.getPriceFormated()}</td>
									<td>${product.brand.name}</td>
									<td>${product.category.name}</td>
									<td> 
										<a href="${updateLink}">Update</a> 
										 | 
										<a href="${deleteLink}"
										onclick="if (!(confirm('Are you sure you want to delete this product?'))) return false">
										Delete</a>	
									</td>
								</tr>
							</c:forEach>
						</table>
					</c:when>
					<c:otherwise>
						<span>There is not products to list.</span>
					</c:otherwise>
				</c:choose>
			</div>
		</form>
	</div>
	
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	
</body>
</html>