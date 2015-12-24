package ca.peterzhu.basestation.googlemaps;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import ca.peterzhu.basestation.dao.bean.BaseStationBean;

/**
 * Provides a draggable map for the new/edit base station page.
 * 
 * @author Peter Zhu
 * @version 1.0
 */
@Named("locationDragMap")
@ViewScoped
public class LocationDragMap implements Serializable {
	private MapModel map;
	private Marker selectedMarker;

	@Inject
	private BaseStationBean baseStationBean;

	/**
	 * 
	 */
	public LocationDragMap() {

	}

	/**
	 * Initializes the map
	 */
	@PostConstruct
	private void init() {
		map = new DefaultMapModel();

		LatLng coord = new LatLng(baseStationBean.getLatitude(), baseStationBean.getLongitude());

		Marker marker = new Marker(coord);
		marker.setDraggable(true);

		map.addOverlay(marker);
	}

	/**
	 * @return the drag map
	 */
	public MapModel getMap() {
		return map;
	}

	/**
	 * @param event
	 *            the marker dragged
	 */
	public void onMarkerDrag(MarkerDragEvent event) {
		selectedMarker = event.getMarker();

		LatLng latLng = selectedMarker.getLatlng();

		baseStationBean.setLongitude(round(latLng.getLng(), 6));
		baseStationBean.setLatitude(round(latLng.getLat(), 6));
	}

	/**
	 * Called when a latitude/longitude change happens in the BaseStationBean
	 * class.
	 */
	public void onLatLngChange() {
		double lat = baseStationBean.getLatitude();
		double lng = baseStationBean.getLongitude();

		map.getMarkers().get(0).setLatlng(new LatLng(lat, lng));
	}

	/**
	 * Resets the map.
	 */
	public void resetMap() {
		init();
	}

	/**
	 * Disables drag on the map. Used for viewing.
	 */
	public void disableDrag() {
		map.getMarkers().get(0).setDraggable(false);
	}

	/**
	 * Rounds a double value to a specific amount of decimal places.
	 * 
	 * @param value the value to be rounded
	 * @param places the decimal places to be rounded
	 * @return the rounded number
	 */
	private double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
}
