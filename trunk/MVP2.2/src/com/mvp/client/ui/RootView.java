package com.mvp.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class RootView extends Composite implements MainView {

	private static RootViewUiBinder uiBinder = GWT
			.create(RootViewUiBinder.class);
	
	interface RootViewUiBinder extends UiBinder<Widget, RootView> {
	}

	Presenter presenter;
	
	@UiField
	ScrollPanel leftPanel;

	@UiField
	ScrollPanel rightPanel;	
	
	private Button btn1 = new Button("left btn1");
	private Button btn2 = new Button("left btn2");
	private Button btn3 = new Button("right btn1");
	private VerticalPanel vrPanelMenu = new VerticalPanel();
	private VerticalPanel vrPanelContent = new VerticalPanel();
	
	public RootView() {
		initWidget(uiBinder.createAndBindUi(this));


		vrPanelMenu.add(btn1);
		vrPanelMenu.add(btn2);
		
		vrPanelContent.add(btn3);
		
		
		leftPanel.add(vrPanelMenu);
		rightPanel.add(vrPanelContent);
		
	}

	@Override
	public void setWidgetName(String widgetName) {

	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

}
