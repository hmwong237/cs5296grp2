package cs5296gp2.cart.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cs5296gp2.common.dao.MasterDao;
import cs5296gp2.product.dao.ProductDao;
import cs5296gp2.customer.model.Customer;
import cs5296gp2.product.model.Product;
import cs5296gp2.cart.model.*;


public class ShoppingCartDao extends MasterDao {
	
    public ShoppingCart getShoppingCart(String sessionId) throws Exception {
    	
    	//Connection conn = null;
    	ProductDao productDao = null;

    	String sGET_SHOPPING_CART_SQL = "SELECT session_id, email, product_code, quantity, date_modified FROM shopping_cart WHERE session_id = ?;";

        ResultSet result = null;
        ShoppingCart shoppingCart = null;
        CartItem cartItem = null;

        try (Connection conn = getConnection();

			// Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = conn.prepareStatement(sGET_SHOPPING_CART_SQL);
        	)
        {   
//        try {        
//        
//        	conn = getConnection();
//
//			// Step 2:Create a statement using connection object
//            PreparedStatement preparedStatement = conn.prepareStatement(sGET_SHOPPING_CART_SQL);
            preparedStatement.setString(1, sessionId);
        	
            System.out.println(preparedStatement);
            // Step 3: Execute the query 
            result = preparedStatement.executeQuery();
            
            while (result.next()) {
            	if (shoppingCart == null) {
            		shoppingCart = new ShoppingCart(result.getString("session_id"),new Customer(result.getString("email")));
            	}
            	
            	if (productDao == null) productDao = new ProductDao();
            	
            	cartItem = new CartItem(productDao.getProduct(result.getInt("product_code")), result.getInt("quantity"));
            	cartItem.setDateModified(result.getTimestamp("date_modified"));
            	shoppingCart.addToCart(cartItem);     
            }
            preparedStatement.close();
            conn.close();
            
        } catch (SQLException e) {
            // process sql exception
            e.printStackTrace();
        }
        return shoppingCart;
    }
    
	public boolean addToCart(String sessionId, Customer customer, CartItem cartItem) throws ClassNotFoundException {
		
		boolean bSuccess = false;
		CartItem storedItem = this.getCartItem(sessionId, cartItem);
		if (storedItem != null) {
			cartItem.setQuantity(cartItem.getQuantity()+storedItem.getQuantity());
			bSuccess = this.updateCartItem(sessionId, cartItem);
		}else {
			bSuccess = this.addCartItem(sessionId, customer, cartItem);
		}
		
		return bSuccess;
	}
	
