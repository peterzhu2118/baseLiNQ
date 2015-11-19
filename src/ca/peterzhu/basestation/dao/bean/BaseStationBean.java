package ca.peterzhu.basestation.dao.bean;

import java.util.ArrayList;
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
	private int altitude;
	private List<CabinetBean> cabinets;
	private List<AntennaBean> antennas;

	public BaseStationBean() {
		this("", "", 0.0, 0.0, 0, new ArrayList<CabinetBean>(), new ArrayList<AntennaBean>());
	}

	public BaseStationBean(String n, String uid, double lng, double lat, int alt, List<CabinetBean> cab,
			List<AntennaBean> ant) {
		this.name = n;
		this.uniqueId = uid;
		this.latitude = lat;
		this.longitude = lng;
		this.altitude = alt;
		this.cabinets = cab;
		this.antennas = ant;
		
		ensureAntennaOrder();
		ensureCabinetOrder();
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof BaseStationBean) {
			return ((BaseStationBean) o).getUniqueId().equals(uniqueId);
		} else
			return false;
	}

	@Override
	public String toString() {
		return name;
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
	public int getAltitude() {
		return altitude;
	}

	/**
	 * @param altitude
	 *            the altitude of the Base Station
	 */
	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}

	public List<CabinetBean> getCabinets() {
		return cabinets;
	}

	public void addCabinet(CabinetBean c) {
		c.setSlotNumber(cabinets.size());

		cabinets.add(c);

		ensureCabinetOrder();
	}

	public List<AntennaBean> getAntennas() {
		return antennas;
	}

	public void addAntenna(AntennaBean a) {
		a.setSlotNumber(antennas.size());

		antennas.add(a);

		ensureAntennaOrder();
	}

	private void ensureCabinetOrder() {
		for (int i = 0; i < cabinets.size(); i++) {
			if (cabinets.get(i).getSlotNumber() != (i + 1)) {
				cabinets.get(i).setSlotNumber(i + 1);
			}
		}
	}

	private void ensureAntennaOrder() {
		for (int i = 0; i < antennas.size(); i++) {
			if (antennas.get(i).getSlotNumber() != (i + 1)) {
				antennas.get(i).setSlotNumber(i + 1);
			}
		}
	}

	private void clearFields() {
		uniqueId = "";
		name = "";
		longitude = 0;
		latitude = 0;
		altitude = 0;
		cabinets = new ArrayList<>();
		antennas = new ArrayList<>();
	}

}
