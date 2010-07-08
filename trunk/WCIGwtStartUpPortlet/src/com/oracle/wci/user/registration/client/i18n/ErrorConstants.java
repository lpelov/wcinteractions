package com.oracle.wci.user.registration.client.i18n;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface ErrorConstants extends Constants{

	ErrorConstants msgs = GWT.create(ErrorConstants.class);
	
	String RpcServerError();
	
}
