package cs5296gp2.common.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class MasterDao {

//	public static final String sCONNECTION_STRING = "jdbc:mysql://cs5296gp2.cserrc70n1l6.us-east-1.rds.amazonaws.com:3306/cs5296gp2?useSSL=false";
//	public static final String sCONNECTION_PWD = "jdf8nbibfuww";
//	public static final String sCONNECTION_STRING = "jdbc:mysql://54.234.251.224:3306/cs5296gp2?useSSL=false"; 
//	public static final String sCONNECTION_USER = "admin";
	public static final String sCONNECTION_STRING = "jdbc:mysql://localhost:3306/cs5296gp2?useSSL=false&holdResultsOpenOverStatementClose=true"; 
	public static final String sCONNECTION_USER = "root";
	public static final String sCONNECTION_PWD = "P@ssw0rd";
	protected Connection getConnection() throws ClassNotFoundException {
		
		Connection conn = null;
        
        try {                    	

        	Class.forName("com.mysql.cj.jdbc.Driver");
        	conn = DriverManager.getConnection(sCONNECTION_STRING, sCONNECTION_USER, sCONNECTION_PWD);
        	
        } catch (SQLException e) {
            // process sql exception
            e.printStackTrace();
        }
		return conn;
	}
	
	protected void closeConnection(Connection conn) {
		
        try {                    	

        	if (conn != null)
        		conn.close();
        	
        } catch (SQLException e) {
            // process sql exception
            e.printStackTrace();
        }
	}

}
