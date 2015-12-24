package ca.peterzhu.basestation.dao.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * Contains all the fields to represent a Antenna. This is a managed
 * bean using CDI to interact with the JSF frontend.
 * 
 * @author Peter Zhu
 * @version 3.0
 */
@Named("antennaBean")
@ViewScoped
public class AntennaBean implements Serializable {
	private int slotNumber;
	private int height;
	private int azimuth;
	private int downtilt;
	private String baseStationPage;

	/**
	 * Initializes the bean with blank fields.
	 */
	public AntennaBean() {
		this(0, 0, 0, 0);
	}

	/**
	 * Initializes with fields passed in.
	 * 
	 * @param s
	 *            slot number to set
	 * @param h
	 *            height to set
	 * @param a
	 *            azimuth to set
	 * @param d
	 *            downtilt to set
	 */
	public AntennaBean(int s, int h, int a, int d) {
		this.slotNumber = s;
		this.height = h;
		this.azimuth = a;
		this.downtilt = d;
	}

	/**
	 * Initializes with a copy of the AntennaBean passed in.
	 * 
	 * @param a
	 *            the AntennaBean to be copied
	 */
	public AntennaBean(AntennaBean a) {
		this.slotNumber = a.getSlotNumber();
		this.height = a.getHeight();
		this.azimuth = a.getAzimuth();
		this.downtilt = a.getDowntilt();
	}

	/**
	 * Overrides the equals method in the Object class. Compares all the fields
	 * of this Object and the Object passed in.
	 * 
	 * @param o
	 *            the Object to be compared
	 * @return true if the Object passed in is equal to this Object; false otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof AntennaBean) {
			AntennaBean ab = (AntennaBean) o;

			return ab.getSlotNumber() == this.getSlotNumber() && ab.getHeight() == this.getHeight()
					&& ab.getDowntilt() == this.getDowntilt() && ab.getAzimuth() == this.getAzimuth();
		} else
			return false;
	}

	/**
	 * Sets this Object with the Object passed in.
	 * 
	 * @param o
	 *            the Object to set this Object to
	 * @param redirect
	 *            the URL to be redirected
	 * @return the redirect URL
	 */
	public String setThisObject(Object o, String redirect) {
		if (o instanceof AntennaBean) {
			// System.out.println("Set Object");

			AntennaBean a = (AntennaBean) o;

			// System.out.println("Height: " + a.getHeight());

			this.slotNumber = a.getSlotNumber();
			this.height = a.getHeight();
			this.azimuth = a.getAzimuth();
			this.downtilt = a.getDowntilt();

			return redirect;
		} else
			throw new IllegalArgumentException("Object not instance of AntennaBean");
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
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the azimuth
	 */
	public int getAzimuth() {
		return azimuth;
	}

	/**
	 * @param azimuth
	 *            the azimuth to set
	 */
	public void setAzimuth(int azimuth) {
		this.azimuth = azimuth;
	}

	/**
	 * @return the downtilt
	 */
	public int getDowntilt() {
		return downtilt;
	}

	/**
	 * @param downtilt
	 *            the downtilt to set
	 */
	public void setDowntilt(int downtilt) {
		this.downtilt = downtilt;
	}

	/**
	 * This method is only used in HTML for temporary variable storage.
	 * 
	 * @return the Base Station page URL
	 */
	public String getBaseStationPage() {
		return baseStationPage;
	}

	/**
	 * This method is only used in HTML for temporary variable storage.
	 * 
	 * @param baseStationPage
	 *            the Base Station page URL to set
	 */
	public void setBaseStationPage(String baseStationPage) {
		this.baseStationPage = baseStationPage;
	}

	/**
	 * Clears all the fields with empty (non null) values.
	 */
	public void clearFields() {
		slotNumber = 0;
		height = 0;
		azimuth = 0;
		downtilt = 0;
		baseStationPage = "";
	}

}
