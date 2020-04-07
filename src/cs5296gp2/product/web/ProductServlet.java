package cs5296gp2.product.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs5296gp2.product.dao.ProductDao;
import cs5296gp2.product.model.Product;


@WebServlet("/product")
public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ProductDao productDao;
	
	public void init() {
		productDao = new ProductDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			String function_code = request.getParameter("function_code");
			String forward_action = "";
			
			switch(function_code) {
			  case "product_list":
				ArrayList<Product> productList = productDao.getProductList();
				request.setAttribute("product_list", productList);
				forward_action = "product_list.jsp";					
			    break;
			  case "product_item":
			    String product_code = request.getParameter("product_code");
			    Product product = productDao.getProduct(Integer.parseInt(product_code));
			    request.setAttribute("product_item", product);
			    forward_action = "detail.jsp";
			    break;
			  default:
			    // code block
			};
			
			RequestDispatcher rd = request.getRequestDispatcher(forward_action);
			rd.forward(request, response);			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
}
