package cs5296gp2.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cs5296gp2.common.dao.MasterDao;
import cs5296gp2.product.model.Product;

public class ProductDao extends MasterDao {
	
    public ArrayList<Product> getProductList() throws ClassNotFoundException {
    	
    	Connection conn = null;

    	String sGET_PRODUCT_LIST_SQL = "SELECT product_code, product_category, product_name, image_file, product_price FROM product;";

        ResultSet result = null;
        Product product = null;
        ArrayList<Product> productList = new ArrayList<Product>() ;

        try {        
        	conn = getConnection();

			// Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = conn.prepareStatement(sGET_PRODUCT_LIST_SQL);
        	
            System.out.println(preparedStatement);
            // Step 3: Execute the query 
            result = preparedStatement.executeQuery();
            
            while (result.next()) {
            	product = new Product(result.getInt("product_code"));
            	product.setProductName(result.getString("product_name"));
            	product.setProductCategory(result.getInt("product_category"));
            	product.setImageFile(result.getString("image_file"));
            	product.setProductPrice(result.getDouble("product_price"));
            	productList.add(product);     
            }            
            
            preparedStatement.close();
            conn.close();
            
        } catch (SQLException e) {
            // process sql exception
            e.printStackTrace();
        }
        return productList;
    }
    
    public Product getProduct(int product_code) throws ClassNotFoundException {
    	
    	Connection conn = null;

    	String sGET_PRODUCT_ITEM_SQL = "SELECT product_code, product_category, product_name, image_file, product_price FROM product WHERE product_code = ?;";

        ResultSet result = null;
        Product product = null;

        try {        
        	conn = getConnection();

			// Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = conn.prepareStatement(sGET_PRODUCT_ITEM_SQL);
            preparedStatement.setInt(1, product_code);
            
            System.out.println(preparedStatement);
            // Step 3: Execute the query 
            result = preparedStatement.executeQuery();
            
            while (result.next()) {
            	product = new Product(result.getInt("product_code"));
            	product.setProductName(result.getString("product_name"));
            	product.setProductCategory(result.getInt("product_category"));
            	product.setImageFile(result.getString("image_file"));
            	product.setProductPrice(result.getDouble("product_price"));
            }            
            
            preparedStatement.close();
            conn.close();
            
        } catch (SQLException e) {
            // process sql exception
            e.printStackTrace();
        }
        return product;
    }
}
