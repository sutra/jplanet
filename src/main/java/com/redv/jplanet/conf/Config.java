/**
 * 
 */
package com.redv.jplanet.conf;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.redv.jplanet.Constants;
import com.redv.jplanet.Planet;
import com.redv.jplanet.Subscription;

/**
 * Hold the config of JPlanet.
 * <p>
 * It is a singleton class.
 * </p>
 * 
 * @author <a href="mailto:zhoushuqun@gmail.com">Sutra Zhou</a>
 * 
 */
public class Config {
	private static final Log log = LogFactory.getLog(Config.class);

	private static Config instance = new Config();

	private final Planet planet;

	/**
	 * Get the instance of Config as Config is a singleton class.
	 * 
	 * @return the single instance of this class.
	 */
	public static Config getInstance() {
		return instance;
	}

	private Config() {
		try {
			planet = Constants.getConfigReader().read();
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

	/**
	 * Save the configration of planet by the config writer.
	 * 
	 * @param planet
	 *            the planet to save.
	 * @throws Exception
	 *             exception while saving.
	 */
	public synchronized void save(final Planet planet) throws Exception {
		Constants.getConfigWriter().write(planet);
	}

	/**
	 * Get the configuration of planet by the config reader.
	 * 
	 * @return the model of planet.
	 */
	public Planet getPlanet() {
		return planet;
	}
}
