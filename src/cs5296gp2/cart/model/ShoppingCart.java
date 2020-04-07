package cs5296gp2.cart.model;

import java.io.Serializable;
import java.util.ArrayList;

import cs5296gp2.customer.model.Customer;

/**
 * JavaBean class used in jsp action tags.
 */
public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String session_id;
	private String email;
	private ArrayList<CartItem> cartItems = new ArrayList<CartItem>();

	public ShoppingCart(String session_id) {
		this.session_id = session_id;
	}

	public ShoppingCart(String session_id, Customer customer) {
		this.session_id = session_id;
		if (customer != null && customer.getEmail() != null)
			this.email = customer.getEmail();
	}

	public ArrayList<CartItem> getCartItems() {
		return cartItems;
	}

	public boolean contains (int productCode) throws Exception{
		
		for (CartItem c : cartItems) {
			if (c.getProduct().getProductCode() == productCode) {
				return true;
			}
		}
		return false;
	}
	
	public boolean contains (CartItem cartItem) throws Exception{
		
		for (CartItem c : cartItems) {
			if (c.getProduct().getProductCode() == cartItem.getProduct().getProductCode() && c.getQuantity() == cartItem.getQuantity()) {
				return true;
			}
		}
		return false;
	}

	public void addToCart (CartItem cartItem) throws Exception{
		
		for (CartItem c : cartItems) {
			if (c.getProduct().getProductCode() == cartItem.getProduct().getProductCode()) {
				updateCart (cartItem);
				break;
			}
		}
		cartItems.add(cartItem);
	}
	
	public void removeFromCart (CartItem cartItem) throws Exception {
		
		if (cartItems.contains(cartItem)) {
			cartItems.remove(cartItem);
		} else { 
			for (CartItem c : cartItems) {
				if (c.getProduct().getProductCode() == cartItem.getProduct().getProductCode()) {
					cartItems.remove(cartItem);
					break;
				}
			}
		}
	}
	
	public void updateCart (CartItem cartItem) {
		
		for (CartItem c : cartItems) {
			if (c.getProduct().getProductCode() == cartItem.getProduct().getProductCode()) {
				c.setQuantity(cartItem.getQuantity());
				c.setDateModified(cartItem.getDateModified());
				break;
			}
		}
	}
	
	public CartItem getCartItem (int product_code) {
		
		CartItem cItem = null;
		for (CartItem c : cartItems) {
			if (c.getProduct().getProductCode() == product_code) {
				cItem = c;
				break;
			}
		}
		return cItem;
	}
	
	public void emptyCart () {
		
		cartItems = new ArrayList<CartItem>();
	}

	public String getSessionId() {
		return session_id;
	}

	public String getEmail() {
		return email;
	}	
	
	public void setEmail(String email) {
		this.email= email ;
	}		
	
}
