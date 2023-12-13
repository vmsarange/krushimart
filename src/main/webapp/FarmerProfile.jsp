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
        <%
        
        String email = (String)session.getAttribute("email");
        String password = (String)session.getAttribute("password");
         UserCrud crud = new UserCrud();
        
       Connection connection=  crud.getConnection();
       
       PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM USER WHERE EMAIL=? AND PASSWORD=?");
       pStatement.setString(1, email);
       pStatement.setString(2, password);
       
       ResultSet rSet = pStatement.executeQuery();
       
       while(rSet.next())
       {
    	   
       %>
      <div class="user">
       <label>Name : <%= rSet.getString("firstname")+ rSet.getString("lastname") %></label>
       <br>
       <label> Email : <%= rSet.getString("email") %></label>
       <br>
       <label>Phone : <%= rSet.getString("phone") %></label>
       <br>
       <label>address : <%= rSet.getString("address") %></label>
       
      </div>
      
      <% } %>
      <br>
      <a href="ChangePass.jsp"><button>change password</button></a>    <a href="EditProfile.jsp"><button>Edit Profile</button></a> 
</body>
</html>