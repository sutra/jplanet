/**
 * 
 */
package com.redv.jplanet.conf.xmlproperties;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Set;

import com.redv.jplanet.Planet;
import com.redv.jplanet.Subscription;
import com.redv.jplanet.conf.ConfigWriter;
import com.redv.jplanet.conf.Constant;

/**
 * @author sutra
 * 
 */
public class XmlPropertiesConfigWriter implements ConfigWriter {
	public void write(Planet planet) throws IOException {
		Properties p = new Properties();
		p.setProperty("title", planet.getTitle());
		p.setProperty("description", planet.getDescription());
		p.setProperty("siteUrl", planet.getSiteUrl());
		p.setProperty("language", planet.getLanguage());
		p.setProperty("adminName", planet.getAdminName());
		p.setProperty("adminEmail", planet.getAdminEmail());
		p.setProperty("mailSubject", planet.getMailSubject());
		p.setProperty("groupingDateFormat", planet.getGroupingDateFormat());
		p.setProperty("postDateFormat", planet.getPostDateFormat());
		p.setProperty("updatePeriod", String.valueOf(planet.getUpdatePeriod()));
		OutputStream os = new FileOutputStream(new File(Constant.getDataDir(),
				"planet.xml"));
		p.storeToXML(os, "JPlanet configuration.", "UTF-8");
		writeSubscriptions(planet.getSubscriptions());
	}

	private void writeSubscriptions(Set<Subscription> subscriptions)
			throws IOException {
		Properties p = new Properties();
		int id = 1;
		for (Subscription subscription : subscriptions) {
			p.setProperty(String.format("%1$s.%2$s", id, "title"), subscription
					.getTitle());
			p.setProperty(String.format("%1$s.%2$s", id, "description"),
					subscription.getDescription());
			p.setProperty(String.format("%1$s.%2$s", id, "feedUrl"),
					subscription.getFeedUrl());
			p.setProperty(String.format("%1$s.%2$s", id, "siteUrl"),
					subscription.getSiteUrl());
			id++;
		}
		OutputStream os = new FileOutputStream(new File(Constant.getDataDir(),
				"subscriptions.xml"));
		p.storeToXML(os, "JPlanet subscriptions configuration.", "UTF-8");
	}
}
