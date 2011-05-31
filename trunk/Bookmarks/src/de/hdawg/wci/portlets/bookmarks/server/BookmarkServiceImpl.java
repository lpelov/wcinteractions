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

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdawg.wci.portlets.bookmarks.client.BookmarkService;
import de.hdawg.wci.portlets.bookmarks.shared.Bookmark;
import de.hdawg.wci.portlets.bookmarks.shared.PortalObject;

/**
 * @author Hauke Wesselmann
 */
@SuppressWarnings("serial")
public class BookmarkServiceImpl extends RemoteServiceServlet implements BookmarkService{

	@Override
	public ArrayList<Bookmark> getBookmarks() {
		try {
			DatabaseHelper dbh = new DatabaseHelper();
			// TODO uncomment when integrating into portal
			//IPortletContext portletContext = PortletContextFactory.createPortletContext(getThreadLocalRequest(), getThreadLocalResponse());
			//Configuration config = dbh.retrieveConfigurationForPortlet(portletContext.getRequest().getPortletID());
			Configuration config = dbh.retrieveConfigurationForPortlet(1);
			
			ArrayList<Bookmark> bookmarks = dbh.retrieveBookmarksForPortlet(config);
			// FIXME run through ObjectManagers and verify access by user
			return bookmarks;
		} catch(Exception e) {
			// TODO do some reasonable error-handling
			GWT.log("", e);
			return null;
		}
	}
	
	@Override
	public ArrayList<Bookmark> updateBookmarks(ArrayList<Bookmark> bookmarks) {
		ArrayList<Bookmark> bookmarksToSave = bookmarks;
		try {
			// TODO uncomment when integrating into portal
			//IPortletContext portletContext = PortletContextFactory.createPortletContext(getThreadLocalRequest(), getThreadLocalResponse());
			DatabaseHelper dbh = new DatabaseHelper();
			//Configuration config = dbh.retrieveConfigurationForPortlet(portletContext.getRequest().getPortletID());
			//PortletACL acl = new PortletACL(portletContext, portletContext.getRequest().getPortletID());
			//if(acl.hasAccessRight(AccessLevel.EDIT)) {
				Configuration config = dbh.retrieveConfigurationForPortlet(1);
				for(int i = 0; i < bookmarks.size(); i++) {
					Bookmark bookmark = bookmarks.get(i);
					GWT.log(bookmark.getName());
					if(bookmark.getPositionInList() == -1 && bookmark.getPortletId() == config.getPortletId()) {
						bookmarksToSave.remove(bookmark);
					}
				}
		
				dbh.updateBookmarksForPortlet(bookmarksToSave, config);
			//}
		
		} catch(Exception e) {
			GWT.log("", e);
		}
		return bookmarksToSave;
	}

	@Override
	public Boolean hasEditRightsOnPortlet() {
		// TODO uncomment when placing in portal!
		//IPortletContext portletContext = PortletContextFactory.createPortletContext(getThreadLocalRequest(), getThreadLocalResponse());
		//PortletACL acl = new PortletACL(portletContext, portletContext.getRequest().getPortletID());
		//return acl.hasAccessRight(AccessLevel.EDIT);
		return true;
	}

	@Override
	public ArrayList<PortalObject> getPortalObjects(int objectType, int rootFolderId) {
		PortalObjectHelper poh = new PortalObjectHelper();
		return poh.getChildrenOfRootObject(rootFolderId, objectType);
	}

	@Override
	public String getPortalObjectMetaData(PortalObject portalObject) {
		PortalObjectHelper poh = new PortalObjectHelper();
		return poh.refreshObjectMetaData(portalObject);
	}
}
