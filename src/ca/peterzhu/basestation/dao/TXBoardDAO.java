package ca.peterzhu.basestation.dao;

import java.sql.SQLException;

import ca.peterzhu.basestation.dao.bean.TXBoardBean;

/**
 * 
 * @author Peter Zhu
 * @version 3.0
 */
public class TXBoardDAO {
	private static final String TABLE_NAME = "txboard";

	public static void addTXBoard(TXBoardBean t, String baseStationID, int cabinetID) throws SQLException {
		SQLConnector.executeStatement("insert into " + TABLE_NAME + " values('" + baseStationID + "', " + cabinetID
				+ ", " + ", " + t.getSlotNumber() + ", " + t.getTransmitPower() + ", " + t.getFrequency() + ");");
	}

	public static void updateTXBoard(TXBoardBean t, String baseStationID, int cabinetID) throws SQLException {
		SQLConnector.executeStatement("update " + TABLE_NAME + " set " + " where basestationid='" + baseStationID
				+ "' and cabinetslotnumber=" + cabinetID + ";");
	}
}