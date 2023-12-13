package krushimart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/sign")
public class RegisterController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String email = req.getParameter("email");
		Long phone = Long.parseLong(req.getParameter("phone"));
		String password = req.getParameter("password");
		String role = req.getParameter("role");
		String address = req.getParameter("address");
		
		User user = new User();
		
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setAddress(address);
		user.setEmail(email);
		user.setPassword(password);
		user.setPhone(phone);
		user.setRole(role);
		
		UserCrud userCrud = new UserCrud();
		
		PrintWriter printWriter = resp.getWriter();
		
		RequestDispatcher rDispatcher ;
		
		try {
			
			if (userCrud.register(user)!=0) {
				
				printWriter.print("<h1>Registered successfully</h1>");
				rDispatcher = req.getRequestDispatcher("Login.html");
				rDispatcher.forward(req, resp);
			
			} else {
                printWriter.print("<h1>Try to register again</h1>");
                rDispatcher = req.getRequestDispatcher("welcome.html");
                rDispatcher.include(req, resp);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
	}
}
