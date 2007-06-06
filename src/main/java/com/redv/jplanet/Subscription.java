/**
 * 
 */
package com.redv.jplanet;

import java.io.Serializable;

/**
 * @author sutra
 * 
 */
public class Subscription implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3773804382205257086L;

	private String title;

	private String description;

	private String feedUrl;

	private String siteUrl;

	/**
	 * @return feedUrl
	 */
	public String getFeedUrl() {
		return feedUrl;
	}

	/**
	 * @param feedUrl
	 *            要设置的 feedUrl
	 */
	public void setFeedUrl(String feedUrl) {
		this.feedUrl = feedUrl;
	}

	/**
	 * @return siteUrl
	 */
	public String getSiteUrl() {
		return siteUrl;
	}

	/**
	 * @param siteUrl
	 *            要设置的 siteUrl
	 */
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	/**
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            要设置的 title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            要设置的 description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
