package ca.peterzhu.basestation.googlemaps;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
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
public class AllBaseStationMap implements Serializable{
	private MapModel map;
	private Marker selectedMarker;

	private BaseStationDAO baseStationDao;

	public AllBaseStationMap() {
		System.out.println("const");
	}

	@PostConstruct
	public void init() {
		System.out.println("init");
		map = new DefaultMapModel();

		baseStationDao = new BaseStationDAO();

		List<BaseStationBean> baseStations = null;
		try {
			baseStations = baseStationDao.retrieveAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (BaseStationBean bsb : baseStations) {
			System.out.println("Added Marker");
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
