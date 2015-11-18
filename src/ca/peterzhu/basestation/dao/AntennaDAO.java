package ca.peterzhu.basestation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ca.peterzhu.basestation.dao.bean.AntennaBean;

/**
 * 
 * @author Peter Zhu
 * @version 3.0
 */
public class AntennaDAO {
	private final String TABLE_NAME;
	
	public AntennaDAO(){
		TABLE_NAME = "antenna";
	}

	public void create(AntennaBean a, String baseStationID) throws SQLException {
		String sqlStatement = "INSERT INTO " + TABLE_NAME + " VALUES(?, ?, ?, ?, ?)";
		Connection connection = null;
		try {
			connection = SQLConnector.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);
			prepStmt.setString(1, baseStationID);
			prepStmt.setInt(2, a.getSlotNumber());
			prepStmt.setInt(3, a.getHeight());
			prepStmt.setInt(4, a.getAzimuth());
			prepStmt.setInt(5, a.getHeight());

			prepStmt.execute();
			//connection.commit();
		} finally {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}
	}

	public void deleteAll(String baseStationID) throws SQLException {
		String sqlStatement = "DELETE FROM " + TABLE_NAME + " WHERE basestationid=?";
		Connection connection = null;
		try {
			connection = SQLConnector.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);
			prepStmt.setString(1, baseStationID);

			prepStmt.execute();
			//connection.commit();
		} finally {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}
	}
}
