/*
 * Copyright 2010 L.Pelov Licensed under the Apache License, 
 * Version 2.0 (the "License"); you may not use this file except 
 * in compliance with the License. You may obtain a copy of the 
 * License at 
 * 		
 * 			http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions 
 * and limitations under the License.
 */
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
