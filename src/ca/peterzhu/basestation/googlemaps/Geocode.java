package ca.peterzhu.basestation.googlemaps;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

/**
 * Provides geocoding service using the Google Maps services for Java.
 * 
 * @author Peter Zhu
 * @version 1.0
 */
public class Geocode {
	private GeoApiContext context;
	private String address;
	private GeocodingResult[] results;

	/**
	 * Initializes the geocoding service with the API key and runs the geocoding
	 * service with the specified address.
	 * 
	 * @param a
	 *            address to geocode for
	 * @throws Exception
	 */
	public Geocode(String a) throws Exception {
		context = new GeoApiContext().setApiKey("AIzaSyDumJ__kGs3O5H58wEHIZ8f3o7KY1-a5pc");

		address = a;

		results = GeocodingApi.geocode(context, address).await();
	}

	/**
	 * Returns the LatLng object of the first result (most accurate result). If
	 * there are no results, a LatLng object with 0 as latitude and longitude
	 * will be returned.
	 * 
	 * @return the latitude and longitude of the geocode
	 */
	public LatLng geocode() {
		return results.length == 0 ? null : results[0].geometry.location;
	}

}
