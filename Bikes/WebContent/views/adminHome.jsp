<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
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
	<%
	Cookie[] cookies = request.getCookies();
	boolean logged = false;
	if (cookies != null) {
		for (Cookie tempCookie : cookies) {
			out.println(tempCookie.getValue());
			if ("logged".equals(tempCookie.getName())) {
		logged = Boolean.valueOf(tempCookie.getValue());
		break;
			}
		}
	}
	String name = String.valueOf(request.getAttribute("name"));
	String command = String.valueOf(request.getAttribute("command"));
	boolean canAccess = (logged == true && name != null);
	%>
	
	
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
									<a class="nav-link dropdown-toggle" href="" id="offcanvasNavbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="true"> Products </a>
									<c:url var="ListProductsLink" value="AdminController">
										<c:param name="logged" value="<%= String.valueOf(logged) %>" />
										<c:param name="name" value="<%= name %>" />
										<c:param name="command" value="LIST_PRODUCTS" />
									</c:url>
									<ul class="dropdown-menu" aria-labelledby="offcanvasNavbarDropdown">
										<li><a class="dropdown-item" href="${ListProductsLink}">List Products</a></li>
										<li><a class="dropdown-item" href="#">Add new product</a></li>
									</ul>
								</li>
								<li class="nav-item dropdown">
									<a class="nav-link dropdown-toggle" href="#" id="offcanvasNavbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="true"> Orders </a>
									<ul class="dropdown-menu" aria-labelledby="offcanvasNavbarDropdown">
										<li><a class="dropdown-item" href="#">List Orders</a></li>
									</ul>
								</li>
							</ul>
							<form class="d-flex">
								<button class="btn btn-outline-success" type="submit">Logout</button>
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
			</c:choose>
		</c:when>
		<c:otherwise>
			<h3>You are not allowed to access to this page. Please login</h3>
		</c:otherwise>
	</c:choose>	
</body>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</html>