	public CartItem getCartItem(String sessionId, CartItem cartItem) throws ClassNotFoundException {
    	
    	//Connection conn = null;  
    	CartItem storedItem = null;
        
    	String sGET_CART_ITEM_SQL = "SELECT session_id, email, product_code, quantity, date_modified FROM shopping_cart WHERE session_id = ? AND product_code = ?;";

        try (Connection conn = getConnection();

			// Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = conn.prepareStatement(sGET_CART_ITEM_SQL);
        	)
        {  
//        try {
//        	conn = getConnection();
//
//			// Step 2:Create a statement using connection object
//            PreparedStatement preparedStatement = conn.prepareStatement(sGET_CART_ITEM_SQL);
            preparedStatement.setString(1, sessionId);
            preparedStatement.setInt(2, cartItem.getProduct().getProductCode());

            System.out.println(preparedStatement);

            // Step 3: Execute the query 
            ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				storedItem = new CartItem(new ProductDao().getProduct(rs.getInt("product_code")),rs.getInt("quantity"));
				storedItem.setDateModified(rs.getTimestamp("date_modified"));
			}
            
			preparedStatement.close();
            conn.close();
            
        } catch (SQLException e) {
            // process sql exception
            e.printStackTrace();
        }
        return storedItem;
    }
	
	public boolean addCartItem(String sessionId, Customer customer, CartItem cartItem) throws ClassNotFoundException {
    	
    	int result = 0;
    	//Connection conn = null;
        java.util.Date utilDate = new java.util.Date();
        java.sql.Timestamp sqlTS = new java.sql.Timestamp(utilDate.getTime());
        
    	String sINSERT_CART_ITEM_SQL = "INSERT INTO shopping_cart (session_id, email, product_code, quantity, date_modified) VALUES (?,?,?,?,?);";

        try (Connection conn = getConnection();

			// Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = conn.prepareStatement(sINSERT_CART_ITEM_SQL);
        	)
        { 
//        try {        
//        
//        	conn = getConnection();
//
//			// Step 2:Create a statement using connection object
//            PreparedStatement preparedStatement = conn.prepareStatement(sINSERT_CART_ITEM_SQL);
            preparedStatement.setString(1, sessionId);
            preparedStatement.setString(2, customer!=null?customer.getEmail():null);
            preparedStatement.setInt(3, cartItem.getProduct().getProductCode());
            preparedStatement.setInt(4, cartItem.getQuantity());
            preparedStatement.setTimestamp(5, sqlTS);

            System.out.println(preparedStatement);

            // Step 3: Execute the query 
            result = preparedStatement.executeUpdate();
            if (result > 0) {
            	cartItem.setDateModified(utilDate);
            }            
            
            preparedStatement.close();            
            conn.close();
            
        } catch (SQLException e) {
            // process sql exception
            e.printStackTrace();
        }
        return (result > 0);
    }
    
    public boolean updateCart(String sessionId, CartItem cartItem) throws ClassNotFoundException {

    	if (cartItem.getQuantity() > 0) {
    		return updateCartItem(sessionId, cartItem);
    	}
    	else {
    		return removeCartItem(sessionId, cartItem);
    	}
    }
        
	public boolean updateCartItem(String sessionId, CartItem cartItem) throws ClassNotFoundException {
    	
    	int result = 0;
    	//Connection conn = null;
        java.util.Date utilDate = new java.util.Date();
        java.sql.Timestamp sqlTS = new java.sql.Timestamp(utilDate.getTime());
        
    	String sUPDATE_CART_ITEM_SQL = "UPDATE shopping_cart SET quantity = ?, date_modified = ? where session_id = ? and product_code = ?;";

        try (Connection conn = getConnection();

			// Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = conn.prepareStatement(sUPDATE_CART_ITEM_SQL);
        	)
        { 
//    	try {        
//        	conn = getConnection();
//
//			// Step 2:Create a statement using connection object
//            PreparedStatement preparedStatement = conn.prepareStatement(sUPDATE_CART_ITEM_SQL);
            preparedStatement.setInt(1, cartItem.getQuantity());
            preparedStatement.setTimestamp(2, sqlTS);
            preparedStatement.setString(3, sessionId);
            preparedStatement.setInt(4, cartItem.getProduct().getProductCode());

            System.out.println(preparedStatement);

            // Step 3: Execute the query 
            result = preparedStatement.executeUpdate();
            if (result > 0) {
            	cartItem.setDateModified(utilDate);
            }
            
            preparedStatement.close();
            conn.close();
            
        } catch (SQLException e) {
            // process sql exception
            e.printStackTrace();
        }
        return (result > 0);
    }    
    
    public boolean removeCartItem(String sessionId, CartItem cartItem) throws ClassNotFoundException {
    	
    	int result = 0;
//    	Connection conn = null;
        
    	String sDELETE_CART_ITEM_SQL = "DELETE FROM shopping_cart where session_id = ? and product_code = ?;";

        try (Connection conn = getConnection();

			// Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = conn.prepareStatement(sDELETE_CART_ITEM_SQL);
        	)
        { 
//    	try {        
//        	conn = getConnection();
//
//			// Step 2:Create a statement using connection object
//            PreparedStatement preparedStatement = conn.prepareStatement(sDELETE_CART_ITEM_SQL);
            preparedStatement.setString(1, sessionId);
            preparedStatement.setInt(2, cartItem.getProduct().getProductCode());

            System.out.println(preparedStatement);

            // Step 3: Execute the query 
            result = preparedStatement.executeUpdate();

            preparedStatement.close();
            conn.close();
            
        } catch (SQLException e) {
            // process sql exception
            e.printStackTrace();
        }
        return (result > 0);
    }
    
    public boolean checkout(ShoppingCart cart) throws ClassNotFoundException {
    	
    	boolean success = false;
    	int result = 0;
    	Connection conn = null;
    	PreparedStatement preparedStatement = null;
        java.util.Date utilDate = new java.util.Date();
        java.sql.Timestamp sqlTS = new java.sql.Timestamp(utilDate.getTime());
        CartItem cartItem = null;
        double amount_total = 0;
        int item_total = 0;
    	
    	String sINSERT_ORDER_SQL = "INSERT INTO customer_order (order_id, email, date_created) VALUES (?,?,?);";
    	String sINSERT_DETAIL_SQL = "INSERT INTO customer_order_detail (order_id, product_code, unit_price, quantity) VALUES (?,?,?,?);";
    	String sUPDATE_CART_SQL = "UPDATE customer_order SET amount_total = ?, item_total = ? WHERE order_id = ?;";
    	String sDELETE_CART_SQL = "DELETE FROM shopping_cart WHERE session_id = ?;";
    	
    	try {        
        	conn = getConnection();
        	conn.setAutoCommit(false);

			// Step 2:Create a statement using connection object
            preparedStatement = conn.prepareStatement(sINSERT_ORDER_SQL);
			// 1. Insert Order
            preparedStatement = conn.prepareStatement(sINSERT_ORDER_SQL);
            preparedStatement.setString(1, Long.toString(utilDate.getTime())+cart.getSessionId());
            preparedStatement.setString(2, cart.getEmail());
            preparedStatement.setTimestamp(3, sqlTS);

            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
            
            // Insert Order Details
            if (result > 0) {
        		preparedStatement = conn.prepareStatement(sINSERT_DETAIL_SQL);
        		ArrayList<CartItem> cartItems = cart.getCartItems();
            	for (int i=0; i < cartItems.size(); i++) {
            		cartItem =  cartItems.get(i); 
            		item_total += cartItem.getQuantity();
            		amount_total += cartItem.getQuantity()*cartItem.getProduct().getProductPrice();
                    preparedStatement.setString(1, Long.toString(utilDate.getTime())+cart.getSessionId());
                    preparedStatement.setInt(2, cartItem.getProduct().getProductCode());
                    preparedStatement.setDouble(3, cartItem.getProduct().getProductPrice());   
                    preparedStatement.setInt(4, cartItem.getQuantity());
                    preparedStatement.addBatch();
            	}
            	preparedStatement.executeBatch();
                preparedStatement.close();            
            }
            
			// 3. Update Order
            preparedStatement = conn.prepareStatement(sUPDATE_CART_SQL);
            preparedStatement.setDouble(1, amount_total);
            preparedStatement.setInt(2, item_total);
            preparedStatement.setString(3, Long.toString(utilDate.getTime())+cart.getSessionId());

            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
            
			// 4. Remove from Cart
            preparedStatement = conn.prepareStatement(sDELETE_CART_SQL);
            preparedStatement.clearParameters();
            preparedStatement.setString(1, cart.getSessionId());

            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
            
            conn.commit();
            conn.close();
            success = true;

          } catch (Exception ex) {

        	// process sql exception
            ex.printStackTrace();
            success = false;
            
            try {
            	if (conn != null) {
                	conn.rollback();
                	conn.close();
            	}
            }catch (SQLException e) {
                	// process sql exception
                    e.printStackTrace();
            }
        } 
        return success;
    }            
}
