package ca.peterzhu.basestation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ca.peterzhu.basestation.dao.bean.AntennaBean;

/**
 * This class works for the BaseStationDAO class to create, delete and retrieve
 * AntennaBean Objects. Updating AntennaBean Objects will be done by deleting
 * and then recreating the AntennaBean Objects into the SQL server. All the
 * methods in this class assumes the SQL server will automatically commit
 * changes.
 * 
 * <p>
 * The connection to the SQL server will be done using the JDBC (Java Database
 * Connectivity) framework.
 * 
 * @author Peter Zhu
 * @version 3.0
 */
public class AntennaDAO {
	private final String TABLE_NAME;

	/**
	 * Initializes the fields to their default values.
	 */
	public AntennaDAO() {
		TABLE_NAME = "antenna";
	}

	/**
	 * Writes all the fields of the AntennaBean into the SQL server.
	 * 
	 * @param a
	 *            the AntennaBean to write to the SQL server
	 * @param baseStationID
	 *            the unique ID of the Base Station this Antenna belongs to
	 * @throws SQLException
	 *             thrown when a SQL exception occurs
	 */
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
			prepStmt.setInt(5, a.getDowntilt());

			prepStmt.execute();
			// connection.commit();
		} finally {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}
	}

	/**
	 * Deletes all the Antennas in the SQL database with the specified Base
	 * Station Unique ID.
	 * 
	 * @param baseStationID
	 *            the Base Station ID of the Antenna to delete
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
			// connection.commit();
		} finally {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}
	}

	/**
	 * Returns a list of AntennaBean for the specified Base Station.
	 * 
	 * @param baseStationID
	 *            the Base Station Unique ID of the AntennaBean to find
	 * @return the list of AntennaBean that have the Base Station ID passed in
	 * @throws SQLException
	 *             thrown when a SQL exception occurs
	 */
	public List<AntennaBean> retrieve(String baseStationID) throws SQLException {
		String sqlStatement = "SELECT * FROM " + TABLE_NAME + " WHERE basestationid=?";
		Connection connection = null;
		try {
			connection = SQLConnector.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);
			prepStmt.setString(1, baseStationID);

			ResultSet results = prepStmt.executeQuery();

			List<AntennaBean> antennas = new ArrayList<>();
			while (results.next()) {
				int slotNumber = results.getInt(2);
				int height = results.getInt(3);
				int azimuth = results.getInt(4);
				int downtilt = results.getInt(5);

				antennas.add(new AntennaBean(slotNumber, height, azimuth, downtilt));
			}

			return antennas;
		} finally {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}
	}
}
