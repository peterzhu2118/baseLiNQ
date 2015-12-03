package ca.peterzhu.basestation.dao.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * Contains all the the fields to represent a TX Board. This is a managed bean
 * using CDI to interact with the HTML JSF pages.
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

	/**
	 * Initializes all the fields with their empty (non-null) values.
	 */
	public TxBoardBean() {
		this(0, 0, 0);
	}

	/**
	 * Initializes all the fields with the parameters passed in.
	 * 
	 * @param slot
	 *            the slot number to set
	 * @param power
	 *            the transmit power to set
	 * @param freq
	 *            the frequency to set
	 */
	public TxBoardBean(int slot, int power, int freq) {
		this.slotNumber = slot;
		this.transmitPower = power;
		this.frequency = freq;
	}

	/**
	 * Initializes all the fields with a copy of the TxBoardBean passed in.
	 * 
	 * @param t
	 *            the TxBoardBean to be copied
	 */
	public TxBoardBean(TxBoardBean t) {
		this.slotNumber = t.getSlotNumber();
		this.transmitPower = t.getTransmitPower();
		this.frequency = t.getFrequency();
	}

	/**
	 * Overrides the equals method in the Object class. Compares all the fields
	 * (slot number, frequency and transmit power) of the TxBoardBean passed in
	 * and this TxBoardBean.
	 * 
	 * @param o
	 *            the Object to compare this TxBoardBean to
	 * @return true if this Object is the same as the one passed in; false
	 *         otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof TxBoardBean) {
			TxBoardBean t = (TxBoardBean) o;

			return t.getSlotNumber() == this.getSlotNumber() && t.getFrequency() == this.getFrequency()
					&& t.getTransmitPower() == this.getTransmitPower();
		} else
			return false;
	}

	/**
	 * @return the slot number
	 */
	public int getSlotNumber() {
		return slotNumber;
	}

	/**
	 * @param slotNumber
	 *            the slot number to set
	 */
	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}

	/**
	 * @return the transmit power
	 */
	public int getTransmitPower() {
		return transmitPower;
	}

	/**
	 * @param transmitPower
	 *            the transmit power to set
	 */
	public void setTransmitPower(int transmitPower) {
		this.transmitPower = transmitPower;
	}

	/**
	 * @return the frequency
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * This method is only used in HTML for temporary variable storage.
	 * 
	 * @param frequency
	 *            the frequency to set
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/**
	 * This method is only used in HTML for temporary variable storage.
	 * 
	 * @return the cabinet page to set
	 */
	public String getCabinetPage() {
		return cabinetPage;
	}

	/**
	 * This method is only used in HTML for temporary variable storage.
	 * 
	 * @param cabinetPage
	 *            the cabinetPage to set
	 */
	public void setCabinetPage(String cabinetPage) {
		this.cabinetPage = cabinetPage;
	}

	/**
	 * Discards changes or creation of the TxBoardBean by clearing all the
	 * fields.
	 * 
	 * @param redirect
	 *            the redirect URL to return
	 * @return the redirect URL
	 */
	public String discard(String redirect) {
		clearFields();

		return redirect;
	}

	/**
	 * Clears all the fields to their empty (non-null) values.
	 */
	private void clearFields() {
		slotNumber = 0;
		frequency = 0;
		transmitPower = 0;
	}

}
