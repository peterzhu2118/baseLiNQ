package ca.peterzhu.basestation.dao.bean.account;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Manages all the This is a fully static class meaning that no object of this
 * class can be created.
 * 
 * @author Peter Zhu
 * @version 1.0
 */
public class SessionBean {
	/**
	 * Private constructor to prevent the creation of this class.
	 */
	private SessionBean() {

	}

	/**
	 * Returns the current http session.
	 * 
	 * @return the current http session
	 */
	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	/**
	 * Returns the current http request.
	 * 
	 * @return the current http request
	 */
	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
}
