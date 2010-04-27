package com.oracle.wci.user.registration.client;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WCIGwtStartUpPortlet extends MainEntry {

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	//private final RegisteringServiceAsync registeringService = GWT.create(RegisteringService.class);
	private RegisteringServiceAsync registeringService;
	private final VerticalPanel vPanel = new VerticalPanel();
	private final FlexTable flexTable = new FlexTable();
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		// get the service URL
		String slotID = getEmptySlotId(RootPanel.getBodyElement(), "sendButtonContainer-");		
		RootPanel portletRootPanel = RootPanel.get(slotID);
		
		String portletID = slotID.substring(slotID.indexOf("-") + 1);		
		registeringService = new AsyncCall(portletID).getService();
		
		
		VerticalPanel vrPanel = new VerticalPanel();
		vrPanel.setWidth("100%");

		HandlerManager eventBus = new HandlerManager(null);
		AppController appViewer = new AppController(registeringService, eventBus);
		appViewer.go(vrPanel);
		appViewer.load("grid");

		flexTable.setWidget(1, 0, vrPanel);
		flexTable.getFlexCellFormatter().setColSpan(1, 0, 4);
		flexTable.setWidth("100%");

		vPanel.add(flexTable);
		vPanel.setWidth("100%");

		portletRootPanel.add(vPanel);		
	}
	
}
