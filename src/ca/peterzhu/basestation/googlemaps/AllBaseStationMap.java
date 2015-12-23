package ca.peterzhu.basestation.googlemaps;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import ca.peterzhu.basestation.dao.BaseStationDAO;
import ca.peterzhu.basestation.dao.bean.BaseStationBean;

/**
 * Contains a map with all the Base Station and works with the BaseStationSearch
 * class to provide searching.
 * 
 * @author Peter Zhu
 * @version 3.0
 */
@Named("baseStationMap")
@ViewScoped
public class AllBaseStationMap implements Serializable {
	private MapModel map;
	private Marker selectedMarker;
	private String mapCenter;
	private int zoom;

	private BaseStationDAO baseStationDao;

	@Inject
	private BaseStationSearch baseStationSearch;

	/**
	 * Sets the values to their default values
	 */
	public AllBaseStationMap() {
		mapCenter = "0,0";
		zoom = 2;

	}

	/**
	 * Initializes the map.
	 * 
	 * @throws Exception
	 */
	@PostConstruct
	public void init() throws Exception {
		update();
	}

	/**
	 * Updates the map with the specified search terms.
	 * 
	 * @throws Exception
	 */
	public void update() throws Exception {
		// System.out.println("Update");

		map = new DefaultMapModel();

		baseStationDao = new BaseStationDAO();

		List<BaseStationBean> baseStations = baseStationDao.retrieveAll();

		// If the search term is empty, reset the map
		if (baseStationSearch.getSearchTerm() == null || baseStationSearch.getSearchTerm().equals("")) {
			mapCenter = "0,0";
			zoom = 2;

			// If the search term is name search
		} else if (baseStationSearch.getSearchType() == 1 && baseStationSearch.getSearchTerm() != null
				&& !baseStationSearch.getSearchTerm().equals("")) {
			mapCenter = "0,0";
			zoom = 2;

			// Search for the names that match the search term. Search is case
			// insensitive
			for (int i = 0; i < baseStations.size(); i++) {
				if (!baseStations.get(i).getName().toLowerCase()
						.contains(baseStationSearch.getSearchTerm().toLowerCase())) {
					baseStations.remove(i);
					i--;
				}
			}

			// If the search type is location search
		} else if (baseStationSearch.getSearchType() == 2 && baseStationSearch.getSearchTerm() != null
				&& !baseStationSearch.getSearchTerm().equals("")) {

			// Perform a geocode
			Geocode geocode = new Geocode(baseStationSearch.getSearchTerm());

			com.google.maps.model.LatLng coord = geocode.geocode();

			// If there are no results set the map to it's default values
			if (coord == null) {
				mapCenter = "0,0";
				zoom = 2;

				// If there are results center the map to the result
			} else {
				mapCenter = coord.lat + "," + coord.lng;

				zoom = 10;
			}
		}

		// Create the markers for the base stations
		for (BaseStationBean bsb : baseStations) {
			LatLng coord = new LatLng(bsb.getLatitude(), bsb.getLongitude());

			map.addOverlay(new Marker(coord, bsb.getName(), bsb));
		}
	}

	/**
	 * @return the map
	 */
	public MapModel getMap() {
		return map;
	}

	/**
	 * @param ose
	 *            the selected marker
	 */
	public void onMarkerSelect(OverlaySelectEvent ose) {
		selectedMarker = (Marker) ose.getOverlay();
	}

	/**
	 * @return the selected marker
	 */
	public Marker getSelectedMarker() {
		return selectedMarker;
	}

	public String getMapCenter() {
		return mapCenter;
	}

	public int getZoom() {
		return zoom;
	}
}
