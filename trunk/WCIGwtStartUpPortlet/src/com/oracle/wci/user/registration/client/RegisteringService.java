package com.oracle.wci.user.registration.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("registering")
public interface RegisteringService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
}
