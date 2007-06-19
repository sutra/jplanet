package com.redv.jplanet.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.redv.jplanet.Constants;
import com.redv.jplanet.FeedContent;
import com.redv.jplanet.JPlanetFeedFetcher;
import com.redv.jplanet.JPlanetFetchException;
import com.redv.jplanet.Planet;
import com.redv.jplanet.conf.Config;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;

/**
 * @author Sutra Zhou
 * 
 */
public class AggregatorServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2001221873578784258L;

	private static final String ALL_FETCH_FAILED_MESSAGE = "All fetch failed, maybe now network is down.";

	private static final Log log = LogFactory.getLog(AggregatorServlet.class);

	private Timer configTimer = new Timer();

	private Timer timer = new Timer();

	private JPlanetFeedFetcher jplanetFeedFetcher;

	private Planet planet;

	private long updatePeriod;

	private Date updateDate = new Date();

	private List<FeedContent> entries = new ArrayList<FeedContent>();

	@Override
	public void init() throws ServletException {
		super.init();

		planet = Config.getInstance().getPlanet();

		this.jplanetFeedFetcher = new JPlanetFeedFetcher(planet);

		configTimer.schedule(new ConfigTask(), 0, 1000 * 60 * Constants
				.getAggregatorConfigTimerPeriod());
	}

	@Override
	public void destroy() {
		configTimer.cancel();
		timer.cancel();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String feedType = req.getParameter("feedType");

		if (feedType == null) {
			processHtml(req, resp);
		} else {
			processFeed(feedType, resp);
		}
	}

	private void processHtml(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("entries", entries);
		String v = "/planet.jsp";
		RequestDispatcher rd = getServletContext().getRequestDispatcher(v);

		rd.forward(req, resp);
	}

	private void processFeed(String feedType, HttpServletResponse resp)
			throws IOException, ServletException {
		resp.setContentType("text/xml; charset=UTF-8");
		SyndFeedOutput output = new SyndFeedOutput();
		try {
			output.output(this.buildFeed(feedType), resp.getWriter());
		} catch (FeedException e) {
			throw new ServletException(e);
		}
	}

	private SyndFeed buildFeed(String feedType) {
		SyndFeed feed = new SyndFeedImpl();
		feed.setFeedType(feedType);
		feed.setTitle(planet.getTitle());
		feed.setDescription(planet.getDescription());
		feed.setLink(planet.getSiteUrl());
		feed.setLanguage(planet.getLanguage());
		feed.setPublishedDate(this.updateDate);
		feed.setCopyright(planet.getCopyright());
		List<SyndEntry> entries = new ArrayList<SyndEntry>();
		int i = 0;
		for (FeedContent fc : this.entries) {
			if (feedType.equals("rss_0.9") && ++i > 15) {
				break;
			}
			entries.add(fc.getPost());
		}
		feed.setEntries(entries);
		return feed;
	}

	private class ConfigTask extends TimerTask {

		/*
		 * （非 Javadoc）
		 * 
		 * @see java.util.TimerTask#run()
		 */
		@Override
		public void run() {
			if (updatePeriod != Config.getInstance().getPlanet()
					.getUpdatePeriod()) {
				log.debug("updatePriod was modified, reset timer.");
				updatePeriod = planet.getUpdatePeriod();
				try {
					timer.cancel();
				} catch (IllegalStateException e) {
					log.debug("Timer already cancelled.");
				}

				timer = new Timer();
				// set timer to get feeds
				timer.schedule(new FetchTask(), 0, 1000 * 60 * planet
						.getUpdatePeriod());
				log.debug("timer resetted");
			} else {
				log.debug("updatePriod has not been modified.");
			}
		}

	}

	private class FetchTask extends TimerTask {

		/*
		 * （非 Javadoc）
		 * 
		 * @see java.util.TimerTask#run()
		 */
		@Override
		public void run() {
			log.debug("timer task start.");
			synchronized (AggregatorServlet.class) {
				try {
					entries = jplanetFeedFetcher.fetchFeeds();
					updateDate = new Date();
					getServletContext().setAttribute("updateDate", updateDate);
				} catch (JPlanetFetchException e) {
					log.error(ALL_FETCH_FAILED_MESSAGE, e);
				}
			}
			log.debug("timer task end.");
		}

	}
}
