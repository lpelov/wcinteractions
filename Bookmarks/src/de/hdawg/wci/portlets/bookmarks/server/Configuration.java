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
package de.hdawg.wci.portlets.bookmarks.server;

/**
 * @author Hauke Wesselmann
 */
public class Configuration {
	private int id;
	private int portletId;
	private boolean showDescription;
	private boolean showIcon;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPortletId() {
		return portletId;
	}
	
	public void setPortletId(int portletId) {
		this.portletId = portletId;
	}
	
	public boolean isShowDescription() {
		return showDescription;
	}
	
	public void setShowDescription(boolean showDescription) {
		this.showDescription = showDescription;
	}
	
	public boolean isShowIcon() {
		return showIcon;
	}
	
	public void setShowIcon(boolean showIcon) {
		this.showIcon = showIcon;
	}	
}
