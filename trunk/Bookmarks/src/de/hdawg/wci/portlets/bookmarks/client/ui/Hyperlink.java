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
package de.hdawg.wci.portlets.bookmarks.client.ui;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Image;

/**
 * Extends standard GWT-hyperlink for using images as link text
 * 
 * @author Hauke Wesselmann
 */
public class Hyperlink extends com.google.gwt.user.client.ui.Hyperlink {

	public Hyperlink() {

	}

	public void setResource(ImageResource imageResource) {
		Image img = new Image(imageResource);
		DOM.appendChild(DOM.getFirstChild(getElement()), img.getElement());
	}
}
