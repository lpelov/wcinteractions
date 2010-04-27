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
	 * Get the GWT Gatewayed Serivce URL
	 */
//	private static native String getGatewayedServiceURL()
//	/*-{		
//		if ($wnd.initPortletServiceURL){
//			return $wnd.initPortletServiceURL();
//		}
//		
//		//if ($wnd.serviceURL$$PORTLET_ID$$) {
//		//	return $wnd.serviceURL$$PORTLET_ID$$;
//		//}		
//		return null;
//	}-*/;

	private static String getGatewayedServiceURL() {
		String serviceid = "serviceURL" + portletID;
		//Window.alert("ServiceID: " + serviceid);
		Dictionary theme = Dictionary.getDictionary(serviceid);
		String url = theme.get("gatewayPrefixURL");

		return url;		
	}
	
	/**
	 * Easy way to init GWT service URL.
	 */
	private static void initializeService() {

		service = (RegisteringServiceAsync) GWT.create(RegisteringService.class);
		final ServiceDefTarget endpoint = (ServiceDefTarget) service;

		final String moduleURL;
		//Window.alert("ServiceURL: " + getGatewayedServiceURL());
		
		if (getGatewayedServiceURL() == null || getGatewayedServiceURL().length() == 0) {
			moduleURL = GWT.getModuleBaseURL() + "registering";
		}
		else {
			moduleURL = getGatewayedServiceURL();
		}
		
		endpoint.setServiceEntryPoint(moduleURL);
		
//		RpcRequestBuilder builder = new RpcRequestBuilder();
//		builder.create(moduleURL);
//		builder.finish().setTimeoutMillis(1);
//		endpoint.setRpcRequestBuilder(builder);
		
/*
 *                 RequestBuilder rb = serviceAsync.getServerInfo(input, new
AsyncCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                        }
                        @Override
                        public void onFailure(Throwable caught) {
                        }
                });
                rb.setTimeoutMillis(6000);
                try {
                        rb.send();
                } catch (RequestException e) {
                } 		
 */
	}

	public class MyRpcRequestBuilder extends RpcRequestBuilder {
		
		@Override
		protected RequestBuilder doCreate(String serviceEntryPoint) {
			RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, serviceEntryPoint);
			builder.setTimeoutMillis(1000); // in milisecounds
			return builder;
		}

	}

}
