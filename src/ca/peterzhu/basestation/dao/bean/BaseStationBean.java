package ca.peterzhu.basestation.dao.bean;

/**
 * 
 * @author Peter Zhu
 * @version 3.0
 */
public class BaseStationBean {
	private String name;
	private long ID;
	private double longitude;
	private double latitude;
	private String UID;
	private short altitude;

	public BaseStationBean() {
		this("", 0l, "", 0.0, 0.0, (short) 0);
	}

	public BaseStationBean(String n, long id, String uid, double lat, double lng, short alt) {
		this.name = n;
		this.ID = id;
		this.UID = uid;
		this.latitude = lat;
		this.longitude = lng;
		this.altitude = alt;
	}

	/**
	 * @return name of the Base Station
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name of the Base Station
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the ID of the Base Station
	 */
	public long getID() {
		return ID;
	}

	/**
	 * @param ID
	 *            the ID of the Base Station
	 */
	public void setID(long ID) {
		this.ID = ID;
	}

	/**
	 * @return the longitude of the Base Station
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            the latitude of the Base Station
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the latitude of the Base Station
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude of the Base Station
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * Returns the 10 digit alphanumeric UID (Unique ID) of the Base Station.
	 * 
	 * @return the UID of the Base Station
	 */
	public String getUID() {
		return UID;
	}

	/**
	 * Sets the 10 digit alphanumeric UID (Unique ID) of the Base Station.
	 * 
	 * @param UID
	 *            the uID to set
	 */
	public void setUID(String UID) {
		this.UID = UID;
	}

	/**
	 * @return the altitude of the Base Station
	 */
	public short getAltitude() {
		return altitude;
	}

	/**
	 * @param altitude
	 *            the altitude of the Base Station
	 */
	public void setAltitude(short altitude) {
		this.altitude = altitude;
	}

}
