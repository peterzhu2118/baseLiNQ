package ca.peterzhu.basestation.dao.bean;

/**
 * 
 * @author Peter Zhu
 * @version 3.0
 */
public class TXBoardBean {
	private int slotNumber;
	private int transmitPower;
	private int frequency;

	public TXBoardBean(){
		
	}
	

	public int getSlotNumber() {
		return slotNumber;
	}


	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
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
