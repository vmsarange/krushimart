<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <form action="changepass" method="post">
  <h2>Change Password</h2>
 
  <br><label>New password : </label><input type="password" name="password1" placeholder="enter new password">
  <br><label>Confirm new password : </label><input type="password" name="password2" placeholder="confirm password">
  <br>
  <input type=submit value="update"> <a href="Login.html"><input type="button" value="cancel"></a>
  
  </form>
</body>
</html>