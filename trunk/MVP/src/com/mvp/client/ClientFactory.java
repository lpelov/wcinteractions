package com.mvp.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.mvp.client.ui.CellTableView;
import com.mvp.client.ui.GoodbyeView;
import com.mvp.client.ui.HelloView;

public interface ClientFactory {
	EventBus getEventBus();

	PlaceController getPlaceController();

	HelloView getHelloView();

	GoodbyeView getGoodbyeView();
	
	CellTableView getCellTableView();
}
