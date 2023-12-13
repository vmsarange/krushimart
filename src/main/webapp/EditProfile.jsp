<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="krushimart.UserCrud"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <h1>Edit Profile Page</h1>
   
   <% 
      String email = (String)session.getAttribute("email"); 
   
      UserCrud crud = new UserCrud();
      
      Connection connection = crud.getConnection();
      
      PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM USER WHERE EMAIL=?");
      
      pStatement.setString(1, email);
      
      ResultSet rSet = pStatement.executeQuery(); 
      
      while(rSet.next()){ 
   %>   
    <form action="editprofile" method="post">
     <label>First Name : <input type="text" value="<%= rSet.getString("firstname") %>" name="firstname"></label>
       <br>
    <label>Last Name : <input type="text" value="<%= rSet.getString("lastname")%>"name="lastname"></label>
       <br>
       <label> Email : <input type="email" value="<%= rSet.getString("email") %>"name="email"></label>
       <br>
       <label>Phone : <input type="phone" value="<%= rSet.getString("phone") %>"name="phone"></label>
       <br>
       <label>Address :<input type="text" name="address" value="<%= rSet.getString("address") %>"></label>
     <br> <br> <input type="submit" value="submit">
    </form>
  
    <% } %>
     
</body>
</html>