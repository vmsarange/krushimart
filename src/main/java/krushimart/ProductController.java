package krushimart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/post")
public class ProductController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String productname = req.getParameter("productname");
		double price = Double.parseDouble(req.getParameter("price"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		String description = req.getParameter("description");
		
		Product product = new Product();
		product.setDescription(description);
		product.setPrice(price);
		product.setQuantity(quantity);
		product.setProductname(productname);
		
		ProductCrud crud = new ProductCrud();
		
		PrintWriter printWriter = resp.getWriter();
		RequestDispatcher rDispatcher;
		try {
			
			int result=crud.addPost(product);
			
			if(result!=0)
			{
				rDispatcher = req.getRequestDispatcher("FarmerHome.jsp");
				rDispatcher.forward(req, resp);
			}
			else {
				
			}
	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
