package ca.peterzhu.basestation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ca.peterzhu.basestation.dao.bean.CabinetBean;
import ca.peterzhu.basestation.dao.bean.TxBoardBean;

/**
 * This class works for the BaseStationDAO class to create, delete and retrieve
 * CabinetBean Objects from the SQL server. Updating the CabinetBean Objects
 * will be done by deleting all the CabinetBean Objects and then recreating
 * them. This class assumes the SQL server will automatically commit changes.
 * 
 * <p>
 * This class utilizes the TxBoardDAO class to function.
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

			txBoardDAO.deleteAll(baseStationID);
		} finally {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}
	}

	public List<CabinetBean> retrieve(String baseStationID) throws SQLException {
		String sqlStatement = "SELECT * FROM " + TABLE_NAME + " WHERE basestationid=?";
		Connection connection = null;
		try {
			connection = SQLConnector.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);
			prepStmt.setString(1, baseStationID);

			ResultSet result = prepStmt.executeQuery();

			List<CabinetBean> cabinets = new ArrayList<>();

			while (result.next()) {
				int slotNumber = result.getInt(2);

				List<TxBoardBean> txBoards = txBoardDAO.retrieve(baseStationID, slotNumber);

				cabinets.add(new CabinetBean(slotNumber, txBoards));
			}

			return cabinets;
		} finally {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}
	}
}
