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
package com.oracle.wci.user.registration.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

/**
 * Main Entry
 * @author L.Pelov, 11.05.2010
 */
public abstract class MainEntry implements EntryPoint {

	/**
	 * This function helps to use more then one portlet on the same page. Search for the '<div id="portlet-">'
	 * if and return the all string like 'portlet-215', depending on the value in $$PORTLET_ID$$
	 * 
	 * @param element
	 *            - something like 'portlet-215', which is the value after conversion of the
	 *            'portlet-$$PORTLET_ID$$', for the current portlet
	 * @param idPrefix
	 * @return
	 */	
	protected static String getEmptySlotId(Element element, String idPrefix) {
		int count = DOM.getChildCount(element);
		if (count == 0) {
			String id = DOM.getElementAttribute(element, "id");
			if (id != null && id.startsWith(idPrefix)) {
				return id;
			}
		}
		else {
			for (int i = 0; i < count; i++) {
				String id = getEmptySlotId(DOM.getChild(element, i), idPrefix);
				if (id != null) {
					return id;
				}
			}
		}
		return null;
	}
	
	/**
	 * Initialize method to be converted to JavaScript
	 * @param portletID
	 */
	public static void setPortletId(int portletID) {
		GWT.log("PortletID: " + portletID);
	}

	/**
	 * Make possible to call then Java methods from JavaScript!
	 */
	public static native void defineSetPortletId()
	/*-{
		$wnd.setPortletId = function(portletID) {
			@com.oracle.wci.user.registration.client.MainEntry::setPortletId(I)(portletID);
		}
	}-*/;
	
}
