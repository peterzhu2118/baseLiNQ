package ca.peterzhu.basestation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * 
 * @author Peter
 * @version 3.0
 */
class SQLConnector {
	private static final String USERNAME = "root";
	private static final String PASSWORD = "raspberr";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/base_stations";

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
