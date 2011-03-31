package com.mvp.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class RootView extends Composite implements HasText {

	private static RootViewUiBinder uiBinder = GWT
			.create(RootViewUiBinder.class);

	interface RootViewUiBinder extends UiBinder<Widget, RootView> {
	}

	public RootView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

//	@UiField
//	Button button;

	public RootView(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
//		button.setText(firstName);
	}

//	@UiHandler("button")
//	void onClick(ClickEvent e) {
//		Window.alert("Hello!");
//	}

	public void setText(String text) {
//		button.setText(text);
	}

	public String getText() {
//		return button.getText();
		return "";
	}

}
