package com.mvp.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.mvp.client.ClientFactory;
import com.mvp.client.activity.CellTableActivity;
import com.mvp.client.activity.GoodbyeActivity;
import com.mvp.client.activity.HelloActivity;
import com.mvp.client.place.CellTablePlace;
import com.mvp.client.place.GoodbyePlace;
import com.mvp.client.place.HelloPlace;

public class AppActivityMapper implements ActivityMapper {

	private ClientFactory clientFactory;

	public AppActivityMapper(ClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof HelloPlace)
			return new HelloActivity((HelloPlace) place, clientFactory);
		else if (place instanceof GoodbyePlace)
			return new GoodbyeActivity((GoodbyePlace) place, clientFactory);
		else if (place instanceof CellTablePlace)
			return new CellTableActivity((CellTablePlace) place, clientFactory);
		return null;

	}

}
