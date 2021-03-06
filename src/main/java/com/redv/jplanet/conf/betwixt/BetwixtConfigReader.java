/**
 * 
 */
package com.redv.jplanet.conf.betwixt;

import java.io.File;
import java.io.IOException;

import org.apache.commons.betwixt.io.BeanReader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.redv.jplanet.Constants;
import com.redv.jplanet.Planet;
import com.redv.jplanet.Subscription;
import com.redv.jplanet.User;
import com.redv.jplanet.conf.ConfigReader;

/**
 * The implemention of config reader with <a
 * href="http://jakarta.apache.org/commons/betwixt/">Commons Betwixt</a>.
 * 
 * @author <a href="mailto:zhoushuqun@gmail.com">Sutra Zhou</a>
 * @see <a href="http://jakarta.apache.org/commons/betwixt/">Commons Betwixt</a>
 */
public class BetwixtConfigReader implements ConfigReader {
	private static final Log log = LogFactory.getLog(BetwixtConfigReader.class);

	private final File jplanetFile;

	public BetwixtConfigReader() {
		jplanetFile = new File(Constants.getDataDir(), "jplanet.xml");
	}

	public BetwixtConfigReader(File jplanetFile) {
		this.jplanetFile = jplanetFile;
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.redv.jplanet.conf.ConfigReader#read()
	 */
	public Planet read() throws Exception {
		BeanReader reader = new BeanReader();
		reader.getXMLIntrospector().setAttributesForPrimitives(false);
		reader.setMatchIDs(false);
		reader.getXMLIntrospector().setWrapCollectionsInElement(false);
		reader.registerBeanClass("Planet", Planet.class);
		reader.registerBeanClass("Planet/subscriptions/subscription",
				Subscription.class);
		reader.registerBeanClass("Planet/editors/editor", User.class);

		reader.addSetNext("Planet/subscriptions/subscription",
				"addSubscription");
		reader.addSetNext("Planet/editors/editor", "addEditor");

		try {
			return (Planet) reader.parse(jplanetFile);
		} catch (IOException e) {
			log.warn(String.format(
					"Could not find configuration from %1$s; using defaults.",
					jplanetFile.getPath(), e));
			log.warn("Loading configuration from jplanet-failsafe.xml.");
			return (Planet) reader.parse(this.getClass().getResourceAsStream(
					"jplanet-failsafe.xml"));
		}
	}
}
