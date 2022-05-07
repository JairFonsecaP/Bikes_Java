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
					<c:when test="${orderList.size() > 0}">
						<table class="table table-sm table-hover">
							<thead class="table-dark">
								<tr>
									<th>Id</th>
									<th>Product Id</th>
									<th>Product Name</th>
									<th>Subtotal</th>
									<th>QST</th>
									<th>GST</th>
									<th>Total</th>
								</tr>
							</thead>
							<c:forEach var="order" items="${ordersList}">
								<tr>
									<th scope="row">${order.id}</th>
									<td>${order.product.id}</td>
									<td>${order.product.name}</td>
									<td>${order.getSubtotalString()}</td>
									<td>${order.getQstString()}</td>
									<td>${order.getGstString()}</td>
									<td>${order.getTotalString()}</td>
								</tr>
							</c:forEach>
						</table>
					</c:when>
					<c:otherwise>
						<span>There is not orders to list.</span>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="row">
				<h5>Quantity sales: ${qtySales} </h5>
				<h5>Total of sales: ${totalSales} </h5>
			</div>
		</form>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>