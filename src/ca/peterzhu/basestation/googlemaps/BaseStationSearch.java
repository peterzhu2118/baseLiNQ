package ca.peterzhu.basestation.googlemaps;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("baseStationSearch")
@ViewScoped
public class BaseStationSearch implements Serializable{
	private String searchTerm;
	private int searchType;
	
	public BaseStationSearch(){
		searchTerm = "";
		searchType = 0;
		//System.out.println("BASESTATION BEAN  SEARCH!!!!!!!!!!!!!!!!!!");
	}

	/**
	 * @return the searchTerm
	 */
	public String getSearchTerm() {
		return searchTerm;
	}

	/**
	 * @param searchTerm the searchTerm to set
	 */
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	/**
	 * @return the searchType
	 */
	public int getSearchType() {
		return searchType;
	}

	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}
	
	
}
