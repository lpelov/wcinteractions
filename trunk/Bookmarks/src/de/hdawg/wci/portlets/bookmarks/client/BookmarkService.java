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


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdawg.wci.portlets.bookmarks.shared.Bookmark;
import de.hdawg.wci.portlets.bookmarks.shared.PortalObject;

/**
 * @author Hauke Wesselmann
 */
@RemoteServiceRelativePath("bookmarkService")
public interface BookmarkService extends RemoteService {
	ArrayList<Bookmark> getBookmarks();
	ArrayList<Bookmark> updateBookmarks(ArrayList<Bookmark> bookmarks);
	Boolean hasEditRightsOnPortlet();
	ArrayList<PortalObject> getPortalObjects(int objectType, int rootFolderId);
	String getPortalObjectMetaData(PortalObject portalObject);
}
