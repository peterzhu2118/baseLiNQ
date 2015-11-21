package ca.peterzhu.basestation.dao;

import java.sql.SQLException;
import java.util.List;

import ca.peterzhu.basestation.dao.bean.BaseStationBean;

public class SQLTester {

	public static void main(String[] args) throws SQLException {
		BaseStationDAO bsd = new BaseStationDAO();
		
		//bsd.delete("QELHQPSUJ2");
		
		/*BaseStationBean bsb = new BaseStationBean();
		bsb.setName("Peter's test");
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
		
		bsb.addAntenna(ab);
		
		bsd.create(bsb);*/
		
		List<BaseStationBean> bsb = bsd.retrieveAll();
		System.out.println(bsb == null);
		System.out.println(bsb.size());
		
	}

}
