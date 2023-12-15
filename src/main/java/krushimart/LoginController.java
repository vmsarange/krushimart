package krushimart;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidAlgorithmParameterException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/login")
public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		HttpSession session = req.getSession();
		session.setAttribute("email", email);
		session.setAttribute("password", password);
		
		UserCrud userCrud = new UserCrud();
		
		PrintWriter printWriter = resp.getWriter();
		RequestDispatcher rDispatcher;
		
		User user;
		
		try {
			
			user = userCrud.login(email);
			
			if (user!=null) {
				
				
				if (user.getPassword().equals(password)) {
					
					if(user.getRole().equalsIgnoreCase("farmer")) {
						
						printWriter.print("Login successful");
						rDispatcher = req.getRequestDispatcher("FarmerHome.jsp");
						rDispatcher.forward(req, resp);
					}
					else if (user.getRole().equalsIgnoreCase("buyer")) {
						
						printWriter.print("Login successful");
						rDispatcher = req.getRequestDispatcher("BuyerHome.jsp");
						rDispatcher.forward(req, resp);
						
					}
					
				}
				else {
					   printWriter.print("<h1>Invalid credentials</h1>");
					   
		               rDispatcher = req.getRequestDispatcher("Login.html");
		               rDispatcher.include(req, resp);
				}
			
			} else {
				   printWriter.print("<h1>Invalid credentials</h1>");
	               rDispatcher = req.getRequestDispatcher("Login.html");
	               rDispatcher.include(req, resp);
			}
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
