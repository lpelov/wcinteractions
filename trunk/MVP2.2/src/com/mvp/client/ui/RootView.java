package com.mvp.client.ui;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.mvp.client.ClientFactory;
import com.mvp.client.mapper.RightActivityMapper;
import com.mvp.client.mapper.RootActivityMapper;

public class RootView extends Composite implements MainView {

	private static RootViewUiBinder uiBinder = GWT
			.create(RootViewUiBinder.class);

	interface RootViewUiBinder extends UiBinder<Widget, RootView> {
	}

	Presenter presenter;
	private ClientFactory clientFactory;

	@UiField
	SimplePanel leftPanel;
	@UiField
	ScrollPanel rightPanel;
	@UiField
	MenuItem btnHome;
	@UiField
	MenuItem btnProfile;
	@UiField
	MenuItem btnAccount;
	@UiField
	MenuItem btnSettings;
	@UiField
	MenuItem btnLogout;
	@UiField
	MenuBar menuBar;

	public RootView(ClientFactory clientFactory) {
		initWidget(uiBinder.createAndBindUi(this));

		this.clientFactory = clientFactory;

		// Start ActivityManager for the main widget with our ActivityMapper
		ActivityMapper leftActivityMapper = new RootActivityMapper(
				clientFactory);
		ActivityManager leftActivityManager = new ActivityManager(
				leftActivityMapper, this.clientFactory.getEventBus());
		leftActivityManager.setDisplay(leftPanel);

		ActivityMapper rightActivityMapper = new RightActivityMapper(
				clientFactory);
		ActivityManager rightActivityManager = new ActivityManager(
				rightActivityMapper, this.clientFactory.getEventBus());
		rightActivityManager.setDisplay(rightPanel);

		btnHome.setCommand(cmdBtnHome);
		btnProfile.setCommand(cmdBtnProfile);
		btnSettings.setCommand(cmdBtnSettings);
		btnLogout.setCommand(cmdBtnLogout);

	}

	Command cmdBtnHome = new Command() {
		public void execute() {
			Window.alert("You selected a Home item!");
		}
	};

	Command cmdBtnProfile = new Command() {
		public void execute() {
			Window.alert("You selected a Profile item!");
		}
	};

	Command cmdBtnSettings = new Command() {
		public void execute() {
			Window.alert("You selected a Settings item!");
		}
	};

	Command cmdBtnLogout = new Command() {
		public void execute() {
			Window.alert("You selected a Logout item!");
		}
	};

	@Override
	public void setWidgetName(String widgetName) {

	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public ClientFactory getClientFactory() {
		return clientFactory;
	}

}
