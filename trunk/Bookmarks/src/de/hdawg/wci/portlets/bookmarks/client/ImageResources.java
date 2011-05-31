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
package de.hdawg.wci.portlets.bookmarks.client;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * @author Hauke Wesselmann
 */
public interface ImageResources extends ClientBundle {	
	// object-types in the portal
	@Source("resources/blue-folder.png")
	ImageResource folder();
	
	@Source("resources/store.png")
	ImageResource comunity();
	
	@Source("resources/report.png")
	ImageResource document();
	
	@Source("resources/blog.png")
	ImageResource page();
	
	@Source("resources/globe.png")
	ImageResource weblink();
	
	@Source("resources/blue-folder--plus.png")
	ImageResource folder_add();
	
	@Source("resources/store--plus.png")
	ImageResource comunity_add();
	
	@Source("resources/report--plus.png")
	ImageResource document_add();
	
	@Source("resources/blog--plus.png")
	ImageResource page_add();
	
	@Source("resources/globe--plus.png")
	ImageResource weblink_add();
	
	// control actions
	@Source("resources/control-090.png")
	ImageResource sort_up();
	
	@Source("resources/control-270.png")
	ImageResource sort_down();
	
	@Source("resources/minus-circle.png")
	ImageResource delete();
}
