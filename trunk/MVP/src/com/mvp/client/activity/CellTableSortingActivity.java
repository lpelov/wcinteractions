package com.mvp.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.mvp.client.ClientFactory;
import com.mvp.client.place.CellTableSortingPlace;
import com.mvp.client.ui.CellTableSortingView;

public class CellTableSortingActivity extends AbstractActivity implements
		CellTableSortingView.Presenter {

	// Used to obtain views, eventBus, placeController
	// Alternatively, could be injected via GIN
	private ClientFactory clientFactory;

	private String name;

	public CellTableSortingActivity(CellTableSortingPlace place,
			ClientFactory clientFactory) {
		this.name = place.getCellTableName();
		this.clientFactory = clientFactory;

	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		
		CellTableSortingView cellTableSortingView = this.clientFactory
				.getCellTableSortableView();
		
		cellTableSortingView.setName(name);
		cellTableSortingView.setPresenter(this);
		panel.setWidget(cellTableSortingView);

		
	}

	@Override
	public void goTo(Place place) {

	}

}
