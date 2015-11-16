package ca.peterzhu.basestation.dao.bean;

import java.util.List;

/**
 * 
 * @author Peter Zhu
 * @version 3.0
 */
public class CabinetBean {
	private String baseStationUID;
	private int cabinetNumber;
	private List<TXBoardBean> txBoards;

	public CabinetBean() {

	}

	/**
	 * Returns the 10 digit alphanumeric UID (Unique ID) of the Base Station
	 * that this Cabinet is bound to.
	 * 
	 * @return the UID of the Base Station
	 */
	public String getBaseStationUID() {
		return baseStationUID;
	}

	/**
	 * Sets the 10 digit alphanumeric UID (Unique ID) of the Base Station that
	 * this Cabinet is bound to.
	 * 
	 * @param baseStationUID
	 *            the UID of the Base Station
	 */
	public void setBaseStationUID(String baseStationUID) {
		this.baseStationUID = baseStationUID;
	}

	/**
	 * @return the ID of this Cabinet
	 */
	public int getCabinetNumber() {
		return cabinetNumber;
	}

	/**
	 * @param cabinetNumber
	 *            the ID of this Cabinet
	 */
	public void setCabinetNumber(int cabinetNumber) {
		this.cabinetNumber = cabinetNumber;
	}

	public void addTXBoard(TXBoardBean t) {

	}

	private void ensureTXBoardOrder() {
		for (int i = 0; i < txBoards.size(); i++) {
			if (txBoards.get(i).getSlotNumber() != (i + 1)) {
				
			}
		}
	}

}
