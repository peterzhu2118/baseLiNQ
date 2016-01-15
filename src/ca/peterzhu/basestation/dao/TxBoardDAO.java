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
 * TxBoardBean objects from the SQL server. Updating the TxBoardBean objects
 * will be done by deleting all TxBoardBean objects then adding them again. This
 * class assumes the SQL server will automatically commit the changes.
 * 
 * <p>
 * The connection to the SQL server will be done using the JDBC (Java Database
 * Connectivity) framework.
 * 
 * @author Peter Zhu
 * @version 3.0
 */
public class TxBoardDAO {
	private final String TABLE_NAME;

	/**
	 * Initializes all the fields to their default values.
	 */
	public TxBoardDAO() {
		TABLE_NAME = "txboard";
	}

	/**
	 * Creates a TX Board in the SQL server with the specified Base Station ID
	 * and Cabinet ID.
	 * 
	 * @param t
	 *            the TxBoardBean to add
	 * @param baseStationID
	 *            the ID of the Base Station this TX Board belongs to
	 * @param cabinetID
	 *            the ID of the Cabinet this TX Board belongs to
	 * @throws SQLException
	 *             thrown when a SQL exception occurs
	 */
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
		} finally {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}
	}

	/**
	 * Deletes all the TX Boards for a specific Base Station.
	 * 
	 * @param baseStationID
	 *            the Base Station ID of the TX Boards
	 * @throws SQLException
	 *             thrown when a SQL exception occurs
	 */
	public void deleteAll(String baseStationID) throws SQLException {
		String sqlStatement = "DELETE FROM " + TABLE_NAME + " WHERE basestationid=?";
		Connection connection = null;
		try {
			connection = SQLConnector.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);
			prepStmt.setString(1, baseStationID);

			prepStmt.execute();
		} finally {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}
	}

	/**
	 * Returns a list of TxBoardBean for the specified Base Station ID and Cabinet ID.
	 * 
	 * @param baseStationID
	 *            the Base Station ID of the TX Boards
	 * @param cabinetID
	 *            the Cabinet ID of the TX Boards
	 * @return a list of TxBoardBean with the Base Station ID and the Cabinet ID
	 * @throws SQLException
	 *             thrown when a SQL exception occurs
	 */
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