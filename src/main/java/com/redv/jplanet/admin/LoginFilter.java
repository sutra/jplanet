/**
 * 
 */
package com.redv.jplanet.admin;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author sutra
 * 
 */
public class LoginFilter implements Filter {
	private static final Log log = LogFactory.getLog(LoginFilter.class);

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {

	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		if (req.getSession().getAttribute("editor") == null) {
			if (log.isDebugEnabled()) {
				log.debug("not logged in.");
			}
			resp.sendRedirect("login.jsp");
		} else {
			if (log.isDebugEnabled()) {
				log.debug(String.format("logged in: ", req.getSession()
						.getAttribute("editor")));
			}
			chain.doFilter(request, response);
		}
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
