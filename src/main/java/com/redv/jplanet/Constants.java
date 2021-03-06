/**
 * 
 */
package com.redv.jplanet;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.redv.jplanet.conf.ConfigReader;
import com.redv.jplanet.conf.ConfigWriter;

/**
 * Constants for JPlanet.
 * 
 * @author <a href="mailto:zhoushuqun@gmail.com">Sutra Zhou</a>
 * 
 */
public class Constants {
	private static final Log log = LogFactory.getLog(Constants.class);

	private static File dataDir;

	private static ConfigReader configReader;

	private static ConfigWriter configWriter;

	private static long aggregatorConfigTimerPeriod;

	static {
		Properties p = new Properties();
		try {
			p.load(Constants.class.getResourceAsStream("/jplanet.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		aggregatorConfigTimerPeriod = Long.parseLong(p
				.getProperty("aggregator.configTimer.period"));
		String confDir = replaceSystemProperty(p.getProperty("conf.dir"));
		log.debug("confDir: " + confDir);
		dataDir = new File(confDir);
		try {
			configReader = (ConfigReader) Class.forName(
					p.getProperty("conf.reader")).newInstance();
			configWriter = (ConfigWriter) Class.forName(
					p.getProperty("conf.writer")).newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Get the data directory of configuration etc.
	 * 
	 * @return the data directory of configuration etc.
	 */
	public static File getDataDir() {
		return dataDir;
	}

	/**
	 * Get the config timer (to refresh fetcher timer) period.
	 * 
	 * @return the config timer period.
	 */
	public static long getAggregatorConfigTimerPeriod() {
		return aggregatorConfigTimerPeriod;
	}

	/**
	 * Get config reader.
	 * 
	 * @return config reader.
	 */
	public static ConfigReader getConfigReader() {
		return configReader;
	}

	/**
	 * Get config writer.
	 * 
	 * @return config writer.
	 */
	public static ConfigWriter getConfigWriter() {
		return configWriter;
	}

	private static String replaceSystemProperty(final String orginalString) {
		String ret = new String(orginalString);
		int dollarIndex = ret.indexOf('$');
		while (dollarIndex != -1) {
			String var = orginalString.substring(
					orginalString.indexOf('{') + 1, orginalString.indexOf('}'));
			log.debug("var: " + var);
			ret = StringUtils.replace(orginalString, String.format("${%1$s}",
					var), System.getProperty(var));
			dollarIndex = orginalString.indexOf('$', dollarIndex + 1);
		}
		return ret;
	}
}
