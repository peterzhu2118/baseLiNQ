package ca.peterzhu.basestation.dao;

import java.sql.SQLException;

import ca.peterzhu.basestation.dao.bean.AntennaBean;
import ca.peterzhu.basestation.dao.bean.BaseStationBean;
import ca.peterzhu.basestation.dao.bean.CabinetBean;
import ca.peterzhu.basestation.dao.bean.TxBoardBean;

public class SQLTester {

	public static void main(String[] args) throws SQLException {
		BaseStationDAO bsd = new BaseStationDAO();
		
		BaseStationBean bsb = new BaseStationBean();
		bsb.setName("Testing 123");
		bsb.setLongitude(20.1);
		bsb.setLatitude(18.2);
		bsb.setAltitude(10);
		
		CabinetBean cb = new CabinetBean();
		
		TxBoardBean txb = new TxBoardBean();
		txb.setFrequency(21);
		txb.setTransmitPower(6);
		
		cb.addTxBoard(txb);
		
		bsb.addCabinet(cb);
		
		AntennaBean ab = new AntennaBean();
		ab.setAzimuth(7);
		ab.setDowntilt(11);
		ab.setHeight(15);
		
		bsb.addCabinet(cb);
		bsb.addAntenna(ab);
		
		bsd.create(bsb);
	}

}
