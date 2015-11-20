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
 * BaseStationBean Objects. The connection is done using JDBC's
 * java.sql.Connection class and JDBC's java.sql.PreparedStatement class. The
 * methods in this class assumes the SQL server will automatically commit the
 * changes.
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

	public BaseStationDAO() {
		TABLE_NAME = "basestation";

		antennaDAO = new AntennaDAO();
		txBoardDAO = new TxBoardDAO();
		cabinetDAO = new CabinetDAO(txBoardDAO);
	}

	public void create(BaseStationBean baseStation) throws SQLException {
		baseStation.setUniqueId(generateUID());
		String sqlStatement = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?, ?)";
		Connection connection = null;
		try {
			connection = SQLConnector.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);

			prepStmt.setString(1, baseStation.getName());
			prepStmt.setString(2, baseStation.getUniqueId());
			prepStmt.setDouble(3, baseStation.getLongitude());
			prepStmt.setDouble(4, baseStation.getLatitude());
			prepStmt.setInt(5, baseStation.getAltitude());

			prepStmt.execute();
			// connection.commit();
		} finally {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}

		for (CabinetBean c : baseStation.getCabinets()) {
			cabinetDAO.create(c, baseStation.getUniqueId());
		}

		for (AntennaBean a : baseStation.getAntennas()) {
			antennaDAO.create(a, baseStation.getUniqueId());
		}
	}

	public void update(BaseStationBean baseStation) throws SQLException {
		String sqlStatement = "UPDATE " + TABLE_NAME
				+ " SET name=?, longitude=?, latitude=?, altitude=? WHERE uniqueid=?";
		Connection connection = null;
		try {
			connection = SQLConnector.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);

			prepStmt.setString(1, baseStation.getName());
			prepStmt.setDouble(2, baseStation.getLongitude());
			prepStmt.setDouble(3, baseStation.getLatitude());
			prepStmt.setInt(4, baseStation.getAltitude());
			prepStmt.setString(5, baseStation.getUniqueId());

			prepStmt.execute();
			// connection.commit();
		} finally {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}

		cabinetDAO.deleteAll(baseStation.getUniqueId());
		antennaDAO.deleteAll(baseStation.getUniqueId());
		for (CabinetBean c : baseStation.getCabinets()) {
			cabinetDAO.create(c, baseStation.getUniqueId());
		}

		antennaDAO.deleteAll(baseStation.getUniqueId());
		for (AntennaBean a : baseStation.getAntennas()) {
			antennaDAO.create(a, baseStation.getUniqueId());
		}
	}

	public void delete(BaseStationBean b) throws SQLException {
		delete(b.getUniqueId());
	}

	public void delete(String uid) throws SQLException {
		String sqlStatement = "DELETE FROM " + TABLE_NAME + " WHERE uniqueid=?";
		Connection connection = null;
		try {
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

	public List<BaseStationBean> retrieveAll() throws SQLException {
		String sqlStatement = "SELECT * FROM " + TABLE_NAME;
		Connection connection = null;
		try {
			connection = SQLConnector.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);
			ResultSet result = prepStmt.executeQuery();

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
		;

		String sqlStatement = "SELECT * FROM " + TABLE_NAME + " WHERE uniqueid=?;";
		Connection connection = null;
		try {
			connection = SQLConnector.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);
			prepStmt.setString(1, UID);

			exists = prepStmt.executeQuery().next();
			// connection.commit();
		} finally {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}

		if (exists)
			return generateUID();
		else
			return UID;

	}
}
