package ca.peterzhu.basestation.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

import ca.peterzhu.basestation.dao.bean.BaseStationBean;

/**
 * 
 * @author Peter
 * @version 3.0
 */
public class BaseStationDAO {
	private static String TABLE_NAME = "basestation";
	private Connection connection;

	public BaseStationDAO(Connection connection) {
		this.connection = connection;
	}

	public static void createBaseStation(BaseStationBean b) throws SQLException {
		SQLConnector.executeStatement("insert into " + TABLE_NAME + " values ('" + b.getName() + "', '" + generateUID()
				+ "', " + b.getLongitude() + ", " + b.getLatitude() + ", " + b.getAltitude() + ");");
	}

	public static void updateBaseStation(BaseStationBean b) throws SQLException {
		SQLConnector.executeStatement("update " + TABLE_NAME + " set name='" + b.getName() + "', longitude="
				+ b.getLongitude() + ", latitude=" + b.getLatitude() + ", altitude=" + b.getAltitude() + " where uid="
				+ b.getUniqueId() + ";");
	}

	public static void deleteBaseStation(BaseStationBean b) throws SQLException {
		deleteBaseStation(b.getUniqueId());
	}

	public static void deleteBaseStation(String uid) throws SQLException {
		SQLConnector.executeStatement("delete from " + TABLE_NAME + "where uid=" + uid + ";");
	}

	private static String generateUID() throws SQLException {
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

		if (SQLConnector.executeStatement("select * from " + TABLE_NAME + " where uid=" + UID + ";"))
			return generateUID();
		else
			return UID;

	}
}
