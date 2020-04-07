package cs5296gp2.cart.model;

import java.io.Serializable;
import java.util.Date;

import cs5296gp2.product.model.Product;

/**
 * JavaBean class used in jsp action tags.
 */
public class CartItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Product product;
	private int quantity;
	private Date date_modified;

	public Date getDateModified() {
		return date_modified;
	}

	public void setDateModified(Date date_modified) {
		this.date_modified = date_modified;
	}

	public CartItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
