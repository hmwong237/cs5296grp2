package cs5296gp2.customer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cs5296gp2.common.dao.MasterDao;
import cs5296gp2.customer.model.Customer;

public class CustomerDao extends MasterDao{

    public int register(Customer customer) throws Exception {
    	
    	Connection conn = null;
    	int result = 0;
        String sCREATE_USER_SQL = "INSERT INTO customer  (email, password, first_name, last_name, contact_no) VALUES (?, ?, ?, ?, ?);";

        try {        
        	conn = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = conn.prepareStatement(sCREATE_USER_SQL);
            
        	preparedStatement.setString(1, customer.getEmail());
            preparedStatement.setString(2, customer.getPassword());
        	preparedStatement.setString(3, customer.getFirstName());
            preparedStatement.setString(4, customer.getLastName());
            preparedStatement.setString(5, customer.getContactNo());

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();

            preparedStatement.close();
            conn.close();

        } catch (SQLException e) {
            // process sql exception
            e.printStackTrace();
            
        } 
        return result;

    }
}
