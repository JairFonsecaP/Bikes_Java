<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="styles/style.css" type="text/css">
<title>${product.name}</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	
<div class="container  mt-3">
    <div class="card">
        <div class="card-body">
            <h3 class="card-title">${product.name}</h3>
            <div class="row">
                <div class="col-lg-5 col-md-5 col-sm-6">
                    <div class="white-box text-center"><img src="${product.image}" class="img-responsive"></div>
                </div>
                <div class="col-lg-7 col-md-7 col-sm-6">
                    <h4 class="box-title mt-5">Product description</h4>
                    <p>${product.description}</p>
                    <h2 class="mt-5">
                        $${product.price}
                    </h2>
                    
                    <button class="btn btn-primary btn-rounded">Add to cart</button>
                    <button class="btn btn-primary btn-rounded">Add to favorites</button>
                </div>
                
            </div>
        </div>
    </div>
</div>

</body>
</html>