package cs5296gp2.login.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cs5296gp2.customer.model.Customer;
import cs5296gp2.login.dao.LoginDao;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginDao;

	public void init() {
		loginDao = new LoginDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String forward_action = "";

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Customer customer = new Customer(email);
		customer.setPassword(password);
		
		try {
			if (loginDao.validate(customer)) {
				
				HttpSession session = request.getSession();				
				session.setAttribute("current_user",customer);
				session.setAttribute("invalid_login",false);
				if ("true".equals(request.getParameter("checkout"))) {
					forward_action = "/shoppingCart?function_code=checkout";
				}else {
					forward_action = "/product?function_code=product_list";
				}
			} else {
				HttpSession session = request.getSession();				
				session.setAttribute("invalid_login",true);
				forward_action = "login.jsp";
			}
			
			RequestDispatcher rd = request.getRequestDispatcher(forward_action);
			rd.forward(request, response);		
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
