/**
 * 
 */
package com.redv.jplanet;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;

/**
 * @author Sutra Zhou
 * 
 */
public class FeedContent {
	private static final Log log = LogFactory.getLog(FeedContent.class);

	// site
	private String siteName;

	private String siteDescription;

	private String siteLink;

	private String fullname;

	private String nickname;

	// post
	private SyndEntry post;

	public String getTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return dateFormat.format(findDate(post));
	}

	public String getDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(findDate(post));
	}

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
	public void setFullname(String fullname) {
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
	public void setNickname(String nickname) {
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
	public void setSiteLink(String siteLink) {
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
	public void setSiteName(String siteName) {
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
	public void setSiteDescription(String siteDescription) {
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
	public void setPost(SyndEntry post) {
		this.post = post;
	}

	/**
	 * @return datetime
	 */
	public Date getDatetime() {
		return findDate(post);
	}

	public SyndContent getDescription() {
		if (post.getDescription() != null) {
			return post.getDescription();
		} else if (post.getContents().size() > 0) {
			return (SyndContent) post.getContents().get(0);
		}
		return null;
	}

	public static Date findDate(SyndEntry se) {
		if (se == null) {
			return null;
		}
		Date date = null;
		if (log.isDebugEnabled()) {
			log.debug("published: " + se.getPublishedDate());
			log.debug("updated: " + se.getUpdatedDate());
		}
		if (se.getPublishedDate() == null) {
			date = se.getPublishedDate();
		}
		if (date == null) {
			date = se.getUpdatedDate();
		}
		return date;
	}
}
