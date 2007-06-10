/**
 * 
 */
package com.redv.jplanet.conf;

import com.redv.jplanet.Planet;

/**
 * @author sutra
 * 
 */
public class Config {
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

	}

	public synchronized void save(Planet planet) throws Exception {
		Constant.getConfigWriter().write(planet);
	}

	public Planet getPlanet() {
		return planet;
	}
}
