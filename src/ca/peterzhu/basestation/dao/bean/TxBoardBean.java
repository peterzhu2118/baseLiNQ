package ca.peterzhu.basestation.dao.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * 
 * @author Peter Zhu
 * @version 3.0
 */
@Named("txBoardBean")
@ViewScoped
public class TxBoardBean implements Serializable {
	private int slotNumber;
	private int transmitPower;
	private int frequency;
	private String cabinetPage;

	public TxBoardBean() {
		this(0, 0, 0);
	}

	public TxBoardBean(int slot, int power, int freq) {
		this.slotNumber = slot;
		this.transmitPower = power;
		this.frequency = freq;
	}

	public TxBoardBean(TxBoardBean t) {
		this.slotNumber = t.getSlotNumber();
		this.transmitPower = t.getTransmitPower();
		this.frequency = t.getFrequency();
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof TxBoardBean) {
			TxBoardBean t = (TxBoardBean) o;

			return t.getSlotNumber() == this.getSlotNumber() && t.getFrequency() == this.getFrequency()
					&& t.getTransmitPower() == this.getTransmitPower();
		} else
			return false;
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

	/**
	 * @return the cabinetPage
	 */
	public String getCabinetPage() {
		return cabinetPage;
	}

	/**
	 * @param cabinetPage
	 *            the cabinetPage to set
	 */
	public void setCabinetPage(String cabinetPage) {
		this.cabinetPage = cabinetPage;
	}

	public String discard(String redirect) {
		clearFields();

		return redirect;
	}

	private void clearFields() {
		slotNumber = 0;
		frequency = 0;
		transmitPower = 0;
	}

}
