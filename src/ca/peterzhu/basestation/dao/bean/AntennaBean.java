package ca.peterzhu.basestation.dao.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("antennaBean")
@ViewScoped
public class AntennaBean implements Serializable {
	private int slotNumber;
	private int height;
	private int azimuth;
	private int downtilt;

	/**
	 * This constructor initializes the bean with blank fields.
	 */
	public AntennaBean() {
		this(0, 0, 0, 0);
	}

	public AntennaBean(int slotNumber, int h, int a, int d) {
		this.slotNumber = slotNumber;
		this.height = h;
		this.azimuth = a;
		this.downtilt = d;
	}

	public AntennaBean(AntennaBean a) {
		this.slotNumber = a.getSlotNumber();
		this.height = a.getHeight();
		this.azimuth = a.getAzimuth();
		this.downtilt = a.getDowntilt();
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof AntennaBean) {
			AntennaBean ab = (AntennaBean) o;

			return ab.getSlotNumber() == this.getSlotNumber() && ab.getHeight() == this.getHeight()
					&& ab.getDowntilt() == this.getDowntilt() && ab.getAzimuth() == this.getAzimuth();
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

	public void clearFields() {
		slotNumber = 0;
		height = 0;
		azimuth = 0;
		downtilt = 0;
	}

}
