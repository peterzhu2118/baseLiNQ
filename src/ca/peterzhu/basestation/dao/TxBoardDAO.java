package ca.peterzhu.basestation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ca.peterzhu.basestation.dao.bean.TxBoardBean;

/**
 * 
 * @author Peter Zhu
 * @version 3.0
 */
public class TxBoardDAO {
	private final String TABLE_NAME;

	public TxBoardDAO() {
		TABLE_NAME = "txboard";
	}

	public void create(TxBoardBean t, String baseStationID, int cabinetID) throws SQLException {
		String sqlStatement = "INSERT INTO " + TABLE_NAME + " VALUES(?, ?, ?, ?, ?)";
		Connection connection = null;
		try {
			connection = SQLConnector.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);
			prepStmt.setString(1, baseStationID);
			prepStmt.setInt(2, cabinetID);
			prepStmt.setInt(3, t.getSlotNumber());
			prepStmt.setInt(4, t.getTransmitPower());
			prepStmt.setInt(5, t.getFrequency());

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