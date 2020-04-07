<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@page import="cs5296gp2.customer.model.Customer"%>
<%@page import="cs5296gp2.product.model.Product"%>
<%@page import="java.util.*"%>
<%@page import="java.text.NumberFormat"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/cookie.js"></script>
<script>
function addToCart(){
	document.forms["addcart"]['sessionid'].value = getCookie('SESSIONID');
	//alert(document.forms["addcart"]['sessionid'].value);
	document.forms["addcart"].submit();
}
function viewCart(){
	document.forms["cart"]['sessionid'].value = getCookie('SESSIONID');
	//alert(document.forms["cart"]['sessionid'].value);
	document.forms["cart"].submit();
}
</script>

<title>Product Details</title>
</head>
<body>
	<div align="center">
		<h1>
		Welcome! <%= request.getSession().getAttribute("current_user")!=null?((Customer)request.getSession().getAttribute("current_user")).getFirstName():""%>
		</h1>
	</div>
	<div align="center">
     <h1>Add To Cart</h1>
	</div>
	<!-- 
    <div align="right">
     	<form id="cart" name="cart" action="<%=request.getContextPath()%>/shoppingCart" method="post">
			<input type="hidden" name="sessionid" />
			<input type="hidden" name="function_code" value="view_cart" />
			<input type="button" name="view_cart" value="View Cart" onclick="javascript:viewCart();" />
		</form>
	</div>
	 -->
	<%
		Product product = null;
		if (request.getAttribute("product_item") != null) {
			product = (Product) request.getAttribute("product_item");
		}

		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(0);
	%>
	<div align="center">
		<img src="<%=request.getContextPath()%>/image/<%=product.getImageFile()%>" width="200px" height="200px" />
		<table>
			<tr class="row1">
				<td class="col2" colspan="2"
					style="padding: 10px; color: blue; font-size: 15px; text-transform: uppercase; text-align: center; font-weight: bold"><%=product.getProductCode()%></td>
			</tr>
			<tr class="row2">
				<td class="col1">Price:</td>
				<td class="col2">HK$ <%= product.getProductPrice() %> </td>
			</tr>
			<tr class="row2">
				<td class="col1">Quantity</td>
				<td class="col2">
					<form name="addcart" id="addcart" action="<%=request.getContextPath()%>/shoppingCart" method="post">
						<input type="number" name="quantity" min="1" value="1" />
						<input type="hidden" name="function_code" value="add_cart" />
						<input type="hidden" name="sessionid" value="" />
						<input type="hidden" name="product_code"value="<%= product.getProductCode() %>" />
						<input type="button" value="Add to Cart" onclick="javascript:addToCart();">
						<input type="button" value="Cancel" onclick="location.href = '<%= request.getContextPath() %>/product?function_code=product_list';">
						
					</form>	
				</td>
			</tr>
		</table>
	</div>		
</body>
</html>
