package com.mvp.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.mvp.client.ClientFactory;
import com.mvp.client.place.RootPlace;

public class LeftSide extends Composite {

	private static LeftSideUiBinder uiBinder = GWT
			.create(LeftSideUiBinder.class);

	interface LeftSideUiBinder extends UiBinder<Widget, LeftSide> {
	}

	@UiField
	Button button1;
	
	@UiField
	Button button2;

	ClientFactory clientFactory;
	
	public LeftSide(ClientFactory clientFactory) {
		initWidget(uiBinder.createAndBindUi(this));
		button1.setText("Sort1");
		button2.setText("Sort2");
		this.clientFactory = clientFactory;
	}

	@UiHandler("button1")
	void onButton1Click(ClickEvent e) {
		this.clientFactory.getPlaceController().goTo(new RootPlace("table"));
	}

	@UiHandler("button2")
	void onButton2Click(ClickEvent e) {
		this.clientFactory.getPlaceController().goTo(new RootPlace("table2"));
	}

}
