package com.oracle.wci.user.registration.client;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.oracle.wci.user.registration.client.presenter.HelloPresenter;
import com.oracle.wci.user.registration.client.presenter.Presenter;
import com.oracle.wci.user.registration.client.view.HelloView;

/**
 * Application main controler, all operations are going throw this controler.
 * 
 * @author L.Pelov, Oracle
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

//		eventBus.addHandler(UploadEvent.TYPE, new UploadEventHandler() {
//			public void onUpload(UploadEvent event) {
//				doUploadEvent(event.getCsFoldeId());
//			}
//		});

		
	}

	public void go(HasWidgets container) {
		this.container = container;
	}

	public void load(String token) {

		if (token != null) {

			Presenter presenter = null;

			if (token.equals("grid")) {
				presenter = new HelloPresenter(rpcService, eventBus, new HelloView());
			}
//			else if (token.equals("add")) {
				// presenter = new EditContactPresenter(rpcService, eventBus, new EditContactView());
//			}

			if (presenter != null) {
				presenter.go(container);
			}
		}
	}

//	private void doUploadEvent(String csFolderId) {
//		Presenter presenter = new UploadPresenter(rpcService, new UploadView(), csFolderId);
//		presenter.go(container);
//	}
	
}
