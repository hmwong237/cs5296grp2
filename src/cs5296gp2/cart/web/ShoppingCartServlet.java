package cs5296gp2.cart.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs5296gp2.cart.dao.ShoppingCartDao;
import cs5296gp2.customer.model.Customer;
import cs5296gp2.product.model.Product;
import cs5296gp2.cart.model.CartItem;
import cs5296gp2.cart.model.ShoppingCart;

@WebServlet("/shoppingCart")
public class ShoppingCartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ShoppingCartDao shoppingCartDao;
	
	public void init() {
		shoppingCartDao = new ShoppingCartDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			String function_code = request.getParameter("function_code");
			ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("shopping_cart");
			
			String forward_action = "";
			String sessionId = request.getParameter("sessionid");
			Customer customer = (Customer) request.getSession().getAttribute("current_user"); 
			String product_code = null;
			String quantity = null;
			CartItem cartItem = null;
			
			switch(function_code) {
			  case "add_cart":
				
				product_code = request.getParameter("product_code");
				quantity = request.getParameter("quantity");
				cartItem = new CartItem(new Product(Integer.parseInt(product_code)), Integer.parseInt(quantity));
				
				boolean bAdded = shoppingCartDao.addToCart(sessionId, customer, cartItem);
				
				if (bAdded) {
					// Update to shopping cart in session
					shoppingCart = shoppingCartDao.getShoppingCart(sessionId);
				}
				
				request.getSession().setAttribute("shopping_cart", shoppingCart);				
				forward_action = "/product?function_code=product_list";
				
			    break;
			  case "update_cart":
								
				product_code = request.getParameter("product_code");
				quantity = request.getParameter("quantity");
				cartItem = new CartItem(new Product(Integer.parseInt(product_code)), Integer.parseInt(quantity));

				boolean bUpdated = shoppingCartDao.updateCart(sessionId, cartItem);
				
				if (bUpdated) {
					// Update to shopping cart in session
					shoppingCart = shoppingCartDao.getShoppingCart(sessionId);
				}
				
				request.getSession().setAttribute("shopping_cart", shoppingCart);				
				forward_action = "cart.jsp";
				
			    break;

			  case "view_cart":
					
				shoppingCart = shoppingCartDao.getShoppingCart(sessionId);
				if (shoppingCart == null) 
					shoppingCart = new ShoppingCart(sessionId,customer);				
				request.getSession().setAttribute("shopping_cart", shoppingCart);				
				forward_action = "cart.jsp";
				
			    break;
			  case "checkout":
				if (customer != null) { 
					if (shoppingCart.getEmail() == null)
						shoppingCart.setEmail(customer.getEmail());
					boolean success = shoppingCartDao.checkout(shoppingCart);
					if (success) {
						forward_action = "checkout_complete.jsp";
					}else {
						forward_action = "/shoppingCart?function_code=view_cart";
					}
					
				}else {
					forward_action = "login.jsp";
					request.setAttribute("checkout", true);
				}
				
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
}
