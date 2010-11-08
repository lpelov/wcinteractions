package com.mvp.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.mvp.client.ClientFactory;
import com.mvp.client.place.CellTablePlace;
import com.mvp.client.place.HelloPlace;
import com.mvp.client.ui.CellTableView;

public class CellTableActivity extends AbstractActivity implements
		CellTableView.Presenter {

	// Used to obtain views, eventBus, placeController
	// Alternatively, could be injected via GIN
	private ClientFactory clientFactory;

	// Name that will be appended to "Hello,"
	private String name;
	private String somerpcdata = "no data";
	
	public CellTableActivity(CellTablePlace place, ClientFactory clientFactory) {
		this.name = place.getCellTableName();
		this.clientFactory = clientFactory;
		
		Window.alert("Do RPC Call");
		somerpcdata = "I've got the data";
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		Window.alert(somerpcdata);
		
		// you can use here every view you want
		CellTableView cellTableView = clientFactory.getCellTableView();
		cellTableView.setName(name);
		cellTableView.setPresenter(this);
		panel.setWidget(cellTableView);
	}

	@Override
	public void goTo(Place place) {		
		clientFactory.getPlaceController().goTo(place);
	}

	@Override
	public void onButtonClicked() {
		Window.alert("This is now the activity here");
		clientFactory.getPlaceController().goTo(new HelloPlace("after table"));
	}

}
