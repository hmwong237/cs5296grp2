package cs5296gp2.product.model;

import java.io.Serializable;

/**
 * JavaBean class used in jsp action tags.
 */
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int product_code;
	private int product_category;
	private String product_name;
	private String image_file;
	private Double product_price;

	public Product(int product_code) {
		this.product_code = product_code;
	}

	public Product(int product_code, int product_category, String product_name,
			String image_file, Double product_price) {
		this.product_code = product_code;
		this.product_category = product_category;
		this.product_name = product_name;
		this.image_file = image_file;
		this.product_price = product_price;
	}

	public int getProductCode() {
		return product_code;
	}

	public void setProductCode(int product_code) {
		this.product_code = product_code;
	}

	public String getProductName() {
		return product_name;
	}

	public void setProductName(String product_name) {
		this.product_name = product_name;
	}

	public String getImageFile() {
		return image_file;
	}

	public void setImageFile(String image_file) {
		this.image_file = image_file;
	}

	public Double getProductPrice() {
		return product_price;
	}

	public void setProductPrice(Double product_price) {
		this.product_price = product_price;
	}

	public int getProductCategory() {
		return product_category;
	}

	public void setProductCategory(int product_category) {
		this.product_category = product_category;
	}

}
