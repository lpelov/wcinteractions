package com.oracle.wci.user.registration.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.plumtree.remote.logging.LogFactory;

/**
 * Web application listener, register the EDK logging.
 * 
 * @author L.Pelov, 14.06.2010
 */
public class AppListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("- contextInitialized");

		String appContext = arg0.getServletContext().getServletContextName();

		/**
		 * Initialise the WCI EDK Logging. Normally this is not going to work
		 * inside the Jetty Application Server used by the GWT Eclipse Pluggin,
		 */
		if (!LogFactory.isInitialized()) {
			LogFactory.initialize(appContext, true);
		}
	}

}
