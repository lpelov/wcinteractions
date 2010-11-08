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
