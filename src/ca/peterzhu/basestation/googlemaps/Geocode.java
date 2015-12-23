package ca.peterzhu.basestation.googlemaps;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

public class Geocode {
	private GeoApiContext context;
	private String address;
	private GeocodingResult[] results;
	
	public Geocode(String a) throws Exception{
		context =  new GeoApiContext().setApiKey("AIzaSyDumJ__kGs3O5H58wEHIZ8f3o7KY1-a5pc");
		
		address = a;
		
		results = GeocodingApi.geocode(context, address).await();
	}
	
	public LatLng geocode(){
		return results[0].geometry.location;
	}
	
}
