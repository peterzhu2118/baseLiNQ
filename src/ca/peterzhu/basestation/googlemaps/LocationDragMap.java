package ca.peterzhu.basestation.googlemaps;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import ca.peterzhu.basestation.dao.bean.BaseStationBean;

@Named("locationDragMap")
@ConversationScoped
public class LocationDragMap {
	private MapModel map;
	private Marker selectedMarker;

	@Inject
	private Conversation conversation;

	@Inject
	private BaseStationBean baseStationBean;

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
		
		System.out.println("Lat: " + latLng.getLat());
		System.out.println("Lng: " + latLng.getLng());

		/*DecimalFormat format = new DecimalFormat("#.######");
		format.setRoundingMode(RoundingMode.HALF_UP);

		baseStationBean.setLatitude(Double.parseDouble(format.format(latLng.getLat())));
		baseStationBean.setLongitude(Double.parseDouble(format.format(latLng.getLng())));*/
	}
}
