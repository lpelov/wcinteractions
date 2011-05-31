/*
 * Copyright 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package de.hdawg.wci.portlets.bookmarks.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Hauke Wesselmann
 */
public class PortalObject implements IsSerializable {
	private int portalId;
	private String name;
	private int portalObjectType;

	public int getPortalId() {
		return portalId;
	}

	public void setPortalId(int portalId) {
		this.portalId = portalId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPortalObjectType() {
		return portalObjectType;
	}

	public void setPortalObjectType(int portalObjectType) {
		this.portalObjectType = portalObjectType;
	}
}
