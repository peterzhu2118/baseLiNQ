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

@Named("locationDragMap")
@ViewScoped
public class LocationDragMap implements Serializable {
	private MapModel map;
	private Marker selectedMarker;

	@Inject
	private BaseStationBean baseStationBean;

	public LocationDragMap() {

	}

	@PostConstruct
	private void init() {
		map = new DefaultMapModel();

		LatLng coord = new LatLng(baseStationBean.getLatitude(), baseStationBean.getLongitude());

		Marker marker = new Marker(coord);
		marker.setDraggable(true);

		map.addOverlay(marker);
	}

	public MapModel getMap() {
		return map;
	}

	public void onMarkerDrag(MarkerDragEvent event) {
		selectedMarker = event.getMarker();

		LatLng latLng = selectedMarker.getLatlng();

		// System.out.println("Lat: " + latLng.getLat());
		// System.out.println("Lng: " + latLng.getLng());

		baseStationBean.setLongitude(round(latLng.getLng(), 6));
		baseStationBean.setLatitude(round(latLng.getLat(), 6));
	}

	public void onLatLngChange() {
		double lat = baseStationBean.getLatitude();
		double lng = baseStationBean.getLongitude();

		//System.out.println("Lat: " + lat);
		//System.out.println("Lng: " + lng);

		map.getMarkers().get(0).setLatlng(new LatLng(lat, lng));
	}

	public void resetMap() {
		init();
	}
	
	public void disableDrag(){
		System.out.println("Disable drag");
		
		map.getMarkers().get(0).setDraggable(false);
	}

	private double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
}
