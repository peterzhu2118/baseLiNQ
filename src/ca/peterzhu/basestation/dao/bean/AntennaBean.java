package ca.peterzhu.basestation.dao.bean;

/**
 * 
 * @author Peter Zhu
 * @version 3.0
 */
public class AntennaBean {
	private int ID;
	private int height;
	private int azimuth;
	private int downtilt;

	public AntennaBean() {

	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * @param iD
	 *            the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
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
