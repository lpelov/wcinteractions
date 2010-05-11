package com.oracle.wci.user.registration.client.presenter;

import com.google.gwt.http.client.RequestTimeoutException;

/**
 * Use this function to extend the presenter interface.
 * 
 * @author L.Pelov, 10.05.2010
 */
public abstract class PresenterBase {

	/**
	 * Handle RPC returned error message, depending on the exception instance
	 * 
	 * @param caught
	 * @return
	 */
	public String onError(Throwable caught) {
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
