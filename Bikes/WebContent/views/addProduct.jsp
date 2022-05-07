<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Product</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
</head>
<body>
	<div class="container mt-5 pt-5">
		<form action="AdminController" method="POST" onsubmit="return ValidateForm(this);">
			<div class="row">
				<h3>Add New Product</h3>
				<table class="table table-sm table-hover">
					<thead class="table-dark">
						<tr>
							<th>Field</th>
							<th>Value</th>
						</tr>
					</thead>
					<tr>
						<td>Name</td>
						<td><input type="text" name="nameProduct" required /></td>
					</tr>
					<tr>
						<td>Description</td>
						<td><textarea name="description" rows="3"
								class="form-control" required></textarea></td>
					</tr>
					<tr>
						<td>Price</td>
						<td>$<input type="text" name="price" value="0" required /></td>
					</tr>
					<tr>
						<td>Stock</td>
						<td><input type="text" name="stock" value="0" required /></td>
					</tr>
					<tr>
						<td>Image URL</td>
						<td><input type="text" name="image" required /></td>
					</tr>
					<tr>
						<td>Brand</td>
						<td><select name="brandSelected"
							class="form-select form-select-sm" required>
								<option selected disabled value="">Select a Brand</option>
								<c:forEach items="${listBrands}" var="brand">
									<option value="${brand.id}">${brand.name}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td>Category</td>
						<td><select name="categorySelected"
							class="form-select form-select-sm" required>
								<option selected disabled value="">Select a Category</option>
								<c:forEach items="${listCategories}" var="category">
									<option value="${category.id}">${category.name}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr class="text-center">
						<td colspan="2"><input type="hidden" name="command"
							value="ADD_PRODUCT" /> <input type="hidden" name="name"
							value="${name}" /> <input type="submit" value="Add"
							class="btn btn-success" /> <input type="hidden" name="logged"
							value="${logged}" /></td>
					</tr>
				</table>
			</div>


		</form>
	</div>
	<script>
		function ValidateForm(form) {

			var price = document.getElementsByName("price")[0].value;

			if (!isNumeric(price)) {
				alert("Price value must be numeric.");
				return false;
			}

			var stock = document.getElementsByName("stock")[0].value;
			if (!isInt(stock)) {
				alert("Stock value must be integer.");
				return false;
			}

			return true;
		}

		function isNumeric(n) {
			return !isNaN(parseFloat(n)) && isFinite(n);
		}
		function isInt(value) {
			return !isNaN(value) && parseInt(Number(value)) == value
					&& !isNaN(parseInt(value, 10));
		}
	</script>
</body>
</html>