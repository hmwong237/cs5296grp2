<%@ page language="java" contentType="text/html;" %>
<%@ page session="true" %>
<%@page import="cs5296gp2.cart.model.*"%>
<%@page import="cs5296gp2.customer.model.Customer"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<script src="js/cookie.js"></script>
<script>
function formSubmit(form_no){
	document.forms[form_no]['sessionid'].value = getCookie('SESSIONID');
	document.forms[form_no].submit();
}
</script>

<title>On-line Shopping Main</title>
</head>
<body>
	<div align="center">
		<h1>Welcome! <%= request.getSession().getAttribute("current_user")!=null?((Customer)request.getSession().getAttribute("current_user")).getFirstName():""%></h1>
	</div>
</body>
</head>
<body>
 <div align="center">
     <h1>Items in Cart</h1>
<%
  ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("shopping_cart");
  ArrayList<CartItem> cartItems = cart.getCartItems();
  double totalPrice = 0;
  
  if (cartItems.size() > 0){
%>
 <b><span lang="en-us"><a href="<%= request.getContextPath() %>/product?function_code=product_list">View Product List</a></span></b>
 <table border='0' cellpadding='6' width='700'>
 <tr>
   <td>Product ID</td>
   <td>Product Name</td>
   <td>Image</td>
   <td>Price</td> 
   <td>Quantity</td> 
   <td>Sub-Total</td>
   <td></td>
 </tr>
<%
  for (int i=0; i<cartItems.size(); i++)
  {
	  CartItem cartItem = cartItems.get(i);	  
	  totalPrice += cartItem.getProduct().getProductPrice()*cartItem.getQuantity();
%>
   <tr>
<form name="item<%=i%>" id="item<%=i%>" action="<%=request.getContextPath()%>/shoppingCart" method="post">
<input type="hidden" name="function_code" value="update_cart" />
<input type="hidden" name="sessionid" />
   <td><input type="hidden" name="product_code" value="<%=cartItem.getProduct().getProductCode()%>"><%=cartItem.getProduct().getProductCode()%></td>
   <td><%=cartItem.getProduct().getProductName()%></td>
   <td><img src="<%=request.getContextPath()%>/image/tn_<%=cartItem.getProduct().getImageFile()%>" /></td>
   <td><%=cartItem.getProduct().getProductPrice()%></td> 
   <td><input type="number" name="quantity" min="0" value="<%=cartItem.getQuantity()%>"></td>
   <td><%=cartItem.getProduct().getProductPrice()*cartItem.getQuantity()%></td> 
   <td><input type="button" name="update_btn" value="Update" onclick="javascript:formSubmit('item<%=i%>');"></td>
   <tr>
  <br>
</form>	
<%
  } /// End of loop
%>
<form name="checkout" id="checkout" action="<%=request.getContextPath()%>/shoppingCart" method="post">
<input type="hidden" name="function_code" value="checkout" />
<input type="hidden" name="sessionid" /> <tr>
   <td></td>
   <td></td>
   <td></td>
   <td></td> 
   <td>Total</td> 
   <td><%=totalPrice %></td>
   <td><input type="button" value="Checkout" onclick="javascript:formSubmit('checkout');"></td>
 </tr>
</table>
<%  
  } else {
%>
    <h2>Your Cart is Empty!</h2>
    <b><span lang="en-us"><a href="<%= request.getContextPath() %>/product?function_code=product_list">View Product List</a></span></b>
<% } %>
 </div>
</body>
</html>
