package ca.peterzhu.basestation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.locks.Lock;

class SQLConnector {
	private static Connection sqlConnection;
	private static Statement sqlStatement;

	private static Lock threadLock;

	private static final String USERNAME;
	private static final String PASSWORD;
	private static final String DATABASE_URL;

	static {
		DATABASE_URL = "jdbc:mysql://localhost:3306/base_stations";
		USERNAME = "root";
		PASSWORD = "raspberr";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}

		try {
			sqlConnection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

			sqlStatement = sqlConnection.createStatement();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	

}
