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

@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthFilter implements Filter {
	public AuthFilter() {

	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

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

	@Override
	public void destroy() {

	}
}
