/**
 * 
 */
package com.redv.jplanet;

import java.io.Serializable;
import java.util.Set;

/**
 * @author sutra
 * 
 */
public class Planet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1307732302091728827L;

	private String title;

	private String description;

	private String siteUrl;

	private String adminName;

	private String adminEmail;

	private String mailSubject;

	private Set<Subscription> subscriptions;

	/**
	 * @return adminEmail
	 */
	public String getAdminEmail() {
		return adminEmail;
	}

	/**
	 * @param adminEmail
	 *            要设置的 adminEmail
	 */
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
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
	 * @return subscriptions
	 */
	public Set<Subscription> getSubscriptions() {
		return subscriptions;
	}

	/**
	 * @param subscriptions
	 *            要设置的 subscriptions
	 */
	public void setSubscriptions(Set<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	/**
	 * @return adminName
	 */
	public String getAdminName() {
		return adminName;
	}

	/**
	 * @param adminName
	 *            要设置的 adminName
	 */
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	/**
	 * @return mailSubject
	 */
	public String getMailSubject() {
		return mailSubject;
	}

	/**
	 * @param mailSubject
	 *            要设置的 mailSubject
	 */
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

}
