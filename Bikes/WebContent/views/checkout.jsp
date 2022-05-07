<%@page import="org.apache.catalina.connector.Response"%>
<%@page import="java.time.LocalTime"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="com.isi.data.Order"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Checkout of ${Order.product.name}</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>


	<%!String getDate() {
		LocalDateTime date = LocalDateTime.now().plusDays(5);
		return date.getDayOfMonth() + "-" + date.getMonth() + "-" + date.getYear();
	}%>

	<%
	Order order = (Order) request.getAttribute("Order");
	%>
	<div class="card-group">
		<div class="card text-center">

			<div class="card-header">
				<ul class="nav nav-tabs card-header-tabs">
					<li class="nav-item"><a class="nav-link active"
						aria-current="true" href="#">Pay</a></li>
				</ul>
			</div>
			<div style="display: flex;">

				<div class="card-body">
				
					<img src="<%=order.getProduct().getImage()%>" class="card-img-top"
						alt="<%=order.getProduct().getName()%>" style="width: 300px">
					<div class="card-body">
						<h5 class="card-title">
							Estimated delivery:
							<%=getDate()%>

						</h5>
						<p class="card-text">${Order.product.name}</p>
						
					</div>
				</div>

				<div class="card text-center">

					<div class="card-body">
						<table class="table">
							<thead class="table-dark">
								<tr>
									<th style="text-align: left;">Order Summary</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr class="table-light">
									<td class="table-light" style="text-align: left;">Total
										before tax:</td>
									<td class="table-light" style="text-align: right;"><%=order.getSubtotalString()%></td>
								</tr>
								<tr class="table-light">
									<td class="table-light" style="text-align: left;">Estimated
										GST/HST:</td>
									<td class="table-light" style="text-align: right;"><%=order.getGstString()%></td>
								</tr>
								<tr class="table-light">
									<td class="table-light" style="text-align: left;">Estimated
										PST/RST/QST:</td>
									<td class="table-light" style="text-align: right;"><%=order.getQstString()%></td>
								</tr>
								<tr class="table-warning">
									<td class="table-warning" style="text-align: left;">Order
										total:</td>
									<td class="table-warning" style="text-align: right;"><%=order.getTotalString()%></td>
								</tr>
							</tbody>
						</table>
						<form action="BikesControllerServlet" method="POST">
							<input type="hidden" name="command" value="SELL" />
							<input type="hidden" name="productId" value="${Order.product.id}" />
							<input type="hidden" name="size" value="${Order.size}" />
							<button type="submit" class="btn btn-outline-success">Place your order</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>

</body>
</html>