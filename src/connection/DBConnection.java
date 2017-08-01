package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
		
private static Connection connection = null;
	
	public static Connection myConnection(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment06","root","admin");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	} // end of myConnection
		
} // end of DBConnection
