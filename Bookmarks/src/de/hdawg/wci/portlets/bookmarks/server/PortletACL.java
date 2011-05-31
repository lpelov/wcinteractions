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

import com.plumtree.remote.portlet.IPortletContext;
import com.plumtree.remote.prc.AccessLevel;
import com.plumtree.remote.prc.IACL;
import com.plumtree.remote.prc.IACLEntry;
import com.plumtree.remote.prc.IPortletManager;
import com.plumtree.remote.prc.IRemoteSession;
import com.plumtree.remote.prc.RemoteSessionFactory;

/**
 * @author Hauke Wesselmann
 */
public class PortletACL {
	private IACL  acl;
	private IRemoteSession remoteSession;
	private int userId;
	private int objecId;
	private int[] userGroups;
	
	public PortletACL(IPortletContext context, int id) {
		try {
			remoteSession = RemoteSessionFactory.getRemotePortalSession(context);
			userId = context.getUser().getUserID();
			objecId = id;
			acl = initACL();
			userGroups = setUserGroups();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private int[] setUserGroups() {
		try {
			return remoteSession.getUserManager().getCurrentUserGroups();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private IACL initACL() {
		try {
			IPortletManager portletManager = remoteSession.getPortletManager();
			return portletManager.queryACL(objecId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Boolean hasAccessRight(AccessLevel level) {
		return hasUserAccess(level) || hasGroupAccess(level);
	}
	
	private Boolean hasUserAccess(AccessLevel level) {
		boolean result = false;
		if(acl != null) {
			 IACLEntry aclEntry = acl.getUserEntry(userId);
			 if(aclEntry != null) {
				 result = aclEntry.getAccessLevel().allows(level);
			 }
		}	
		return result;
	}
	
	private Boolean hasGroupAccess(AccessLevel level) {
		boolean result = false;
		if(userGroups != null && acl != null) {
			IACLEntry aclEntry = null;
			for(int i = 0; i < userGroups.length; i++) {
				aclEntry = acl.getUserGroupEntry(userGroups[i]);
				if(aclEntry != null) {
					if(aclEntry.getAccessLevel().allows(level)) {
						result = true;
						break;
					}
				}
			}
		}
		return result;
	}
}
