package project.jdbc.util;

import java.sql.*;

public class ConnectionProvider {
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:site");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
		
	}
}
