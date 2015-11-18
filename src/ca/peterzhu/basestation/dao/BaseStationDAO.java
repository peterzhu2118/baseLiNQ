package ca.peterzhu.basestation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import ca.peterzhu.basestation.dao.bean.AntennaBean;
import ca.peterzhu.basestation.dao.bean.BaseStationBean;
import ca.peterzhu.basestation.dao.bean.CabinetBean;

/**
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
		String sqlStatement = "insert into ? values (?, ?, ?, ?, ?)";
		Connection connection = null;
		try {
			connection = SQLConnector.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);
			prepStmt.setString(1, TABLE_NAME);
			prepStmt.setString(2, baseStation.getName());
			prepStmt.setString(3, generateUID());
			prepStmt.setDouble(4, baseStation.getLongitude());
			prepStmt.setDouble(5, baseStation.getLatitude());
			prepStmt.setInt(6, baseStation.getAltitude());

			prepStmt.execute();
			connection.commit();
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

	public void update(BaseStationBean b) throws SQLException {
		String sqlStatement = "update ? set name=?, longitude=?, latitude=?, altitude=? where uniqueid=?";
		Connection connection = null;
		try {
			connection = SQLConnector.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);

			prepStmt.setString(1, TABLE_NAME);
			prepStmt.setString(2, b.getName());
			prepStmt.setDouble(3, b.getLongitude());
			prepStmt.setDouble(4, b.getLatitude());
			prepStmt.setInt(5, b.getAltitude());
			prepStmt.setString(6, b.getUniqueId());

			prepStmt.execute();
			connection.commit();
		} finally {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}
	}

	public void delete(BaseStationBean b) throws SQLException {
		delete(b.getUniqueId());
	}

	public void delete(String uid) throws SQLException {
		String sqlStatement = "delete from ? where uniqueid=?";
		Connection connection = null;
		try {
			connection = SQLConnector.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);

			prepStmt.setString(1, TABLE_NAME);
			prepStmt.setString(2, uid);

			prepStmt.execute();
			connection.commit();
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

		boolean exists = false;;

		/*String sqlStatement = "select * from ? where uniqueid=?";
		Connection connection = null;
		try {
			connection = SQLConnector.getConnection();
			PreparedStatement prepStmt = connection.prepareStatement(sqlStatement);
			prepStmt.setString(1, TABLE_NAME);
			prepStmt.setString(2, UID);

			exists = prepStmt.execute();
			connection.commit();
		} finally {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		}*/

		if (exists)
			return generateUID();
		else
			return UID;

	}
}
