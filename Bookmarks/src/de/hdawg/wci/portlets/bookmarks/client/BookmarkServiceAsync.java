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

import java.util.ArrayList;


import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdawg.wci.portlets.bookmarks.shared.Bookmark;
import de.hdawg.wci.portlets.bookmarks.shared.PortalObject;

/**
 * @author Hauke Wesselmann
 */
public interface BookmarkServiceAsync {
	public void getBookmarks(AsyncCallback<ArrayList<Bookmark>> callback);
	public void updateBookmarks(ArrayList<Bookmark> bookmarks, AsyncCallback<ArrayList<Bookmark>> callback);
	public void hasEditRightsOnPortlet(AsyncCallback<Boolean> callback);
	public void getPortalObjects(int objectType, int rootFolderId, AsyncCallback<ArrayList<PortalObject>> callback);
	public void getPortalObjectMetaData(PortalObject portalObject, AsyncCallback<String> callback);
}
