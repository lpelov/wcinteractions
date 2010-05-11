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
