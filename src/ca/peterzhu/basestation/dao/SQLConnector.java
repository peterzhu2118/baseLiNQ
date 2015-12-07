package ca.peterzhu.basestation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class manages connections to the SQL server. This is a fully static
 * class meaning that no Object of this class can be created.
 * 
 * @author Peter Zhu
 * @version 3.0
 */
class SQLConnector {
	private static final String USERNAME = "root";
	private static final String PASSWORD = "1Jcsxdliu";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/base_stations";

	private SQLConnector() {

	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
