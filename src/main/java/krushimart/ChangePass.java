package krushimart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/changepass")
public class ChangePass  extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String password1 = req.getParameter("password1");
		String password2 = req.getParameter("password2");
		
		HttpSession session = req.getSession();
		 String email = (String) session.getAttribute("email");
		 
		 
		 PrintWriter printWriter = resp.getWriter();
		 
		 RequestDispatcher rDispatcher;
		 
		try {
			 if (password1.equals(password2)) {
					
				 UserCrud crud = new UserCrud();
				 
				 int result = crud.updatePassword(email, password1);
				 
				 if (result!=0) {
					
					 session.setAttribute("password", password1);
					 
					 printWriter.print("<h1>Password Updated</h1>");
					 
					 rDispatcher = req.getRequestDispatcher("Login.html");
					 rDispatcher.include(req, resp);
					 
					 
					 
				} else {
					 printWriter.print("<h1>Something went wrong!</h1>");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
