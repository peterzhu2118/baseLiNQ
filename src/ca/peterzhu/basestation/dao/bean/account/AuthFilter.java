package ca.peterzhu.basestation.dao.bean.account;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Filters the pages depending on if the user has been authenticated.
 * 
 * @author Peter Zhu
 * @version 1.0
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthFilter implements Filter {
	/**
	 * Initializes this class.
	 */
	public AuthFilter() {

	}

	/**
	 * Overrides the init method in the Filter interface.
	 * 
	 * @param config
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {

	}

	/**
	 * Overrides the doFilter method in the Filter interface. Called whenever a
	 * page with the urlPattern is matched. Redirect the user to the login page
	 * if the user isn't authenticated; allows them to pass if they are.
	 * 
	 * @param request
	 *            the request of the web page
	 * @param response
	 *            the response to the web page
	 * @param chain
	 *            the http chain of the request
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession ses = req.getSession(false);

		String URI = req.getRequestURI();
		if (URI.indexOf("/login.xhtml") >= 0 || (ses != null && ses.getAttribute("username") != null)
				|| URI.contains("javax.faces.resource")) {
			chain.doFilter(request, response);
		} else {
			res.sendRedirect(req.getContextPath() + "/login.xhtml");
		}
	}

	/**
	 * Overrides the destroy method in Filter class.
	 */
	@Override
	public void destroy() {

	}
}
