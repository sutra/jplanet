/**
 * 
 */
package com.redv.jplanet.conf.betwixt;

import java.io.File;

import org.apache.commons.betwixt.io.BeanReader;

import com.redv.jplanet.Planet;
import com.redv.jplanet.Subscription;
import com.redv.jplanet.User;
import com.redv.jplanet.conf.ConfigReader;
import com.redv.jplanet.conf.Constant;

/**
 * @author sutra
 * 
 */
public class BetwixtConfigReader implements ConfigReader {

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

		return (Planet) reader.parse(new File(Constant.getDataDir(),
				"jplanet.xml"));
	}

}
