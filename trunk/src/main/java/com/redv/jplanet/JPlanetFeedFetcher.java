/**
 * 
 */
package com.redv.jplanet;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.fetcher.FeedFetcher;
import com.sun.syndication.fetcher.FetcherException;
import com.sun.syndication.fetcher.impl.FeedFetcherCache;
import com.sun.syndication.fetcher.impl.HashMapFeedInfoCache;
import com.sun.syndication.fetcher.impl.HttpClientFeedFetcher;
import com.sun.syndication.io.FeedException;

/**
 * @author sutra
 * 
 */
public class JPlanetFeedFetcher {
	private static final Log log = LogFactory.getLog(JPlanetFeedFetcher.class);

	private DateFormat groupingDateFormat;

	private DateFormat postDateFormat;

	private Planet planet;

	public JPlanetFeedFetcher(Planet planet) {
		this.planet = planet;
		this.groupingDateFormat = new SimpleDateFormat(planet
				.getGroupingDateFormat());
		this.postDateFormat = new SimpleDateFormat(planet.getPostDateFormat());
	}

	public List<FeedContent> fetchFeeds() throws JPlanetFetchException {
		List<SyndEntry> fetchingSyndEntries = new ArrayList<SyndEntry>();
		List<FeedContent> fetchingEntries = new ArrayList<FeedContent>();
		int exceptionCount = 0;
		if (planet.getSubscriptions() != null) {
			for (Subscription subscription : planet.getSubscriptions()) {
				String feedUrl = subscription.getFeedUrl();
				try {
					retrieveFeed(fetchingSyndEntries, fetchingEntries, feedUrl);
				} catch (Exception e) {
					exceptionCount++;
					log.error("error while reading feed: " + feedUrl, e);
				}
			}

			if (exceptionCount == planet.getSubscriptions().size()) {
				throw new JPlanetFetchException("All fetch failed.");
			}
			sort(fetchingEntries);
		}

		return fetchingEntries;
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