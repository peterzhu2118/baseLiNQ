package ca.peterzhu.basestation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountDAO {
	private final String TABLE_NAME;

	public AccountDAO() {
		TABLE_NAME = "account";
	}

	public boolean authenticate(String u, String p) throws SQLException {
		String sqlStatement = "SELECT * FROM " + TABLE_NAME + " WHERE username=? AND password=?;";
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

	public void add(String u, String p) throws SQLException {
		String sqlStatement = "INSERT INTO " + TABLE_NAME + " VALUES(?, ?};";
		Connection connection = null;
		try {
			connection = SQLConnector.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);
			prepStmt.setString(1, u);
			prepStmt.setString(2, p);

			prepStmt.execute();
		} finally {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}
	}
}
