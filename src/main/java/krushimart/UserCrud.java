package krushimart;


import java.io.CharConversionException;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;

import org.apache.catalina.realm.UserDatabaseRealm;

import com.mysql.cj.jdbc.JdbcConnection;



public class UserCrud {
 /*
  * Creating connection with the database by creating method
  */
	public Connection getConnection() throws Exception  {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/krushimart","root","root");
		return connection;
	}
	/*
	 * 
	 */
	public int register(User user) throws Exception {
		
		Connection connection = getConnection();
		
		PreparedStatement pStatement = connection.prepareStatement("insert into user (firstname,lastname,email,password,phone,role,address) values(?,?,?,?,?,?,?)");
		pStatement.setString(1,user.getFirstname());
		pStatement.setString(2,user.getLastname());
		pStatement.setString(3, user.getEmail());
		pStatement.setString(4, user.getPassword());
		pStatement.setLong(5,user.getPhone());
		pStatement.setString(6,user.getRole());
		pStatement.setString(7,user.getAddress());
		
	   int count =	pStatement.executeUpdate();
	   
	   
	   return count;
	}
	public User login(String email) throws Exception {
		
		Connection connection = getConnection();
		
		PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM USER WHERE EMAIL=?");
		pStatement.setString(1, email);
		
		ResultSet rSet = pStatement.executeQuery();
		
		User user = new User();
		
		if (rSet.next()) {
			
			user.setPassword(rSet.getString("password"));
			user.setRole(rSet.getString("role"));
			connection.close();
			return user;
		}
		connection.close();
		return null;
		
	}
	public int updatePassword(String email, String password) throws Exception {
		
		Connection connection = getConnection();
		
		PreparedStatement pStatement = connection.prepareStatement("UPDATE USER SET PASSWORD=? WHERE EMAIL=?");
		pStatement.setString(1, password);
		pStatement.setString(2, email);
		
		int count = pStatement.executeUpdate();
		
		connection.close();
		
		return count;
	}
	public int changePassword(String email,String newpass) throws Exception {
		
		Connection connection = getConnection();
		
		PreparedStatement pStatement  = connection.prepareStatement("SELECT * FROM USER WHERE EMAIL=?");
		pStatement.setString(1, email);
		
		ResultSet set = pStatement.executeQuery();
		
		int count=0;
		
		if (set.next()) {
			
			PreparedStatement pStatement2 = connection.prepareStatement("UPDATE USER SET PASSWORD=? WHERE EMAIL=?");
			pStatement2.setString(1, newpass);
			pStatement2.setString(2, email);
			
			count = pStatement2.executeUpdate();
			
			connection.close();
			return count;
					
			
		} else {
           
			connection.close();
			return count;		
	  }
	
	}
	public int updateUser(User user,String email) throws Exception {
		
		Connection connection = getConnection();
		
		PreparedStatement pStatement = connection.prepareStatement("update user set firstname=?,lastname=?,email=?,phone=?,address=? where email=?");
		pStatement.setString(1,user.getFirstname());
		pStatement.setString(2,user.getLastname());
		pStatement.setString(3, user.getEmail());
		pStatement.setLong(4,user.getPhone());
		pStatement.setString(5,user.getAddress());
		pStatement.setString(6, email);
		
	   int count =	pStatement.executeUpdate();
	   
	   
	   return count;
		
	}
}
