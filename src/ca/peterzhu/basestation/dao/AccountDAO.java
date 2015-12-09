package ca.peterzhu.basestation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Performs retrieves on user accounts in the SQL server.
 * 
 * @author Peter Zhu
 * @version 1.0
 */
public class AccountDAO {
	private final String TABLE_NAME;

	/**
	 * Initializes this fields to their default values.
	 */
	public AccountDAO() {
		TABLE_NAME = "account";
	}

	/**
	 * Authenticates the username and password passed in against the SQL
	 * database. Performs a binary bit-to-bit comparison in the SQL server which
	 * allows for case sensitivity.
	 * 
	 * @param u
	 *            the username of the user logging in
	 * @param p
	 *            the password of the user logging in
	 * @return true if the username and password exists in the SQL server; false
	 *         otherwise
	 * @throws SQLException
	 *             thrown if a SQL exception occurs
	 */
	public boolean authenticate(String u, String p) throws SQLException {
		String sqlStatement = "SELECT * FROM " + TABLE_NAME + " WHERE BINARY username=? AND BINARY password=?;";
		Connection connection = null;
		try {
			connection = SQLConnector.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);
			prepStmt.setString(1, u);
			prepStmt.setString(2, p);

			return prepStmt.executeQuery().next();
		} finally {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}
	}

	/*
	 * public void add(String u, String p) throws SQLException { String
	 * sqlStatement = "INSERT INTO " + TABLE_NAME + " VALUES(?, ?};"; Connection
	 * connection = null; try { connection = SQLConnector.getConnection();
	 * PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);
	 * prepStmt.setString(1, u); prepStmt.setString(2, p);
	 * 
	 * prepStmt.execute(); } finally { if (connection != null) {
	 * connection.close(); connection = null; } } }
	 */
}
