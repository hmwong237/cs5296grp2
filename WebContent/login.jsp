<%@ page language="java" contentType="text/html;" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<script src="js/cookie.js"></script>
<script>
function formSubmit(){
	
	if (document.forms[0]['email'].value == "" || document.forms[0]['password'].value == ""){
		alert('Missing email or password!')
	}else{
		document.forms[0].submit();
	}
}

function checkCookie(){
	document.forms[0]['sessionid'].value = getCookie('SESSIONID');
//	alert(getCookie('SESSIONID'));
//	alert(document.forms[0]['sessionid'].value);
}
</script>

<title>Customer Login</title>
</head>
<body onload="javascript:checkCookie();">
	<div align="center">
		<h1>Customer Login Form</h1>
		<% if (session.getAttribute("invalid_login") != null && ((boolean)(session.getAttribute("invalid_login")))) { %> 
		Invalid Login or Password. 
		<% } %>
		<form action="<%=request.getContextPath()%>/login" method="post">
			<input type="hidden" name="sessionid" />
			<input type="hidden" name="checkout" value="<%= request.getAttribute("checkout") %>"/>
			<table style="with: 100%">
				<tr>
					<td>Email</td>
					<td><input type="text" name="email" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" /></td>
				</tr>
			
			</table>
			<input type="button" value="Submit" onclick="javascript:formSubmit()"/>
		</form>
	</div>
</body>
</html>
