/**
 * 
 */
package com.redv.jplanet;

import java.text.DateFormat;
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

	private DateFormat groupingDateFormat;

	private DateFormat postDateFormat;

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

	/**
	 * @return groupingDateFormat
	 */
	public DateFormat getGroupingDateFormat() {
		return groupingDateFormat;
	}

	/**
	 * @param groupingDateFormat
	 *            要设置的 groupingDateFormat
	 */
	public void setGroupingDateFormat(DateFormat groupingDateFormat) {
		this.groupingDateFormat = groupingDateFormat;
	}

	/**
	 * @return postDateFormat
	 */
	public DateFormat getPostDateFormat() {
		return postDateFormat;
	}

	/**
	 * @param postDateFormat
	 *            要设置的 postDateFormat
	 */
	public void setPostDateFormat(DateFormat postDateFormat) {
		this.postDateFormat = postDateFormat;
	}

	public SyndContent getDescription() {
		if (post.getDescription() != null) {
			return post.getDescription();
		} else if (post.getContents().size() > 0) {
			return (SyndContent) post.getContents().get(0);
		}
		return null;
	}

	public String getGroupingDate() {
		return this.groupingDateFormat.format(this.getDatetime());
	}

	public String getPostDate() {
		return this.postDateFormat.format(this.getDatetime());
	}

	public static Date findDate(SyndEntry se) {
		return se == null ? null : (se.getPublishedDate() != null ? se
				.getPublishedDate() : se.getUpdatedDate());
	}
}
