/**
 * 
 */
package com.redv.jplanet;

import java.text.SimpleDateFormat;

import com.sun.syndication.feed.synd.SyndEntry;

/**
 * @author Sutra Zhou
 * 
 */
public class FeedContent {
	// site
	private String siteName;

	private String siteDescription;

	private String siteLink;

	private String fullname;

	private String nickname;

	// post
	private SyndEntry post;

	public String getTime() {
		if (this.post == null || this.post.getPublishedDate() == null) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return dateFormat.format(this.post.getPublishedDate());
	}

	public String getDate() {
		if (this.post == null || this.post.getPublishedDate() == null) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(this.post.getPublishedDate());
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

}
