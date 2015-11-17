package ca.peterzhu.basestation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class SQLConnector {
//	private static Connection sqlConnection;
//	private static Statement sqlStatement;
//
//	private static Lock threadLock;

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

//	public static ResultSet executeQuery(String sqlCommand) throws SQLException {
//		threadLock.lock();
//
//		ResultSet results = sqlStatement.executeQuery(sqlCommand);
//
//		threadLock.unlock();
//
//		return results;
//	}
//
//	public static boolean executeStatement(String sqlCommand) throws SQLException {
//		threadLock.lock();
//		
//		boolean exists = sqlStatement.execute(sqlCommand);
//		
//		threadLock.unlock();
//		
//		return exists;
//	}
}
