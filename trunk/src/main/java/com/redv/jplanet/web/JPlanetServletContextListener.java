/**
 * 
 */
package com.redv.jplanet.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.redv.jplanet.conf.Config;

/**
 * The listener store the model of planet to the application scope named
 * "planet".
 * 
 * @author sutra
 * 
 */
public class JPlanetServletContextListener implements ServletContextListener {

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		sce.getServletContext().removeAttribute("planet");
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		sce.getServletContext().setAttribute("planet",
				Config.getInstance().getPlanet());
	}

}
