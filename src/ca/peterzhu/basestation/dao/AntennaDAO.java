package ca.peterzhu.basestation.dao;

import java.sql.SQLException;

import ca.peterzhu.basestation.dao.bean.AntennaBean;

/**
 * 
 * @author Peter Zhu
 * @version 3.0
 */
public class AntennaDAO {
	private static final String TABLE_NAME = "antenna";

	public static void createAntenna(AntennaBean a, String baseStationID) throws SQLException {
		SQLConnector.executeStatement("insert into " + TABLE_NAME + " values('" + baseStationID + "', "
				+ a.getSlotNumber() + ", " + a.getHeight() + ", " + a.getAzimuth() + ", " + a.getHeight() + ");");
	}

	public static void updateAntenna(AntennaBean a, String baseStationID) throws SQLException {
		SQLConnector.executeStatement("update " + TABLE_NAME + " set height=" + a.getHeight() + ", azimuth="
				+ a.getAzimuth() + ", downtilt=" + a.getDowntilt() + " where basestationid='" + baseStationID
				+ "' and slotnumber=" + a.getSlotNumber() + ";");
	}

	public static void deleteAntenna(int antennaID, String baseStationID) throws SQLException {
		SQLConnector.executeStatement("delete from " + TABLE_NAME + " where basestationid='" + baseStationID
				+ "' and slotnumber=" + antennaID + ";");
	}

	public static void deleteAntenna(AntennaBean a, String baseStationID) throws SQLException {
		deleteAntenna(a.getSlotNumber(), baseStationID);
	}
}
