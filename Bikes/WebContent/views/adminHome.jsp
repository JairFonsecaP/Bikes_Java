
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%
	Cookie[] cookies = request.getCookies();
	boolean logged = false;
	if (cookies != null) {
		for (Cookie tempCookie : cookies) {
			out.println("cookie:" + tempCookie.getName());
			if ("logged".equals(tempCookie.getName())) {
				out.println("siiiiii");
				logged = Boolean.valueOf(tempCookie.getValue());
			}
		}
	}
	String name = String.valueOf(request.getAttribute("name"));
	String command = String.valueOf(request.getAttribute("command"));

	String log = String.valueOf(request.getAttribute("logged"));
	
	logged = Boolean.valueOf(log);
	boolean canAccess = (logged == true && name != null);
	out.println(canAccess);
	%>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Bikes - Admin</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>

<body>
	
	
	
	<c:choose>
		<c:when test="<%=canAccess%>">
			<nav class="navbar navbar-light bg-light fixed-top">
				<div class="container-fluid">
					<a class="navbar-brand" href="#">Welcome
				<%=name%>!</a>
					<button class="navbar-toggler" type="button"
						data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar"
						aria-controls="offcanvasNavbar">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="offcanvas offcanvas-end" tabindex="-1"
						id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
						<div class="offcanvas-header">
							<h5 class="offcanvas-title" id="offcanvasNavbarLabel">Bike Shop</h5>
							<button type="button" class="btn-close text-reset"
								data-bs-dismiss="offcanvas" aria-label="Close"></button>
						</div>
						<div class="offcanvas-body">
							<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
								<c:url var="link" value="AdminController">
									<c:param name="logged" value="<%= String.valueOf(logged) %>" />
									<c:param name="name" value="<%= name %>" />
								</c:url>
								<li class="nav-item">
									<a class="nav-link active"	aria-current="page" href="${link}">Home</a>
								</li>
								<li class="nav-item dropdown">
									<h5> Products </h5>
									<c:url var="ListProductsLink" value="AdminController">
										<c:param name="logged" value="<%= String.valueOf(logged) %>" />
										<c:param name="name" value="<%= name %>" />
										<c:param name="command" value="LIST_PRODUCTS" />
									</c:url>
									
									<c:url var="AddProductLink" value="AdminController">
										<c:param name="logged" value="<%= String.valueOf(logged) %>" />
										<c:param name="name" value="<%= name %>" />
										<c:param name="command" value="ADD_FORM" />
									</c:url>
									<ul style="list-style: none;" >
										<li><a class="dropdown-item" href="${ListProductsLink}">List Products</a></li>
										<li><a class="dropdown-item" href="${AddProductLink}">Add new product</a></li>
									</ul>
								</li>
								<li class="nav-item dropdown">
									<h5> Orders </h5>
									<c:url var="ListOrdersLink" value="AdminController">
										<c:param name="logged" value="<%= String.valueOf(logged) %>" />
										<c:param name="name" value="<%= name %>" />
										<c:param name="command" value="LIST_ORDERS" />
									</c:url>
									<ul style="list-style: none;" >
										<li><a class="dropdown-item" href="${ListOrdersLink}">List Orders</a></li>
									</ul>
								</li>
								
							</ul>
							<form class="d-flex mt-3" action="AdminController" method="POST">
								<input type="hidden" name="command" value="LOGOUT" /> 
								<input type="hidden" name="name" value="<%= name %>" /> 
								<input type="hidden" name="logged" value="${logged}" /> 
								<button class="btn btn-outline-danger" type="submit">Logout</button>
							</form>
						</div>
					</div>
				</div>
			</nav>
			<c:choose>
				<c:when test="${command == 'List_Products'}">
					<jsp:include page="productsList.jsp">
						<jsp:param value="${productsList}" name="productsList"/>
					</jsp:include>
				</c:when>
				<c:when test="${command == 'update_Product'}">
					<jsp:include page="updateProduct.jsp">
						<jsp:param value="${product}" name="product"/>
						<jsp:param value="${listBrands}" name="listBrands"/>
						<jsp:param value="${listCategories}" name="listCategories"/>
						<jsp:param value="${name}" name="name"/>
					</jsp:include>
				</c:when>
				<c:when test="${command == 'add_Product'}">
					<jsp:include page="addProduct.jsp">
						<jsp:param value="${listBrands}" name="listBrands"/>
						<jsp:param value="${listCategories}" name="listCategories"/>
						<jsp:param value="${name}" name="name"/>
					</jsp:include>
				</c:when>
				<c:when test="${command == 'List_Orders'}">
					<jsp:include page="ordersList.jsp">
						<jsp:param value="${ordersList}" name="ordersList"/>
						<jsp:param value="${totalSales}" name="qtySales"/>
					</jsp:include>
				</c:when>
			</c:choose>
		</c:when>
		<c:otherwise>
			<h3>You are not allowed to access to this page. Please login</h3>
		</c:otherwise>
	</c:choose>	
</body>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</html>