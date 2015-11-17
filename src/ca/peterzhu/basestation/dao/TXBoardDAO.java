package ca.peterzhu.basestation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ca.peterzhu.basestation.dao.bean.TXBoardBean;

/**
 * 
 * @author Peter Zhu
 * @version 3.0
 */
public class TXBoardDAO {
	private final String TABLE_NAME;
	
	public TXBoardDAO(){
		TABLE_NAME = "txboard";
	}

	public void create(TXBoardBean t, String baseStationID, int cabinetID) throws SQLException {
		String sqlStatement = "insert into ? values(?, ?, ?, ?, ?)";
		Connection connection = null;
		try {
			connection = SQLConnector.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);
			prepStmt.setString(1, TABLE_NAME);
			prepStmt.setString(2, baseStationID);
			prepStmt.setInt(3, cabinetID);
			prepStmt.setInt(4, t.getSlotNumber());
			prepStmt.setInt(5, t.getTransmitPower());
			prepStmt.setInt(6, t.getFrequency());

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