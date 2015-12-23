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
 * @version 2.0
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
	 *
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

		if (baseStationSearch.getSearchTerm() == null || baseStationSearch.getSearchTerm().equals("")) {
			mapCenter = "0,0";
			zoom = 2;
		} else if (baseStationSearch.getSearchType() == 1 && baseStationSearch.getSearchTerm() != null
				&& !baseStationSearch.getSearchTerm().equals("")) {
			mapCenter = "0,0";
			zoom = 2;

			for (int i = 0; i < baseStations.size(); i++) {
				if (!baseStations.get(i).getName().toLowerCase()
						.contains(baseStationSearch.getSearchTerm().toLowerCase())) {
					baseStations.remove(i);
					i--;
				}
			}
		} else if (baseStationSearch.getSearchType() == 2 && baseStationSearch.getSearchTerm() != null
				&& !baseStationSearch.getSearchTerm().equals("")) {
			Geocode geocode = new Geocode(baseStationSearch.getSearchTerm());

			com.google.maps.model.LatLng coord = geocode.geocode();

			mapCenter = coord.lat + "," + coord.lng;

			zoom = 10;
		}

		for (BaseStationBean bsb : baseStations) {
			LatLng coord = new LatLng(bsb.getLatitude(), bsb.getLongitude());

			map.addOverlay(new Marker(coord, bsb.getName(), bsb));
		}
	}

	/**
	 * @return the map
	 */
	public MapModel getMap() {
		// System.out.println("Get map center: " + mapCenter);

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
