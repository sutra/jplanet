/**
 * 
 */
package com.redv.jplanet;

import java.io.Serializable;

/**
 * The model of a subscription. It hold the title, feedUrl and the siteUrl.
 * 
 * @author <a href="mailto:zhoushuqun@gmail.com">Sutra Zhou</a>
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
	public void setFeedUrl(final String feedUrl) {
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
	public void setSiteUrl(final String siteUrl) {
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
	public void setTitle(final String title) {
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
	public void setDescription(final String description) {
		this.description = description;
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result
				+ ((description == null) ? 0 : description.hashCode());
		result = PRIME * result + ((feedUrl == null) ? 0 : feedUrl.hashCode());
		result = PRIME * result + ((siteUrl == null) ? 0 : siteUrl.hashCode());
		result = PRIME * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Subscription other = (Subscription) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (feedUrl == null) {
			if (other.feedUrl != null)
				return false;
		} else if (!feedUrl.equals(other.feedUrl))
			return false;
		if (siteUrl == null) {
			if (other.siteUrl != null)
				return false;
		} else if (!siteUrl.equals(other.siteUrl))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
