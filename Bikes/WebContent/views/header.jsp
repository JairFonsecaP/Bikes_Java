<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
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
			if ("logged".equals(tempCookie.getName())) {
		logged = Boolean.valueOf(tempCookie.getValue());
			}
		}
	}
	%>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="BikesControllerServlet">Bikes</a>
			<div class="d-flex">
				<c:choose>
					<c:when test="<%=logged%>">
						<a type="button" class="btn btn-outline-success"
							href="AdminController">DashBoard</a>
					</c:when>
					<c:otherwise>
						<button type="button" class="btn btn-outline-success"
							data-bs-toggle="modal" data-bs-target="#exampleModal">Login</button>
					</c:otherwise>
				</c:choose>

				<div class="modal fade" id="exampleModal" tabindex="-1"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<jsp:include page="login.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</nav>

</body>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
</html>