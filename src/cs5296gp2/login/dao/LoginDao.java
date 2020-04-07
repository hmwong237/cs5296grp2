package cs5296gp2.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cs5296gp2.common.dao.MasterDao;
import cs5296gp2.customer.model.*;

public class LoginDao extends MasterDao {

	public boolean validate(Customer customer) throws ClassNotFoundException {
		
    	Connection conn = null;
		boolean login_status = false;
		String sVALIDATE_USER_SQL = "select first_name, last_name from customer where email = ? and password = ? ";

        try {        
        	conn = getConnection();

			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = conn.prepareStatement(sVALIDATE_USER_SQL);
			preparedStatement.setString(1, customer.getEmail());
			preparedStatement.setString(2, customer.getPassword());

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				login_status = true;
				customer.setFirstName(rs.getString(1));
				customer.setLastName(rs.getString(2));
			}
			preparedStatement.close();
            conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return login_status;
	}

}
