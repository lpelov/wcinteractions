package com.oracle.wci.user.registration.client;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.oracle.wci.user.registration.client.event.LoadEvent;
import com.oracle.wci.user.registration.client.event.LoadEventHandler;
import com.oracle.wci.user.registration.client.presenter.HelloPresenter;
import com.oracle.wci.user.registration.client.presenter.Presenter;
import com.oracle.wci.user.registration.client.view.HelloView;

/**
 * Application main controler, all operations are going throw this controler.
 * 
 * @author L.Pelov
 */
public class AppController implements com.oracle.wci.user.registration.client.presenter.Presenter {

	private final HandlerManager eventBus;
	private final RegisteringServiceAsync rpcService;
	private HasWidgets container;

	public AppController(RegisteringServiceAsync rpcService, HandlerManager eventBus) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		bind();
	}

	private void bind() {
		// History.addValueChangeHandler(this);

		eventBus.addHandler(LoadEvent.TYPE, new LoadEventHandler() {
			public void onLoad(LoadEvent event) {
				doLoadEvent(event.getLoader());
			}
		});

	}

	public void go(HasWidgets container) {
		this.container = container;
	}

	/**
	 * Load presenter view controler
	 * @param token
	 */
	public void load(String token) {
		doLoadEvent(token);
	}

	/**
	 * Use to load the presenter
	 * @param token
	 */
	private void doLoadEvent(String token) {

		if (token != null) {

			Presenter presenter = null;

			if (token.equals("grid")) {
				presenter = new HelloPresenter(rpcService, eventBus, new HelloView());
			}
			else if (token.equals("reinvite")) {
				// presenter = new ReinvitePresenter(rpcService, eventBus, new ReinviteView());
			}
			else if (token.equals("confirm")) {
				// presenter = new ConfirmationPresenter(rpcService, eventBus, new ConfirmationView());
			}
			else if (token.equals("pwdreset")) {
				// presenter = new RwdResetPresenter(rpcService, eventBus, new PwdResetView());
			}

			if (presenter != null) {
				presenter.go(container);
			}
			
		}
	}

}
