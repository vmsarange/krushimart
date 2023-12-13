package krushimart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ProductCrud {

public Connection getConnection() throws Exception  {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/krushimart","root","root");
		return connection;
	}
	/*
	 * 
	 */
	public int addPost(Product product) throws Exception {
		
		Connection connection = getConnection();
		
		PreparedStatement pStatement = connection.prepareStatement("insert into product (productname,price,quantity,description) values(?,?,?,?)");
		pStatement.setString(1,product.getProductname());
		pStatement.setDouble(2,product.getPrice());
		pStatement.setInt(3, product.getQuantity());
		pStatement.setString(4, product.getDescription());
		
		
	   int count =	pStatement.executeUpdate();
	   
	   
	   return count;
	}
}
