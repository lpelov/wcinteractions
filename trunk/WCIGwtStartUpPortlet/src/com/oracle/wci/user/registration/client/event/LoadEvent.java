package com.oracle.wci.user.registration.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Loader event handler
 * @author L.Pelov
 */
public class LoadEvent extends GwtEvent<LoadEventHandler> {
	public static Type<LoadEventHandler> TYPE = new Type<LoadEventHandler>();
	private String loader;

	public LoadEvent(String loader) {
		this.loader = loader;
	}

	public String getLoader() {
		return loader;
	}

	@Override
	public Type<LoadEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(LoadEventHandler handler) {
		handler.onLoad(this);
	}
}
