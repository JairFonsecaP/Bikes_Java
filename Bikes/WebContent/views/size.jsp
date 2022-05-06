<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

</head>
<body>

	<div class="modal fade" id="sizeModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<form class="modal-content" action="BikesControllerServlet"
				method="GET">
				<input type="hidden" name="command" value="SIZE"></input> <input
					type="hidden" name="productId"
					value="<%=request.getParameter("productId")%>"></input>
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Find my size</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>

				<h6 class="card-subtitle mb-2 text-muted">Select your height</h6>
				<div class="modal-body">

					<select class="form-select" aria-label="Default select example"
						name="height">
						<%
						for (int i = 152; i < 194; i++) {
						%>
						<option value="<%=i%>"><%=i%> centimeters
						</option>
						<%
						}
						%>
					</select>
				</div>
				<div class="modal-footer">

					<button type="submit" class="btn btn-primary">Find my size</button>

				</div>
			</form>
		</div>
	</div>
</body>
</html>