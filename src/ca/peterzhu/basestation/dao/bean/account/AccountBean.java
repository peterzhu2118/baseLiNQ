package ca.peterzhu.basestation.dao.bean.account;

import java.io.Serializable;
import java.sql.SQLException;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import ca.peterzhu.basestation.dao.AccountDAO;

/**
 * Authenticates the user for their login. This is a managed bean that interactsF
 * directly with the JSF frontend.
 * 
 * @author Peter Zhu
 * @version 2.0
 */
@Named("accountBean")
@SessionScoped
public class AccountBean implements Serializable {
	private String username;
	private String password;

	/**
	 * Initializes the fields to their default values.
	 */
	public AccountBean() {
		username = "";
		password = "";
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Attempts to log the user in. Will return the main page and allow access
	 * to the other pages if successful. Will display a error message and return
	 * the login page if failed.
	 * 
	 * @return the page to be redirected to
	 * @throws SQLException
	 *             thrown when a SQL exception occurs
	 */
	public String login() throws SQLException {
		AccountDAO accountDAO = new AccountDAO();

		boolean valid = accountDAO.authenticate(username, password);
		if (valid) {
			HttpSession session = SessionBean.getSession();
			session.setAttribute("username", username);

			return "main";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect Username or Password", "Please try again"));
			return "login";
		}
	}

	/**
	 * Logs the user out.
	 * 
	 * @return the login page to redirect to
	 */
	public String logout() {
		HttpSession session = SessionBean.getSession();
		session.invalidate();
		return "login";
	}

}
