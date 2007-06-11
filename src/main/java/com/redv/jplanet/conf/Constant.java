/**
 * 
 */
package com.redv.jplanet.conf;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author sutra
 * 
 */
public class Constant {
	private static final Log log = LogFactory.getLog(Constant.class);

	private static File dataDir;

	private static ConfigReader configReader;

	private static ConfigWriter configWriter;
	static {
		Properties p = new Properties();
		try {
			p.load(Constant.class.getResourceAsStream("/planet.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
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

	public static File getDataDir() {
		return dataDir;
	}

	public static ConfigReader getConfigReader() {
		return configReader;
	}

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
