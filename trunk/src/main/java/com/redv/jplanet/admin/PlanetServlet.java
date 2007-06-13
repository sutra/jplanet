/**
 * 
 */
package com.redv.jplanet.admin;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.redv.jplanet.Planet;
import com.redv.jplanet.Subscription;
import com.redv.jplanet.User;
import com.redv.jplanet.conf.Config;

/**
 * @author sutra
 * 
 */
public class PlanetServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7652262131843746403L;

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(PlanetServlet.class);

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		setAttributes(req);
		this.getServletContext().getRequestDispatcher("/admin/planet.jsp")
				.forward(req, resp);
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Planet planet = Config.getInstance().getPlanet();
		planet.setTitle(req.getParameter("title"));
		planet.setDescription(req.getParameter("description"));
		planet.setSiteUrl(req.getParameter("siteUrl"));
		planet.setLanguage(req.getParameter("language"));
		planet.setAdminName(req.getParameter("adminName"));
		planet.setAdminEmail(req.getParameter("adminEmail"));
		planet.setMailSubject(req.getParameter("mailSubject"));
		planet.setGroupingDateFormat(req.getParameter("groupingDateFormat"));
		planet.setPostDateFormat(req.getParameter("postDateFormat"));
		planet
				.setUpdatePeriod(Long.parseLong(req
						.getParameter("updatePeriod")));

		String[] openids = req.getParameterValues("editor.openid");
		Set<User> editors = new LinkedHashSet<User>();
		for (int i = 0; i < openids.length; i++) {
			if (openids[i].length() > 0) {
				editors.add(new User(openids[i]));
			}
		}
		planet.setEditors(editors);

		String[] feedUrls = req.getParameterValues("subscription.feedUrl");
		String[] titles = req.getParameterValues("subscription.title");
		String[] siteUrls = req.getParameterValues("subscription.siteUrl");
		String[] descriptions = req
				.getParameterValues("subscription.description");
		Set<Subscription> subscriptions = new LinkedHashSet<Subscription>();
		for (int i = 0; i < feedUrls.length; i++) {
			if (feedUrls[i].length() > 0) {
				Subscription subscription = new Subscription();
				subscription.setFeedUrl(feedUrls[i]);
				subscription.setTitle(titles[i]);
				subscription.setSiteUrl(siteUrls[i]);
				subscription.setDescription(descriptions[i]);
				subscriptions.add(subscription);
			}
		}
		planet.setSubscriptions(subscriptions);

		try {
			Config.getInstance().save(planet);
		} catch (Exception e) {
			throw new ServletException(e);
		}

		this.setAttributes(req);
		req.setAttribute("success", true);
		this.getServletContext().getRequestDispatcher("/admin/planet.jsp")
				.forward(req, resp);
	}

	private void setAttributes(HttpServletRequest req) {
		Planet planet = Config.getInstance().getPlanet();
		req.setAttribute("planet", planet);
	}
}
