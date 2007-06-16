/**
 * 
 */
package com.redv.jplanet.conf;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.redv.jplanet.Constant;
import com.redv.jplanet.Planet;
import com.redv.jplanet.Subscription;

/**
 * @author sutra
 * 
 */
public class Config {
	private static final Log log = LogFactory.getLog(Config.class);

	private static Config instance = new Config();

	private Planet planet;

	public static Config getInstance() {
		return instance;
	}

	private Config() {
		try {
			planet = Constant.getConfigReader().read();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		if (log.isDebugEnabled()) {
			log.debug("planet.subscriptions: " + planet.getSubscriptions());
			if (planet.getSubscriptions() != null) {
				log.debug("planet.subscriptions.size: "
						+ planet.getSubscriptions().size());
				for (Subscription subscription : planet.getSubscriptions()) {
					log.debug(subscription.getFeedUrl());
				}
			}
			log.debug(String.format("update period(minutes): %1$d", planet
					.getUpdatePeriod()));
		}
	}

	public synchronized void save(Planet planet) throws Exception {
		Constant.getConfigWriter().write(planet);
	}

	public Planet getPlanet() {
		return planet;
	}
}
