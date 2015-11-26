package ca.peterzhu.basestation.dao.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ca.peterzhu.basestation.dao.BaseStationDAO;

/**
 * 
 * @author Peter Zhu
 * @version 3.0
 */
@Named("baseStationBean")
@ConversationScoped
public class BaseStationBean implements Serializable {
	private String name;
	private String uniqueId;
	private double longitude;
	private double latitude;
	private int altitude;
	private List<CabinetBean> cabinets;
	private List<AntennaBean> antennas;

	@Inject
	private Conversation conversation;

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
	
	@PostConstruct
	private void init(){
		beginConversation();
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
	
	private void beginConversation(){
		System.out.println("Begin");
		conversation.begin();
	}
	
	private void endConversation(){
		System.out.println("End");
		conversation.end();
	}

	public String setThisObject(BaseStationBean b, String redirect) {
		this.name = b.getName();
		this.uniqueId = b.getUniqueId();
		this.latitude = b.getLatitude();
		this.longitude = b.getLongitude();
		this.altitude = b.getAltitude();
		this.cabinets = b.getCabinets();
		this.antennas = b.getAntennas();

		ensureAntennaOrder();
		ensureCabinetOrder();

		return redirect;
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
		System.out.println("Set name");
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
	
	public void removeAntenna(AntennaBean a) {
		antennas.remove(a);
	}

	public String addAntenna(AntennaBean a, String redirect) {
		a.setSlotNumber(antennas.size());

		antennas.add(new AntennaBean(a));
		
		a.clearFields();

		ensureAntennaOrder();

		return redirect;
	}

	public String create(String redirect) throws SQLException {
		BaseStationDAO dao = new BaseStationDAO();
		System.out.println("Unique ID: " + uniqueId);
		System.out.println("Unique ID: " + (uniqueId == ""));

		if (uniqueId != null && uniqueId != "") {
			throw new IllegalStateException("Unique ID is not blank for new Base Station");
		}

		dao.create(this);

		clearFields();

		return redirect;
	}

	public String update(String redirect) throws SQLException {
		BaseStationDAO dao = new BaseStationDAO();

		if (uniqueId == null || uniqueId == "") {
			throw new IllegalStateException("Unique ID is blank for saving Base Station");
		}

		dao.create(this);

		clearFields();

		return redirect;
	}

	public String discard(String redirect) {
		clearFields();

		return redirect;
	}

	public void delete() throws SQLException, InterruptedException {
		System.out.println("Delete");

		BaseStationDAO dao = new BaseStationDAO();

		if (uniqueId == null || uniqueId == "") {
			throw new IllegalStateException("Unique ID is blank for deleting Base Station");
		}

		dao.delete(uniqueId);

		clearFields();
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
