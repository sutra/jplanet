/**
 * 
 */
package com.redv.jplanet.conf;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import com.redv.jplanet.conf.xmlproperties.XmlPropertiesConfigReader;

/**
 * @author sutra
 * 
 */
public class Constant {
	private static File dataDir;

	private static ConfigReader configReader;

	private static ConfigWriter configWriter;
	static {
		Properties p = new Properties();
		try {
			p.load(XmlPropertiesConfigReader.class
					.getResourceAsStream("/planet.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		dataDir = new File(p.getProperty("conf.dir"));
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
}
