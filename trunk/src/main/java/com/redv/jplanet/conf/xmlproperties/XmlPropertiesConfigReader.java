/**
 * 
 */
package com.redv.jplanet.conf.xmlproperties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.InvalidPropertiesFormatException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.redv.jplanet.Planet;
import com.redv.jplanet.Subscription;
import com.redv.jplanet.conf.ConfigReader;
import com.redv.jplanet.conf.Constant;

/**
 * @author sutra
 * 
 */
public class XmlPropertiesConfigReader implements ConfigReader {

	public Planet read() throws InvalidPropertiesFormatException, IOException {
		Planet planet = new Planet();
		Properties p = new Properties();
		InputStream is = new FileInputStream(new File(Constant.getDataDir(),
				"planet.xml"));
		try {
			p.loadFromXML(is);
			planet.setTitle(p.getProperty("title").trim());
			planet.setDescription(p.getProperty("description").trim());
			planet.setSiteUrl(p.getProperty("siteUrl").trim());
			planet.setLanguage(p.getProperty("language").trim());
			planet.setAdminName(p.getProperty("adminName").trim());
			planet.setAdminEmail(p.getProperty("adminEmail").trim());
			planet.setMailSubject(p.getProperty("mailSubject").trim());
			planet.setGroupingDateFormat(p.getProperty("groupingDateFormat")
					.trim());
			planet.setPostDateFormat(p.getProperty("postDateFormat").trim());
			planet.setSubscriptions(this.readSubscription());
			planet.setUpdatePeriod(Long.parseLong(p.getProperty("updatePeriod")
					.trim()));
		} finally {
			is.close();
		}
		return planet;
	}

	@SuppressWarnings("unchecked")
	private Set<Subscription> readSubscription()
			throws InvalidPropertiesFormatException, IOException {
		Map<String, Subscription> subscriptions = new LinkedHashMap<String, Subscription>();
		Properties p = new Properties();
		InputStream is = new FileInputStream(new File(Constant.getDataDir(),
				"subscriptions.xml"));
		p.loadFromXML(is);
		try {
			for (Object k : p.keySet()) {
				String key = (String) k;
				int dot = key.indexOf(".");
				String id = key.substring(0, dot);
				String field = key.substring(dot + 1, key.length());
				Subscription subscription = subscriptions.get(id);
				if (subscription == null) {
					subscription = new Subscription();
					subscriptions.put(id, subscription);
				}
				if (field.equals("title")) {
					subscription.setTitle(p.getProperty(key).trim());
				} else if (field.equals("description")) {
					subscription.setDescription(p.getProperty(key).trim());
				} else if (field.equals("feedUrl")) {
					subscription.setFeedUrl(p.getProperty(key).trim());
				} else if (field.equals("siteUrl")) {
					subscription.setSiteUrl(p.getProperty(key).trim());
				}
			}
		} finally {
			is.close();
		}
		Set ret = new HashSet<Subscription>();
		ret.addAll(subscriptions.values());
		return ret;
	}
}
