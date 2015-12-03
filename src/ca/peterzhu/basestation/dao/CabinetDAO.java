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
 * The connection to the SQL server will be done using the JDBC (Java Database
 * Connectivity) framework.
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

	/**
	 * Initializes all the fields to their default values.
	 * 
	 * @param d
	 *            the TxBoardDAO to use
	 */
	public CabinetDAO(TxBoardDAO d) {
		this.txBoardDAO = d;

		TABLE_NAME = "cabinet";
	}

	/**
	 * Creates a Cabinet in the SQL server with the specified Base Station ID.
	 * Loops through the list of TxBoardBean and creates them.
	 * 
	 * @param c
	 *            the CabinetBean to add to the SQL server
	 * @param baseStationID
	 *            the ID of the Base Station that this Cabinet belongs to
	 * @throws SQLException
	 *             thrown when a SQL exception occurs
	 */
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

	/**
	 * Deletes all the Cabinets with the specified Base Station ID.
	 * 
	 * @param baseStationID
	 *            the Base Station ID of the Cabinets
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

			txBoardDAO.deleteAll(baseStationID);
		} finally {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}
	}

	/**
	 * Returns a fully initialized list of CabinetBean for the specified Base
	 * Station ID.
	 * 
	 * @param baseStationID
	 *            the Base Station ID of the Cabinets
	 * @return a list of Cabinets with the Base Station ID
	 * @throws SQLException
	 *             thrown when a SQL exception occurs
	 */
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
