<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.List"%>
<%@page import="com.isi.data.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="styles/style.css" type="text/css">
<title>${product.name}</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<%
	Product product = (Product) request.getAttribute("Product");
	%>
	<div class="container  mt-3">
		<div class="card">
			<div class="card-body">
				<h3 class="card-title">${Product.name}</h3>
				<div class="row">
					<div class="col-lg-5 col-md-5 col-sm-6">
						<div class="white-box text-center">
							<img src="${Product.image}" class="img-responsive" style="width: 450px;">
						</div>
					</div>
					<div class="col-lg-7 col-md-7 col-sm-6">
						<h4 class="box-title mt-5">Product description</h4>
						<p>${Product.description}</p>
						<p>Quantity available: ${Product.stock}</p>

						<h2 class="mt-5">
							<%=product.getPriceFormated()%>
						</h2>
						<p>Size:</p>
						<form action="BikesControllerServlet" method="GET">
							<select class="form-select" aria-label="Default select example"
								name="size">
								<c:forEach var="Size" items="${Sizes}">
									<option value="${Size}"
										<c:if test="${Size_Calculated eq Size}">selected="selected"</c:if>>${Size}</option>
								</c:forEach>
							</select>
							<p>Free shipping</p>
							 <input type="hidden" name="command" value="CHECKOUT"></input> <input
								type="hidden" name="productId" value="${Product.id}"></input>
							<button type="submit" class="btn btn-primary btn-rounded mt-3">Buy
								now</button>
							<button type="button" class="btn btn-primary btn-rounded mt-3"
								data-bs-toggle="modal" data-bs-target="#sizeModal">Find
								my size</button>
						</form>

					</div>
					<jsp:include page="size.jsp">
						<jsp:param name="productId" value="${Product.id}" />
					</jsp:include>
				</div>
			</div>
		</div>
	</div>
</body>
</html>