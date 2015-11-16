package ca.peterzhu.basestation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
	
	public static Connection getConnection() {
		return sqlConnection;
	}

	public static ResultSet executeQuery(String sqlCommand) throws SQLException {
		threadLock.lock();

		ResultSet results = sqlStatement.executeQuery(sqlCommand);

		threadLock.unlock();

		return results;
	}

	public static boolean executeStatement(String sqlCommand) throws SQLException {
		threadLock.lock();
		
		boolean exists = sqlStatement.execute(sqlCommand);
		
		threadLock.unlock();
		
		return exists;
	}
}
