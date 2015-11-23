package ca.peterzhu.basestation.googlemaps;

import java.io.Serializable;
import java.sql.SQLException;
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

@Named("baseStationMap")
@ViewScoped
public class AllBaseStationMap implements Serializable {
	private MapModel map;
	private Marker selectedMarker;

	private BaseStationDAO baseStationDao;

	@Inject
	private BaseStationSearch baseStationSearch;

	public AllBaseStationMap() {
		System.out.println("const");
	}

	@PostConstruct
	public void init() throws SQLException {
		System.out.println("init");
		update();
	}

	public void update() throws SQLException {
		map = new DefaultMapModel();

		baseStationDao = new BaseStationDAO();

		List<BaseStationBean> baseStations = baseStationDao.retrieveAll();

		System.out.println("Search term: " + baseStationSearch.getSearchTerm());
		System.out.println("Search Type: " + baseStationSearch.getSearchType());

		if (baseStationSearch.getSearchType() == 0) {

		} else if (baseStationSearch.getSearchType() == 1 && baseStationSearch.getSearchTerm() != null
				&& baseStationSearch.getSearchTerm() != "") {
			for (int i = 0; i < baseStations.size(); i++) {
				if (!baseStations.get(i).getName().toLowerCase()
						.contains(baseStationSearch.getSearchTerm().toLowerCase())) {
					System.out.println("remove");
					baseStations.remove(i);
				}
			}
		}

		for (BaseStationBean bsb : baseStations) {
			System.out.println("Added Marker: " + bsb.getName());
			LatLng coord = new LatLng(bsb.getLatitude(), bsb.getLongitude());

			map.addOverlay(new Marker(coord, bsb.getName(), bsb));
		}
	}

	public MapModel getMap() {
		return map;
	}

	public void onMarkerSelect(OverlaySelectEvent ose) {
		selectedMarker = (Marker) ose.getOverlay();
	}

	public Marker getSelectedMarker() {
		return selectedMarker;
	}
}
