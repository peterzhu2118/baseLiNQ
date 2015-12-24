package ca.peterzhu.basestation.googlemaps;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * Contains all the fields for searching for the AllBaseStationMap class.
 * 
 * @author Peter Zhu
 * @version 1.0
 */
@Named("baseStationSearch")
@ViewScoped
public class BaseStationSearch implements Serializable {
	private String searchTerm;
	private int searchType;

	/**
	 * Initializes all the fields with default values.
	 */
	public BaseStationSearch() {
		searchTerm = "";
		searchType = 0;
	}

	/**
	 * @return the search term
	 */
	public String getSearchTerm() {		
		return searchTerm;
	}

	/**
	 * @param searchTerm
	 *            the search term to set
	 */
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm.trim();
	}

	/**
	 * @return the search type
	 */
	public int getSearchType() {
		return searchType;
	}

	/**
	 * @param searchType
	 *            the search type to set
	 */
	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}

}
