package com.mvp.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.mvp.client.ClientFactory;
import com.mvp.client.place.GoodbyePlace;
import com.mvp.client.ui.GoodbyeView;

public class GoodbyeActivity extends AbstractActivity implements
		GoodbyeView.Presenter {

	// Used to obtain views, eventBus, placeController
	// Alternatively, could be injected via GIN
	private ClientFactory clientFactory;

	// Name that will be appended to "Hello,"
	private String name;

	public GoodbyeActivity(GoodbyePlace place, ClientFactory clientFactory) {
		this.name = place.getGoodbyeName();
		this.clientFactory = clientFactory;

	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		GoodbyeView goodbyeView = clientFactory.getGoodbyeView();
		goodbyeView.setName(name);
		goodbyeView.setPresenter(this);
		containerWidget.setWidget(goodbyeView.asWidget());

	}

	@Override
	public String mayStop() {
		//return "Please hold on. This activity is stopping.";
		return super.mayStop();
	}

	@Override
	public void goTo(Place place) {
		this.clientFactory.getPlaceController().goTo(place);
	}
}
