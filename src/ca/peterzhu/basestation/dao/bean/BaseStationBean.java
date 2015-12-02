package ca.peterzhu.basestation.dao.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ca.peterzhu.basestation.dao.BaseStationDAO;

/**
 * Contains all the the fields to represent a Base Station. This is a managed
 * bean using CDI to interact with the HTML JSF pages.
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

	/**
	 * Constructs an empty non-null value bean.
	 */
	public BaseStationBean() {
		this("", "", 0.0, 0.0, 0, new LinkedList<CabinetBean>(), new LinkedList<AntennaBean>());
	}

	/**
	 * Initializes with the bean with the specified values.
	 * 
	 * @param n
	 *            name of the Base Station
	 * @param uid
	 *            unique ID of the Base Station
	 * @param lng
	 *            longitude of the Base Station
	 * @param lat
	 *            latitude of the Base Station
	 * @param alt
	 *            altitude of the Base Station
	 * @param cab
	 *            cabinets in the Base Station
	 * @param ant
	 *            antennas in the Base Station
	 */
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

	/**
	 * Initializes the bean by elevating it to ConversationScope.
	 */
	@PostConstruct
	private void init() {
		beginConversation();
	}

	/**
	 * Overrides the equals method in the Object class. Compares only the Unique
	 * ID of this Object to the Object passed in.
	 * 
	 * @param o
	 *            The Object to compare this Object to
	 * @return true if the Object passed in is the same as this Object; false
	 *         otherwise
	 * 
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof BaseStationBean) {
			return ((BaseStationBean) o).getUniqueId().equals(uniqueId);
		} else
			return false;
	}

	/**
	 * Overrides the toString method in Object class. Returns the name of the
	 * Base Station.
	 * 
	 * @return the name of the Base Station
	 */
	@Override
	public String toString() {
		return name;
	}

	/**
	 * Elevates this bean into a long running Conversation scope.
	 */
	private void beginConversation() {
		conversation.begin();
	}

	/**
	 * Ends the Conversation and marks the bean into transient.
	 */
	private void endConversation() {
		conversation.end();
	}

	/**
	 * Copies the Object passed in into this Object. Returns the redirect link
	 * passed in (for HTML).
	 * 
	 * @param b
	 *            the Object to be copied
	 * @param redirect
	 *            the link to be redirected
	 * @return the redirect link passed in
	 */
	public String setThisObject(Object b, String redirect) {
		if (b instanceof BaseStationBean) {
			BaseStationBean bsb = (BaseStationBean) b;

			this.name = bsb.getName();
			this.uniqueId = bsb.getUniqueId();
			this.latitude = bsb.getLatitude();
			this.longitude = bsb.getLongitude();
			this.altitude = bsb.getAltitude();
			this.cabinets = bsb.getCabinets();
			this.antennas = bsb.getAntennas();

			ensureAntennaOrder();
			ensureCabinetOrder();

			return redirect;
		} else
			throw new IllegalArgumentException("Object not instance of BaseStationBean");
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
	 *            the UID to set
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
	 *            the name to set
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
	 *            the latitude to set
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
	 *            the latitude to set
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
	 *            the altitude to set
	 */
	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}

	/**
	 * @return the List of CabinetBean
	 */
	public List<CabinetBean> getCabinets() {
		return cabinets;
	}

	/**
	 * Removes the first occurrence of the CabinetBean (found using the equals
	 * method in CabinetBean).
	 * 
	 * @param a
	 *            the CabinetBean to remove
	 */
	public void removeCabinet(CabinetBean a) {
		cabinets.remove(a);

		ensureCabinetOrder();
	}

	/**
	 * Adds a CabinetBean to the end of the List of CabinetBeans.
	 * 
	 * @param c
	 *            the CabinetBean to add to the List
	 * @param redirect
	 *            the redirect link to return
	 * @return the redirect link
	 */
	public String addCabinet(CabinetBean c, String redirect) {
		c.setSlotNumber(cabinets.size());

		cabinets.add(new CabinetBean(c));

		c.clearFields();

		ensureCabinetOrder();

		return redirect;
	}

	/**
	 * The CabinetBean to update. Replaces the CabinetBean at element number
	 * (slotNumber - 1).
	 * 
	 * @param c
	 *            the CabinetBean to replace
	 * @param redirect
	 *            the redirect link to return
	 * @return the redirect link
	 */
	public String updateCabinet(CabinetBean c, String redirect) {
		cabinets.set(c.getSlotNumber() - 1, new CabinetBean(c));

		c.clearFields();

		return redirect;
	}

	/**
	 * @return the List of AntennaBean
	 */
	public List<AntennaBean> getAntennas() {
		return antennas;
	}

	/**
	 * Removes the first occurrence of the AntennaBean (found using the equals
	 * method in AntennaBean).
	 * 
	 * @param a
	 *            the AntennaBean to remove
	 */
	public void removeAntenna(AntennaBean a) {
		antennas.remove(a);

		ensureAntennaOrder();
	}

	/**
	 * Adds an AntennaBean to the end of the List of AntennaBean.
	 * 
	 * @param a the AntennaBean to add to the List
	 * @param redirect the redirect link to return
	 * @return the redirect link
	 */
	public String addAntenna(AntennaBean a, String redirect) {
		a.setSlotNumber(antennas.size() + 1);

		antennas.add(new AntennaBean(a));

		a.clearFields();

		ensureAntennaOrder();

		return redirect;
	}

	/**
	 * 
	 * @param a
	 * @param redirect
	 * @return
	 */
	public String updateAntenna(AntennaBean a, String redirect) {
		antennas.set(a.getSlotNumber() - 1, a);

		return redirect;
	}

	public AntennaBean getAntenna(int slotNumber) {
		return antennas.get(slotNumber - 1);
	}

	public String create(String redirect) throws SQLException {
		BaseStationDAO dao = new BaseStationDAO();
		// System.out.println("Unique ID: " + uniqueId);
		// System.out.println("Unique ID: " + (uniqueId == ""));

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

		dao.update(this);

		clearFields();

		return redirect;
	}

	public String discard(String redirect) {
		clearFields();

		return redirect;
	}

	public void delete() throws SQLException, InterruptedException {
		// System.out.println("Delete");

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
