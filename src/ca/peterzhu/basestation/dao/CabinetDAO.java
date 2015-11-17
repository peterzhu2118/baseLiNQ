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
		String sqlStatement = "insert into ? values(?, ?)";
		Connection connection = null;
		try {
			connection = SQLConnector.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);
			prepStmt.setString(1, TABLE_NAME);
			prepStmt.setString(2, baseStationID);
			prepStmt.setInt(3, c.getSlotNumber());

			prepStmt.execute();
			connection.commit();
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
