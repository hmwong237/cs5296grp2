package cs5296gp2.customer.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs5296gp2.customer.dao.CustomerDao;
import cs5296gp2.customer.model.Customer;


@WebServlet("/register")
public class CustomerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CustomerDao customerDao;
	
	public void init() {
		customerDao = new CustomerDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String contact_no = request.getParameter("contact_no");
		
		Customer customer = new Customer(email);
		customer.setPassword(password);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setContactNo(contact_no);
		
		try {
			customerDao.register(customer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("registration_complete.jsp");
	}
}
