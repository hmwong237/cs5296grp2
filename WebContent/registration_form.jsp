<%@ page language="java" contentType="text/html;"%>
<!DOCTYPE html>
<html>
<head>
<title>Customer	Registration</title>
</head>
<body>
 <div align="center">
  <h1>Customer Register Form</h1>
  <form action="<%= request.getContextPath() %>/register" method="post">
   <table style="with: 80%">
    <tr>
     <td>Email</td>
     <td><input type="text" name="email" /></td>
    </tr>
    <tr>
     <td>Password</td>
     <td><input type="password" name="password" /></td>
    </tr>
    <tr>
     <td>Re-Enter Password</td>
     <td><input type="password" name="retype_password" /></td>
    </tr>
    <tr>
     <td>First Name</td>
     <td><input type="text" name="firstName" /></td>
    </tr>
    <tr>
     <td>Last Name</td>
     <td><input type="text" name="lastName" /></td>
    </tr>
    <tr>
     <td>Contact No</td>
     <td><input type="text" name="contact_no" /></td>
    </tr>
   </table>
   <input type="submit" value="Submit" />
  </form>
 </div>
</body>
</html>