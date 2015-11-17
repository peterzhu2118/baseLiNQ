package ca.peterzhu.basestation.dao;

import java.sql.SQLException;

import ca.peterzhu.basestation.dao.bean.CabinetBean;

/**
 * 
 * @author Peter Zhu
 * @version 3.0
 */
public class CabinetDAO {
	private static final String TABLE_NAME = "cabinet";

	public static void createCabinet(CabinetBean c, String baseStationUID) throws SQLException {
		SQLConnector.executeStatement(
				"insert into " + TABLE_NAME + " values('" + baseStationUID + "', " + c.getSlotNumber() + ");");
	}

	public static void removeCabinet() {

	}

	public static void removeAllCabinets(String baseStationUID) throws SQLException {
		SQLConnector.executeStatement("delete from " + TABLE_NAME + " where basestationid='" + baseStationUID + "';");
	}
}
