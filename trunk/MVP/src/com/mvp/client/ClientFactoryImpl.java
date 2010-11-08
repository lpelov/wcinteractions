package com.mvp.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.mvp.client.ui.CellTableView;
import com.mvp.client.ui.CellTableViewImpl;
import com.mvp.client.ui.GoodbyeView;
import com.mvp.client.ui.GoodbyeViewImpl;
import com.mvp.client.ui.HelloView;
import com.mvp.client.ui.HelloViewImpl;

public class ClientFactoryImpl implements ClientFactory {
	private final EventBus eventBus = new SimpleEventBus();
	private final PlaceController placeController = new PlaceController(
			eventBus);
	private final HelloView helloView = new HelloViewImpl();
	private final GoodbyeView goodbyeView = new GoodbyeViewImpl();
	private final CellTableView cellTableView = new CellTableViewImpl();

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public HelloView getHelloView() {
		return helloView;
	}

	@Override
	public GoodbyeView getGoodbyeView() {
		return goodbyeView;
	}

	@Override
	public CellTableView getCellTableView() {
		return cellTableView;
	}

}
