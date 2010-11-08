package com.mvp.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.mvp.client.ClientFactory;
import com.mvp.client.place.GoodbyePlace;
import com.mvp.client.place.HelloPlace;
import com.mvp.client.ui.HelloView;

public class HelloActivity extends AbstractActivity implements
		HelloView.Presenter {

	// Used to obtain views, eventBus, placeController
	// Alternatively, could be injected via GIN
	private ClientFactory clientFactory;

	// Name that will be appended to "Hello,"
	private String name;

	public HelloActivity(HelloPlace place, ClientFactory clientFactory) {
		this.name = place.getHelloName();
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		HelloView helloView = clientFactory.getHelloView();
		Window.alert(clientFactory.getCellTableView().getTextBox().getText());
		helloView.setName(name);
		helloView.setPresenter(this);
		containerWidget.setWidget(helloView.asWidget());
	}

	@Override
	public String mayStop() {
		// return "Please hold on. This activity is stopping.";
		return super.mayStop();
	}

	@Override
	public void goTo(Place place) {
		this.clientFactory.getPlaceController().goTo(place);
	}

	@Override
	public void onLinkClicked() {
		this.clientFactory.getPlaceController().goTo(new GoodbyePlace(name));
	}

}
