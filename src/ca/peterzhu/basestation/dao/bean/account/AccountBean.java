package ca.peterzhu.basestation.dao.bean.account;

import java.io.Serializable;
import java.sql.SQLException;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import ca.peterzhu.basestation.dao.AccountDAO;

@Named("accountBean")
@SessionScoped
public class AccountBean implements Serializable {
	private String username;
	private String password;

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
		// System.out.println("set username");
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
		// System.out.println("set username");
		this.password = password;
	}

	public String login() throws SQLException {
		AccountDAO accountDAO = new AccountDAO();

		boolean valid = accountDAO.authenticate(username, password);

		// System.out.println("Username: " + username);
		// System.out.println("Password: " + password);
		if (valid) {
			HttpSession session = SessionBean.getSession();
			session.setAttribute("username", username);
			// System.out.println("AUTHENTICATED");
			return "main";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect Username or Password", "Please try again"));
			// System.out.println("INCORRECT PASSWORD");
			return "login";
		}
	}

	public String logout() {
		HttpSession session = SessionBean.getSession();
		session.invalidate();
		return "login";
	}

}
