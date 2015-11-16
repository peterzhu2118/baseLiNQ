package ca.peterzhu.basestation.dao.bean;

import java.util.List;

/**
 * 
 * @author Peter Zhu
 * @version 3.0
 */
public class BaseStationBean {
	private String name;
	private String uniqueId;
	private double longitude;
	private double latitude;
	private short altitude;
	private List<CabinetBean> cabinets;
	private List<AntennaBean> antennas;

	public BaseStationBean() {
		this("", "", 0.0, 0.0, (short) 0);
	}

	public BaseStationBean(String n, String uid, double lat, double lng, short alt) {
		this.name = n;
		this.uniqueId = uid;
		this.latitude = lat;
		this.longitude = lng;
		this.altitude = alt;
	}
	
	@Override
	public boolean equals(Object o){
		if (o instanceof BaseStationBean){
			return ((BaseStationBean) o).getUniqueId().equals(uniqueId);
		} else
			return false;
	}
	
	@Override
	public String toString(){
		
	}

	/**
	 * Returns the 10 digit alphanumeric UID (Unique ID) of the Base Station.
	 * 
	 * @return the UID of the Base Station
	 */
	public String getUniqueId() {
		return uniqueId;
	}

	/**
	 * Sets the 10 digit alphanumeric UID (Unique ID) of the Base Station.
	 * 
	 * @param uniqueId
	 *            the UID of the Base Station
	 */
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
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
	
	public void add

}
