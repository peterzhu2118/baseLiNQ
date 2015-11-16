package ca.peterzhu.basestation.dao.bean;

/**
 * 
 * @author Peter Zhu
 * @version 3.0
 */
public class AntennaBean {
	private int slotNumber;
	private int height;
	private int azimuth;
	private int downtilt;

	public AntennaBean() {
		this(0, 0, 0);
	}

	public AntennaBean(int h, int a, int d) {
		this.height = h;
		this.azimuth = a;
		this.downtilt = d;
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

}
