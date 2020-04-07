<%@ page language="java" contentType="text/html;" %>
<%@ page session="true" %>
<%@page import="cs5296gp2.cart.model.*"%>
<%@page import="cs5296gp2.customer.model.Customer"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<script src="js/cookie.js"></script>
<title>On-line Shopping Main</title>
</head>
<body>
	<div align="center">
		<h1>Welcome! <%=((Customer)request.getSession().getAttribute("current_user")).getFirstName()%></h1>
	</div>
</body>
</head>
<body>
 <div align="center">
     <h1>You have checked out successfully!</h1>
    <b><span lang="en-us"><a href="<%= request.getContextPath() %>/product?function_code=product_list">View Product List</a></span></b>
 </div>
</body>
</html>
