package krushimart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/forgotpass")
public class ForgotPass extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email   = req.getParameter("email");
		String confirmpass = req.getParameter("confirmpass");
		String newpass = req.getParameter("newpass");
		
		
		PrintWriter printWriter = resp.getWriter();
		
		RequestDispatcher rDispatcher ;
		
		try {
			
			UserCrud crud = new UserCrud();
			
		  if (newpass.equals(confirmpass)) {
			  
			  int result = crud.changePassword(email, newpass);
			  
			  if (result!=0) {
				printWriter.print("<h1>Password Updated Successfully</h1>");
				rDispatcher = req.getRequestDispatcher("Login.html");
				rDispatcher.include(req, resp);
			} else {
				printWriter.print("<h2>User with given email does not exist...</h2>");
				rDispatcher = req.getRequestDispatcher("Login.html");
				rDispatcher.include(req, resp);
			}
		 
		} else {
			printWriter.print("<h1 color:green>Password does not matched...</h1>");
			rDispatcher = req.getRequestDispatcher("Login.html");
			rDispatcher.include(req, resp);
		}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}
}
