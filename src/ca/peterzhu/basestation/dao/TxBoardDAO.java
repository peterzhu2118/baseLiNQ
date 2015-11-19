package ca.peterzhu.basestation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ca.peterzhu.basestation.dao.bean.TxBoardBean;

/**
 * This class works for the CabinetDAO class to create, delete and retrieve
 * TxBoardBean Objects from the SQL server. Updating the TxBoardBean Objects
 * will be done by deleting all TxBoardBean Objects then adding them again. This
 * class assumes the SQL server will automatically commit the changes.
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
			// connection.commit();
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
			// connection.commit();
		} finally {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}
	}

	public List<TxBoardBean> retrieve(String baseStationID, int cabinetID) throws SQLException {
		String sqlStatement = "SELECT * FROM " + TABLE_NAME + " WHERE basestationid=? AND cabinetslotnumber=?";
		Connection connection = null;
		try {
			connection = SQLConnector.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);
			prepStmt.setString(1, baseStationID);
			prepStmt.setInt(2, cabinetID);

			ResultSet results = prepStmt.executeQuery();

			List<TxBoardBean> txBoards = new ArrayList<>();

			while (results.next()) {
				int slotNumber = results.getInt(3);
				int transmitPower = results.getInt(4);
				int frequency = results.getInt(5);

				txBoards.add(new TxBoardBean(slotNumber, transmitPower, frequency));
			}

			return txBoards;
		} finally {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}
	}
}