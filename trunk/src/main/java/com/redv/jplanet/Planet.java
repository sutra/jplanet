/**
 * 
 */
package com.redv.jplanet;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author sutra
 * 
 */
public class Planet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1307732302091728827L;

	private static final Log log = LogFactory.getLog(Planet.class);

	private String title;

	private String keywords;

	private String description;

	private String siteUrl;

	private String language;

	private String copyright;

	private String adminName;

	private String adminEmail;

	private String mailSubject;

	private String groupingDateFormat;

	private String postDateFormat;

	private Set<Subscription> subscriptions = new LinkedHashSet<Subscription>();

	private Set<User> editors = new LinkedHashSet<User>();

	/**
	 * In minite.
	 */
	private long updatePeriod;

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
	 * @return keywords
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * @param keywords
	 *            要设置的 keywords
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
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
	 * @return language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language
	 *            要设置的 language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return copyright
	 */
	public String getCopyright() {
		return copyright;
	}

	/**
	 * @param copyright
	 *            要设置的 copyright
	 */
	public void setCopyright(String copyright) {
		this.copyright = copyright;
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

	/**
	 * @return groupingDateFormat
	 */
	public String getGroupingDateFormat() {
		return groupingDateFormat;
	}

	/**
	 * @param groupingDateFormat
	 *            要设置的 groupingDateFormat
	 */
	public void setGroupingDateFormat(String groupingDateFormat) {
		this.groupingDateFormat = groupingDateFormat;
	}

	/**
	 * @return postDateFormat
	 */
	public String getPostDateFormat() {
		return postDateFormat;
	}

	/**
	 * @param postDateFormat
	 *            要设置的 postDateFormat
	 */
	public void setPostDateFormat(String postDateFormat) {
		this.postDateFormat = postDateFormat;
	}

	/**
	 * @return updatePeriod 单位秒
	 */
	public long getUpdatePeriod() {
		return updatePeriod;
	}

	/**
	 * @param updatePeriod
	 *            要设置的 updatePeriod，单位秒
	 */
	public void setUpdatePeriod(long updatePeriod) {
		this.updatePeriod = updatePeriod;
	}

	/**
	 * @return editors
	 */
	public Set<User> getEditors() {
		return editors;
	}

	/**
	 * @param editors
	 *            要设置的 editors
	 */
	public void setEditors(Set<User> editors) {
		this.editors = editors;
	}

	/* Public methods */

	public void addSubscription(Subscription subscription) {
		synchronized (subscriptions) {
			log.debug("addSubscription called.");
			this.subscriptions.add(subscription);
		}
	}

	public void addEditor(User user) {
		synchronized (editors) {
			this.editors.add(user);
		}
	}
}
