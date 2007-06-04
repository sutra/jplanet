package com.redv.jplanet;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
public class PlanetServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2001221873578784258L;

	private static final Log log = LogFactory.getLog(PlanetServlet.class);

	private Timer timer = new Timer();

	private String[] feeds;

	private Properties prop = new Properties();

	private List<SyndEntry> syndEntries = new ArrayList<SyndEntry>();

	private List<FeedContent> entries = new ArrayList<FeedContent>();

	@Override
	public void init() throws ServletException {
		super.init();

		try {
			prop.loadFromXML(getClass().getResourceAsStream("/planet.xml"));
		} catch (InvalidPropertiesFormatException e) {
			throw new ServletException(e);
		} catch (IOException e) {
			throw new ServletException(e);
		}
		feeds = prop.getProperty("feeds").toString().split("\n");

		// set timer to get feeds
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				fetchFeeds();
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
			req.setAttribute("title", prop.getProperty("title"));
			req.setAttribute("entries", entries);
			String v = "/planet.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(v);
			rd.forward(req, resp);
		} else {
			resp.setContentType("text/xml; charset=UTF-8");
			SyndFeedOutput output = new SyndFeedOutput();
			try {
				output.output(this.buildFeed(feedType), resp.getWriter());
			} catch (FeedException e) {
				throw new ServletException(e);
			}
		}
	}

	private SyndFeed buildFeed(String feedType) {
		SyndFeed feed = new SyndFeedImpl();
		feed.setFeedType(feedType);
		feed.setTitle(prop.getProperty("title"));
		feed.setDescription(prop.getProperty("description"));
		feed.setLink(prop.getProperty("link"));
		feed.setEntries(syndEntries);
		return feed;
	}

	private synchronized void fetchFeeds() {
		List<SyndEntry> fetchingSyndEntries = new ArrayList<SyndEntry>();
		List<FeedContent> fetchingEntries = new ArrayList<FeedContent>();
		for (String feedUrl : feeds) {
			String trimedFeedUrl = feedUrl.trim();
			if (trimedFeedUrl.length() == 0) {
				continue;
			}
			try {
				retrieveFeed(fetchingSyndEntries, fetchingEntries,
						trimedFeedUrl);
			} catch (Exception e) {
				log.error("error while reading feed: " + feedUrl, e);
			}
		}
		Collections.sort(fetchingSyndEntries, new Comparator<SyndEntry>() {

			public int compare(SyndEntry o1, SyndEntry o2) {
				if (o1 == null || o2 == null || o1.getPublishedDate() == null
						|| o2.getPublishedDate() == null) {
					return 0;
				}
				return -o1.getPublishedDate().compareTo(o2.getPublishedDate());
			}
		});
		Collections.sort(fetchingEntries, new Comparator<FeedContent>() {

			public int compare(FeedContent o1, FeedContent o2) {
				if (o1 == null || o2 == null || o1.getPost() == null
						|| o2.getPost() == null
						|| o1.getPost().getPublishedDate() == null
						|| o2.getPost().getPublishedDate() == null) {
					return 0;
				}
				return -o1.getPost().getPublishedDate().compareTo(
						o2.getPost().getPublishedDate());
			}
		});

		this.syndEntries = fetchingSyndEntries;
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
			FeedContent fc = new FeedContent();
			fc.setPost(syndEntry);
			fc.setSiteName(inFeed.getTitle());
			fc.setSiteDescription(inFeed.getDescription());
			fc.setSiteLink(inFeed.getLink());
			fc.setFullname(inFeed.getAuthor());
			fetchingEntries.add(fc);
		}
	}
}
