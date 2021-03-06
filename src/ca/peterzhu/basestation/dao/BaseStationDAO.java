package ca.peterzhu.basestation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ca.peterzhu.basestation.dao.bean.AntennaBean;
import ca.peterzhu.basestation.dao.bean.BaseStationBean;
import ca.peterzhu.basestation.dao.bean.CabinetBean;

/**
 * This class contains all the methods to create, update, delete and retrieve
 * BaseStationBean Objects. The methods in this class assumes the SQL server
 * will automatically commit the changes.
 * 
 * <p>
 * The connection to the SQL server will be done using the JDBC (Java Database
 * Connectivity) framework.
 * 
 * <p>
 * This class utilizes the AntennaDAO and CabinetDAO to function.
 * 
 * @author Peter Zhu
 * @version 3.0
 */
public class BaseStationDAO {
	private final String TABLE_NAME;
	private CabinetDAO cabinetDAO;
	private TxBoardDAO txBoardDAO;
	private AntennaDAO antennaDAO;

	/**
	 * Initializes all the fields to their default values.
	 */
	public BaseStationDAO() {
		TABLE_NAME = "basestation";

		antennaDAO = new AntennaDAO();
		txBoardDAO = new TxBoardDAO();
		cabinetDAO = new CabinetDAO(txBoardDAO);
	}

	/**
	 * Writes all the fields of the BaseStationBean passed in. Loops through the
	 * list of CabinetBean and AntennaBean and creates them through their DAO.
	 * 
	 * @param baseStation
	 *            the BaseStationBean to create
	 * @throws SQLException
	 *             thrown when a SQL exception occurs
	 */
	public void create(BaseStationBean baseStation) throws SQLException {
		baseStation.setUniqueId(generateUID());
		String sqlStatement = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?, ?)";
		Connection connection = null;
		try {
			// Adds an entry to the SQL server
			connection = SQLConnector.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);

			prepStmt.setString(1, baseStation.getName());
			prepStmt.setString(2, baseStation.getUniqueId());
			prepStmt.setDouble(3, baseStation.getLongitude());
			prepStmt.setDouble(4, baseStation.getLatitude());
			prepStmt.setInt(5, baseStation.getAltitude());

			prepStmt.execute();
		} finally {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}

		// Adds the Cabinets in the Base Station
		for (CabinetBean c : baseStation.getCabinets()) {
			cabinetDAO.create(c, baseStation.getUniqueId());
		}

		// Adds the Antennas in the Base Station
		for (AntennaBean a : baseStation.getAntennas()) {
			antennaDAO.create(a, baseStation.getUniqueId());
		}
	}

	/**
	 * Updates an existing Base Station from the SQL server. Updates the entry
	 * with the same unique ID as the BaseStationBean passed in. Also updates
	 * all the Cabinets and Antennas by deleting all and then recreating
	 * them.
	 * 
	 * @param baseStation
	 *            the BaseStationBean to update
	 * @throws SQLException
	 *             thrown when a SQL exception occurs
	 */
	public void update(BaseStationBean baseStation) throws SQLException {
		String sqlStatement = "UPDATE " + TABLE_NAME
				+ " SET name=?, longitude=?, latitude=?, altitude=? WHERE uniqueid=?";
		Connection connection = null;
		try {
			// Updates the entry in the SQL server
			connection = SQLConnector.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);

			prepStmt.setString(1, baseStation.getName());
			prepStmt.setDouble(2, baseStation.getLongitude());
			prepStmt.setDouble(3, baseStation.getLatitude());
			prepStmt.setInt(4, baseStation.getAltitude());
			prepStmt.setString(5, baseStation.getUniqueId());

			prepStmt.execute();
		} finally {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}

		// Recreates all the Cabinets
		cabinetDAO.deleteAll(baseStation.getUniqueId());
		for (CabinetBean c : baseStation.getCabinets()) {
			cabinetDAO.create(c, baseStation.getUniqueId());
		}

		// Recrceates all the Antennas
		antennaDAO.deleteAll(baseStation.getUniqueId());
		for (AntennaBean a : baseStation.getAntennas()) {
			antennaDAO.create(a, baseStation.getUniqueId());
		}
	}

	/**
	 * Removes the BaseStationBean passed from the SQL server. Removes all the entries in
	 * the SQL server with the unique ID of the BaseStationBean passed in.
	 * 
	 * @param b
	 *            the BaseStationBean to remove
	 * @throws SQLException
	 *             thrown when a SQL exception occurs
	 */
	public void delete(BaseStationBean b) throws SQLException {
		delete(b.getUniqueId());
	}

	/**
	 * Removes a BaseStationBean from the SQL server. Removes all the entries in
	 * the SQL server with the unique ID passed in.
	 * 
	 * @param uid
	 *            the unique ID of the Base Station to remove
	 * @throws SQLException
	 *             thrown when a SQL exception occurs
	 */
	public void delete(String uid) throws SQLException {
		String sqlStatement = "DELETE FROM " + TABLE_NAME + " WHERE uniqueid=?";
		Connection connection = null;
		try {
			// Removes a entry from the SQl server
			connection = SQLConnector.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);

			prepStmt.setString(1, uid);

			prepStmt.execute();

			cabinetDAO.deleteAll(uid);
			antennaDAO.deleteAll(uid);
		} finally {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}
	}

	/**
	 * Returns a list of all the Base Stations in the SQL server packaged as
	 * BaseStationBean objects.
	 * 
	 * @return the list of BaseStationBean
	 * @throws SQLException
	 *             thrown when a SQL exception occurs
	 */
	public List<BaseStationBean> retrieveAll() throws SQLException {
		String sqlStatement = "SELECT * FROM " + TABLE_NAME;
		Connection connection = null;
		try {
			// Reads from the SQL server
			connection = SQLConnector.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);
			ResultSet result = prepStmt.executeQuery();

			// Takes the results and creates the list of Base Stations
			List<BaseStationBean> baseStationList = new ArrayList<>();
			while (result.next()) {
				String name = result.getString(1);
				String UID = result.getString(2);
				double lng = result.getDouble(3);
				double lat = result.getDouble(4);
				int alt = result.getInt(5);
				List<CabinetBean> cabBeanList = cabinetDAO.retrieve(UID);
				List<AntennaBean> antBeanList = antennaDAO.retrieve(UID);

				baseStationList.add(new BaseStationBean(name, UID, lng, lat, alt, cabBeanList, antBeanList));
			}

			return baseStationList;
		} finally {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}
	}

	/**
	 * Generates a unique 10 digit alphanumeric ID for a new Base Station.
	 * 
	 * @return a unique 10 digit alphanumeric ID
	 * @throws SQLException
	 *             thrown when a SQL exception occurs
	 */
	private String generateUID() throws SQLException {
		String UID = "";

		Random r = new Random();

		// Generates 10 random numbers and characters placed in a string
		for (int i = 0; i < 10; i++) {
			int randomNum = r.nextInt(36);

			if (randomNum > 9) {
				UID += (char) (randomNum + 55);
			} else {
				UID += randomNum;
			}
		}

		boolean exists = false;

		// Checks if the UID is used
		String sqlStatement = "SELECT * FROM " + TABLE_NAME + " WHERE uniqueid=?;";
		Connection connection = null;
		try {
			connection = SQLConnector.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);
			prepStmt.setString(1, UID);

			exists = prepStmt.executeQuery().next();
		} finally {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}

		// Recursively generates another one if it exists
		if (exists)
			return generateUID();
		else
			return UID;

	}
}
