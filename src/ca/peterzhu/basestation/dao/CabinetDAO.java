package ca.peterzhu.basestation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ca.peterzhu.basestation.dao.bean.CabinetBean;
import ca.peterzhu.basestation.dao.bean.TxBoardBean;

/**
 * 
 * @author Peter Zhu
 * @version 3.0
 */
public class CabinetDAO {
	private final String TABLE_NAME;
	private TxBoardDAO txBoardDAO;

	public CabinetDAO(TxBoardDAO d) {
		this.txBoardDAO = d;

		TABLE_NAME = "cabinet";
	}

	public void create(CabinetBean c, String baseStationID) throws SQLException {
		String sqlStatement = "INSERT INTO " + TABLE_NAME + " VALUES(?, ?)";
		Connection connection = null;
		try {
			connection = SQLConnector.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);
			prepStmt.setString(1, baseStationID);
			prepStmt.setInt(2, c.getSlotNumber());

			prepStmt.execute();
			//connection.commit();
		} finally {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}

		for (TxBoardBean t : c.getTxBoards()) {
			txBoardDAO.create(t, baseStationID, c.getSlotNumber());
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
			// connection.commit();
		} finally {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}
	}
}
