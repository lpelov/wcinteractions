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
