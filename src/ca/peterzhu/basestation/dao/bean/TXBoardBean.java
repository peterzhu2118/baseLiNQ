package ca.peterzhu.basestation.dao.bean;

/**
 * 
 * @author Peter Zhu
 * @version 3.0
 */
public class TXBoardBean {
	private int ID;
	private int transmitPower;
	private int frequency;

	public TXBoardBean(){
		
	}
	
	/**
	 * @return the ID of the TX Board
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param iD
	 *            the ID of the TX Board
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * @return the transmit power of the TX Board
	 */
	public int getTransmitPower() {
		return transmitPower;
	}

	/**
	 * @param transmitPower
	 *            the transmit power of the TX Board
	 */
	public void setTransmitPower(int transmitPower) {
		this.transmitPower = transmitPower;
	}

	/**
	 * @return the frequency of the TX Board
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * @param frequency
	 *            the frequency of the TX Boad
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

}
