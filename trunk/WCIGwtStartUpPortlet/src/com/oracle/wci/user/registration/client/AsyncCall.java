/*
 * Copyright 2010 L.Pelov Licensed under the Apache License, 
 * Version 2.0 (the "License"); you may not use this file except 
 * in compliance with the License. You may obtain a copy of the 
 * License at 
 * 		
 * 			http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions 
 * and limitations under the License.
 */
package com.oracle.wci.user.registration.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestTimeoutException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.i18n.client.Dictionary;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.RpcRequestBuilder;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 * Implements the AsynCallBacks
 * 
 * @author L.Pelov
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

		if (getGatewayedServiceURL() == null || getGatewayedServiceURL().length() == 0) {
			if (GWT.isClient()) {
				moduleURL = "";
			}
			else {
				moduleURL = GWT.getModuleBaseURL();
			}
			endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "registering");
		}
		else {
			moduleURL = getGatewayedServiceURL();
			endpoint.setServiceEntryPoint(getGatewayedServiceURL() + "registering");
		}
		
		endpoint.setRpcRequestBuilder(theBuilder);
	}

	/**
	 * Set up max time out for the RPC Request
	 * 
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

	/**
	 * Entry interface use to build the query string.
	 * 
	 * @author L.Pelov, 10.05.2010
	 */
	public static interface Entry {
		String getName();

		String getValue();
	}

	/**
	 * Build string query for POST or GET body.
	 * 
	 * @param queryEntries
	 * @return
	 */
	public static String buildQueryString(Entry[] queryEntries) {
		StringBuffer sb = new StringBuffer();

		for (int i = 0, n = queryEntries.length; i < n; i++) {
			Entry queryEntry = queryEntries[i];

			if (i > 0) {
				sb.append("&");
			}

			String encodeName = URL.encodeComponent(queryEntry.getName());
			sb.append(encodeName);

			sb.append("=");

			String encodeValue = URL.encodeComponent(queryEntry.getValue());
			sb.append(encodeValue);
		}

		return sb.toString();
	}

	/**
	 * Do post information via AJAX call to the server.
	 * @param url
	 * @param requestData
	 */
	public static void doPost(String url, String requestData) {
		RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, url);

		try {
			builder.setTimeoutMillis(MAX_TIMEOUT);

			// Request response = 
			builder.sendRequest(requestData, new RequestCallback() {
				public void onResponseReceived(Request request, Response response) {
				}

				public void onError(Request request, Throwable exception) {
					if (exception instanceof RequestTimeoutException) {
						Window.alert(((RequestTimeoutException) exception).getMessage());
					}
					else {
						Window.alert(exception.getMessage());
					}
				}
			});

		}
		catch (com.google.gwt.http.client.RequestException e) {
			Window.alert("Unable to send the request: " + e.getMessage());
		}
	}
}
