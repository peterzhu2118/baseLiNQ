package ca.peterzhu.basestation.dao;

import java.sql.Connection;
import java.sql.SQLException;

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
		SQLConnector.executeStatement("insert into " + TABLE_NAME + " values ('" + b.getName() + "', '" + b.getUID()
				+ "', " + b.getID() + ", " + b.getLongitude() + ", " + b.getLatitude() + b.getAltitude() + ")");
	}

	public static void updateBaseStation(BaseStationBean b) {
		
	}
}
