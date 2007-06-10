package com.redv.jplanet;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import com.redv.jplanet.conf.Config;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.fetcher.FeedFetcher;
import com.sun.syndication.fetcher.FetcherException;
import com.sun.syndication.fetcher.impl.FeedFetcherCache;
import com.sun.syndication.fetcher.impl.HashMapFeedInfoCache;
import com.sun.syndication.fetcher.impl.HttpClientFeedFetcher;
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

	private static final Log log = LogFactory.getLog(AggregatorServlet.class);

	private Timer configTimer = new Timer();

	private Timer timer = new Timer();

	private Planet planet;

	private long updatePeriod;

	private DateFormat groupingDateFormat;

	private DateFormat postDateFormat;

	private Date updateDate;

	private List<FeedContent> entries = new ArrayList<FeedContent>();

	@Override
	public void init() throws ServletException {
		super.init();

		try {
			planet = Config.getInstance().getPlanet();
			this.groupingDateFormat = new SimpleDateFormat(planet
					.getGroupingDateFormat());
			this.postDateFormat = new SimpleDateFormat(planet
					.getPostDateFormat());
		} catch (Exception e) {
			throw new ServletException(e);
		}

		if (log.isDebugEnabled()) {
			log.debug("planet.subscriptions: " + planet.getSubscriptions());
			if (planet.getSubscriptions() != null) {
				log.debug("planet.subscriptions.size: "
						+ planet.getSubscriptions().size());
				for (Subscription subscription : planet.getSubscriptions()) {
					log.debug(subscription.getFeedUrl());
				}
			}
			log.debug(String.format("update period(minutes): %1$d", planet
					.getUpdatePeriod()));
		}

		configTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				if (updatePeriod != planet.getUpdatePeriod()) {
					log.debug("updatePriod was modified, reset timer.");
					updatePeriod = planet.getUpdatePeriod();
					try {
						timer.cancel();
					} catch (IllegalStateException e) {
						log.debug("Timer already cancelled.");
					}

					timer = new Timer();
					// set timer to get feeds
					timer.schedule(new TimerTask() {

						@Override
						public void run() {
							log.debug("timer task start.");
							updateDate = new Date();
							fetchFeeds();
							log.debug("timer task end.");
						}
					}, 0, 1000 * 60 * planet.getUpdatePeriod());
					log.debug("timer resetted");
				} else {
					log.debug("updatePriod has not been modified.");
				}
			}

		}, 0, 1000 * 60 * 10);
	}

	@Override
	public void destroy() {
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
		req.setAttribute("planet", planet);
		req.setAttribute("updateDate", updateDate);
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
		List<SyndEntry> entries = new ArrayList<SyndEntry>();
		for (FeedContent fc : this.entries) {
			entries.add(fc.getPost());
		}
		feed.setEntries(entries);
		return feed;
	}

	private synchronized void fetchFeeds() {
		List<SyndEntry> fetchingSyndEntries = new ArrayList<SyndEntry>();
		List<FeedContent> fetchingEntries = new ArrayList<FeedContent>();
		if (planet.getSubscriptions() != null) {
			for (Subscription subscription : planet.getSubscriptions()) {
				String feedUrl = subscription.getFeedUrl();
				if (feedUrl.length() == 0) {
					continue;
				}
				try {
					retrieveFeed(fetchingSyndEntries, fetchingEntries, feedUrl);
				} catch (Exception e) {
					log.error("error while reading feed: " + feedUrl, e);
				}
			}

			sort(fetchingEntries);
		}

		this.entries = fetchingEntries;
	}

	@SuppressWarnings("unchecked")
	private void retrieveFeed(List<SyndEntry> fetchingSyndEntries,
			List<FeedContent> fetchingEntries, String feedUrl)
			throws IllegalArgumentException, IOException, FeedException,
			FetcherException {
		FeedFetcherCache feedInfoCache = HashMapFeedInfoCache.getInstance();
		FeedFetcher feedFetcher = new HttpClientFeedFetcher(feedInfoCache);

		URL inputUrl = new URL(feedUrl);
		SyndFeed inFeed;
		inFeed = feedFetcher.retrieveFeed(inputUrl);
		feedFetcher.setUsingDeltaEncoding(true);
		List<SyndEntry> syndEntries = inFeed.getEntries();
		fetchingSyndEntries.addAll(syndEntries);
		for (SyndEntry syndEntry : syndEntries) {
			if (syndEntry.getPublishedDate() == null) {
				syndEntry.setPublishedDate(syndEntry.getUpdatedDate());
			}
			if (syndEntry.getDescription() == null) {
				if (syndEntry.getContents().size() > 0) {
					syndEntry.setDescription((SyndContent) syndEntry
							.getContents().get(0));
				}
			}

			FeedContent fc = new FeedContent();
			fc.setGroupingDateFormat(groupingDateFormat);
			fc.setPostDateFormat(postDateFormat);
			fc.setPost(syndEntry);
			fc.setSiteName(inFeed.getTitle());
			fc.setSiteDescription(inFeed.getDescription());
			fc.setSiteLink(inFeed.getLink());
			fc.setFullname(inFeed.getAuthor());
			fetchingEntries.add(fc);
		}
	}

	private void sort(List<FeedContent> fetchingEntries) {
		Collections.sort(fetchingEntries, new Comparator<FeedContent>() {

			public int compare(FeedContent o1, FeedContent o2) {
				if (o1 == null || o1.getPost() == null
						|| FeedContent.findDate(o1.getPost()) == null) {
					return 1;
				} else if (o2 == null || o2.getPost() == null
						|| FeedContent.findDate(o2.getPost()) == null) {
					return -1;
				}
				return -FeedContent.findDate(o1.getPost()).compareTo(
						FeedContent.findDate(o2.getPost()));
			}
		});
	}
}
