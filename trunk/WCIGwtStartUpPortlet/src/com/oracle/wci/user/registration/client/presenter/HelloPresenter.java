package com.oracle.wci.user.registration.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.http.client.RequestTimeoutException;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.oracle.wci.user.registration.client.RegisteringServiceAsync;

import eu.maydu.gwt.validation.client.ValidationProcessor;

/**
 * Hello presenter to test the MVP GWT Framework
 * @author L.Pelov, Oracle
 *
 */
public class HelloPresenter implements Presenter{

	public interface Display {	
		ValidationProcessor getValidator();
		
		HasClickHandlers getSendButton();
		
		HasText getTextBox();
		
		Widget asWidget();
	}

	private final RegisteringServiceAsync rpcService;
	@SuppressWarnings("unused")
	private final HandlerManager eventBus;
	private final Display display;
	
	public HelloPresenter(RegisteringServiceAsync rpcService, HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;		
	}
	
	public void bind() {
		// use to bind Display interface
		// Example:
		//		display.getDeleteButton().addClickHandler(new ClickHandler() {
		//			public void onClick(ClickEvent event) {
		//				deleteSelectedContacts();
		//			}
		//		});
		
		display.getSendButton().addClickHandler(new ClickHandler() {			
			public void onClick(ClickEvent event) {
				//TODO: Implement validator
				//if (display.getValidator().validate()) {
					sendNameToServer();
				//}				
			}
		});		
	}
	
	/**
	 * Bind the view to the root panel. In case you use for example just a DialogBox you don't need to
	 * implement this method!
	 */
	public void go(HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());		
	}
	
	private void sendNameToServer() {
		
		rpcService.greetServer(display.getTextBox().getText(), new AsyncCallback<String>() {
			
			public void onSuccess(String result) {
				Window.alert(result);
			}
			
			public void onFailure(Throwable caught) {
				Window.alert(onError(caught));
			}
		});
		
	}

	/**
	 * Error handler
	 * @param caught
	 * @return
	 */
	private String onError(Throwable caught) {
		String msg;

		// in case of timeout exception
		if (caught instanceof RequestTimeoutException) {
			msg = ((RequestTimeoutException) caught).getMessage();
		}
		else {
			msg = caught.getMessage();
		}

		return msg;
	}
	
}
