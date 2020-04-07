<%@ page language="java" contentType="text/html;" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<script src="js/cookie.js"></script>
<script>
function formSubmit(){
	document.forms[0]['sessionid'].value = getCookie('SESSIONID');
	document.forms[0].submit();
}
</script>

<title>On-line Shopping Main</title>
</head>
<body onload="javascript:formSubmit();">
		<form action="<%=request.getContextPath()%>/product" method="post">
			<input type="hidden" name="sessionid" />
			<input type="hidden" name="function_code" value="product_list" />
		</form>
</body>
</html>