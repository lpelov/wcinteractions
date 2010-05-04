package com.oracle.wci.user.registration.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.i18n.client.Dictionary;
import com.google.gwt.user.client.rpc.RpcRequestBuilder;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 * Implements the AsynCallBacks
 * 
 * @author L.Pelov, Oracle
 * 
 */
public class AsyncCall {

	private static int MAX_TIMEOUT = 30000; // in milisec
	private static RegisteringServiceAsync service = null;
	private static String moduleURL = null;
	private static String portletID = "$$PORTLET_ID$$";

	public AsyncCall() {
		initializeService();
	}

	public AsyncCall(String id) {
		portletID = id;
		initializeService();
	}

	public static String getModuleURL() {
		return moduleURL;
	}

	public RegisteringServiceAsync getService() {
		return service;
	}

	/**
	 * Get the url from the dictionary 
	 * @return
	 */
	private static String getGatewayedServiceURL() {
		String serviceid = "serviceURL" + portletID;
		Dictionary theme = Dictionary.getDictionary(serviceid);
		String url = theme.get("gatewayPrefixURL");
		return url;
	}

	/**
	 * Easy way to init GWT service URL.
	 */
	private static void initializeService() {
		
		RpcRequestBuilder theBuilder = new TimeOutRpcRequestBuilder();
		
		service = (RegisteringServiceAsync) GWT.create(RegisteringService.class);
		final ServiceDefTarget endpoint = (ServiceDefTarget) service;

		final String moduleURL;

		if (getGatewayedServiceURL() == null || getGatewayedServiceURL().length() == 0) {
			moduleURL = GWT.getModuleBaseURL() + "registering";
		}
		else {
			moduleURL = getGatewayedServiceURL();
		}

		endpoint.setServiceEntryPoint(moduleURL);
		endpoint.setRpcRequestBuilder(theBuilder);
	}

	/**
	 * Set up max time out for the RPC Request
	 * @author L.Pelov, 04.05.2010
	 */
	public static class TimeOutRpcRequestBuilder extends RpcRequestBuilder {
		@Override
		protected RequestBuilder doCreate(String serviceEntryPoint) {
			RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, serviceEntryPoint);
			builder.setTimeoutMillis(MAX_TIMEOUT); // in milisecounds
			return builder;
		}

	}
}
