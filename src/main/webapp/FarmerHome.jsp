<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="krushimart.ProductCrud"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 <link rel="stylesheet" href="productview.css">
</head>
<body>
<h1 align="center"><u>Welcome to FarmerHome Page</u></h1>
<br>
<nav>
<a href="FarmerHome.jsp">Home</a>
<a href="AddPost.html">Post</a>
<a href="FarmerProfile.jsp">Profile</a>
<a href="">About</a>
<a href="">contacts</a><br><br>
</nav>
    <%
         ProductCrud crud = new ProductCrud();
        
       Connection connection=  crud.getConnection();
       
       PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM PRODUCT");
       
       ResultSet rSet = pStatement.executeQuery();
       
      %>
    <div class="table"> 
        <table align="center">
        <thead>
        <th>Product Name</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Description</th> 
        </thead>
        <tbody>
       <% while(rSet.next())
       {
    	%>
            <tr>
                <td><%= rSet.getString("productname") %></td>
                <td><%= rSet.getString("price") %></td>
                <td><%= rSet.getString("quantity") %></td>
                <td><%= rSet.getString("description") %></td>
            </tr>
             <% } %>
        </tbody>
        </table>
        </div>
</body>
</html>