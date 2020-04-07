<%@ page language="java" contentType="text/html;" %>
<%@ page session="true" %>
<%@page import="cs5296gp2.customer.model.Customer"%>
<%@page import="cs5296gp2.product.model.Product"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<script src="js/cookie.js"></script>
<script>

function formSubmit(product_code){
	document.forms["product"]['sessionid'].value = getCookie('SESSIONID');
	//alert(document.forms["product"]['sessionid'].value);
	document.forms["product"]['product_code'].value = product_code;
	document.forms["product"].submit();
}

function viewCart(){
	document.forms["cart"]['sessionid'].value = getCookie('SESSIONID');
	//alert(document.forms["cart"]['sessionid'].value);
	document.forms["cart"].submit();
}
function checkCookie(){
	<%
	if (request.getParameter("sessionid") == null || "".equals(request.getParameter("sessionid"))){
	%>
	setCookie('SESSIONID','<%= request.getRequestedSessionId()%>');
	<%
	}
	%>
}
</script>

<title>On-line Shopping Main</title>
</head>
<% if (request.getParameter("sessionid") == null || "".equals(request.getParameter("sessionid"))){ %>
<body onload="javascript:checkCookie();">
<% } else {%>
<body>
<% } %>
	<div align="center">
		<h1>
		Welcome! <%= request.getSession().getAttribute("current_user")!=null?((Customer)request.getSession().getAttribute("current_user")).getFirstName():""%>
		</h1>
	</div>
	<div align="right">
		<h2>	
		</h2>
	</div>
</body>
</head>
<body>
 <div align="center">
     <h1>View Products</h1>
 <table border='0' cellpadding='6' width='700'>
 <tr>
   <td></td>
   <td></td>
   <td></td>
   <td></td>
   <td></td> 
   <td>
   	<form id="cart" name="cart" action="<%=request.getContextPath()%>/shoppingCart" method="post">
		<input type="button" name="view_cart" value="View Cart" onclick="javascript:viewCart();" />
		<input type="hidden" name="sessionid" />
		<input type="hidden" name="function_code" value="view_cart" />
	</form>
	</td> 
 </tr> 
<form id="product" name="product" action="<%=request.getContextPath()%>/product" method="post">
<input type="hidden" name="function_code" value="product_item" />
<input type="hidden" name="product_code" />
<input type="hidden" name="sessionid" />
 <tr>
   <td>Product ID</td>
   <td>Product Name</td>
   <td>Product Category</td>
   <td>Image</td>
   <td>Price</td> 
   <td></td> 
   </tr>
  <%
  ArrayList<Product> productList = (ArrayList) request.getAttribute("product_list");
  
  for (int i=0; i<productList.size(); i++)
  {
	  Product product = productList.get(i); 
  %>
   <tr>
   <td><%=product.getProductCode()%></td>
   <td><%=product.getProductName()%></td>
   <td><%=product.getProductCategory()%></td>
   <td><img src="<%=request.getContextPath()%>/image/tn_<%=product.getImageFile()%>"/></td>
   <td><%=product.getProductPrice()%></td> 
   <td><input type="button" name="add_to_cart" value="Add to Cart" onclick="javascript:formSubmit('<%= product.getProductCode() %>');"></td>
   <tr>
  <br>
  <%
  } /// End of loop
  %>
</form>	
</table>
 </div>
</body>
</html>
