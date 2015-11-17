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
		String sqlStatement = "insert into ? values(?, ?, ?, ?, ?)";
		Connection connection = null;
		try {
			connection = SQLConnector.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);
			prepStmt.setString(1, TABLE_NAME);
			prepStmt.setString(2, baseStationID);
			prepStmt.setInt(3, a.getSlotNumber());
			prepStmt.setInt(4, a.getHeight());
			prepStmt.setInt(5, a.getAzimuth());
			prepStmt.setInt(6, a.getHeight());

			prepStmt.execute();
			connection.commit();
		} finally {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}
	}

	public void deleteAll(String baseStationID) throws SQLException {
		String sqlStatement = "delete from ? where basestationid=?";
		Connection connection = null;
		try {
			connection = SQLConnector.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);
			prepStmt.setString(1, TABLE_NAME);
			prepStmt.setString(2, baseStationID);

			prepStmt.execute();
			connection.commit();
		} finally {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}
	}
}
