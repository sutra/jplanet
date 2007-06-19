/**
 * 
 */
package com.redv.jplanet;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;

/**
 * The feed entry wrapper. It contains the entry's site properties etc.
 * 
 * @author <a href="mailto:zhoushuqun@gmail.com">Sutra Zhou</a>
 * 
 */
public class FeedContent {
	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(FeedContent.class);

	// site
	private String siteName;

	private String siteDescription;

	private String siteLink;

	private String fullname;

	private String nickname;

	// post
	private SyndEntry post;

	/**
	 * @return fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname
	 *            要设置的 fullname
	 */
	public void setFullname(final String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @return nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname
	 *            要设置的 nickname
	 */
	public void setNickname(final String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return siteLink
	 */
	public String getSiteLink() {
		return siteLink;
	}

	/**
	 * @param siteLink
	 *            要设置的 siteLink
	 */
	public void setSiteLink(final String siteLink) {
		this.siteLink = siteLink;
	}

	/**
	 * @return siteName
	 */
	public String getSiteName() {
		return siteName;
	}

	/**
	 * @param siteName
	 *            要设置的 siteName
	 */
	public void setSiteName(final String siteName) {
		this.siteName = siteName;
	}

	/**
	 * @return siteDescription
	 */
	public String getSiteDescription() {
		return siteDescription;
	}

	/**
	 * @param siteDescription
	 *            要设置的 siteDescription
	 */
	public void setSiteDescription(final String siteDescription) {
		this.siteDescription = siteDescription;
	}

	/**
	 * @return post
	 */
	public SyndEntry getPost() {
		return post;
	}

	/**
	 * @param post
	 *            要设置的 post
	 */
	public void setPost(final SyndEntry post) {
		this.post = post;
	}

	/**
	 * @return datetime
	 * @see #findDate(SyndEntry)
	 */
	public Date getDatetime() {
		return findDate(post);
	}

	/**
	 * Get the description of entry.
	 * 
	 * @return if the description is assigned, return the description. Otherwise
	 *         return the first of contents.
	 */
	public SyndContent getDescription() {
		if (post.getDescription() != null) {
			return post.getDescription();
		} else if (post.getContents().size() > 0) {
			return (SyndContent) post.getContents().get(0);
		}
		return null;
	}

	/**
	 * Find the entry's date.
	 * 
	 * @param syndEntry
	 *            the feed entry.
	 * @return If entry is null, return null.If entry's published date is
	 *         assigned return the published date, otherwise return the updated
	 *         date. If entry is not null, but both of published date and
	 *         updated date are null, return null too.
	 */
	public static Date findDate(final SyndEntry syndEntry) {
		return syndEntry == null ? null
				: (syndEntry.getPublishedDate() != null ? syndEntry
						.getPublishedDate() : syndEntry.getUpdatedDate());
	}
}
