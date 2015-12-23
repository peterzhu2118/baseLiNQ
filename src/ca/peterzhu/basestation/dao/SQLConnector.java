package ca.peterzhu.basestation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages connections to the SQL server. This is a fully static
 * class meaning that no Object of this class can be created.
 * 
 * @author Peter Zhu
 * @version 3.0
 */
class SQLConnector {
	private static final String USERNAME = "root";
	private static final String PASSWORD ="1Jcsxdliu";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/base_stations";

	/**
	 * Private constructor prevents the creation of this class.
	 */
	private SQLConnector() {

	}

	/**
	 * Returns a Connection to the SQL server.
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
