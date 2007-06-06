/**
 * 
 */
package com.redv.jplanet;

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

/**
 * @author sutra
 * 
 */
public class ConfigReader {
	public static File getDataDir() {
		Properties p = new Properties();
		try {
			p
					.load(ConfigReader.class
							.getResourceAsStream("/planet.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return new File(p.getProperty("data.dir"));
	}

	public Planet read() throws InvalidPropertiesFormatException, IOException {
		Planet planet = new Planet();
		Properties p = new Properties();
		InputStream is = new FileInputStream(new File(
				ConfigReader.getDataDir(), "planet.xml"));
		try {
			p.loadFromXML(is);
			planet.setTitle(p.getProperty("title").trim());
			planet.setDescription(p.getProperty("description").trim());
			planet.setSiteUrl(p.getProperty("siteUrl").trim());
			planet.setAdminName(p.getProperty("adminName").trim());
			planet.setAdminEmail(p.getProperty("adminEmail").trim());
			planet.setMailSubject(p.getProperty("mailSubject").trim());
			planet.setSubscriptions(this.readSubscription());
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
		InputStream is = new FileInputStream(new File(
				ConfigReader.getDataDir(), "subscriptions.xml"));
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
					subscription.setSiteUrl(p.getProperty(key).trim());
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
