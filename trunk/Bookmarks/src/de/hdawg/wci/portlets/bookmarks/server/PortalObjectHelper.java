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

import de.hdawg.wci.portlets.bookmarks.shared.PortalObject;

/**
 * @author Hauke Wesselmann
 */
public class PortalObjectHelper {
	
	public String refreshObjectMetaData(PortalObject object) {
		// TODO create real code using Portal-api
		
		return "Community für einen guten Start!";
	}
	
	public ArrayList<PortalObject> getChildrenOfRootObject(int rootFolderId, int objectId) {
		ArrayList<PortalObject> objects = new ArrayList<PortalObject>();
		// TODO do some prc-magic here...
		if(rootFolderId == 0) {
			PortalObject o1 = new PortalObject();
			o1.setName("Produkte");
			o1.setPortalId(101);
			o1.setPortalObjectType(512);
			objects.add(o1);
			PortalObject o2 = new PortalObject();
			o2.setName("Termine");
			o2.setPortalId(1);
			o2.setPortalObjectType(100);
			objects.add(o2);
			PortalObject o3 = new PortalObject();
			o3.setName("Pepsi");
			o3.setPortalId(102);
			o3.setPortalObjectType(512);
			objects.add(o3);
			PortalObject o4 = new PortalObject();
			o4.setName("Coke");
			o4.setPortalId(103);
			o4.setPortalObjectType(512);
			objects.add(o4);
		} else if(rootFolderId == 1) {
			PortalObject o2 = new PortalObject();
			o2.setName("Termine");
			o2.setPortalId(2);
			o2.setPortalObjectType(100);
			objects.add(o2);
			PortalObject o3 = new PortalObject();
			o3.setName("Pepsi");
			o3.setPortalId(102);
			o3.setPortalObjectType(512);
			objects.add(o3);
			PortalObject o4 = new PortalObject();
			o4.setName("Coke");
			o4.setPortalId(103);
			o4.setPortalObjectType(512);
			objects.add(o4);
		} else if(rootFolderId == 2) {
			PortalObject o3 = new PortalObject();
			o3.setName("Pepsi");
			o3.setPortalId(102);
			o3.setPortalObjectType(512);
			objects.add(o3);
			PortalObject o4 = new PortalObject();
			o4.setName("Coke");
			o4.setPortalId(103);
			o4.setPortalObjectType(512);
			objects.add(o4);
		}
		
		return objects;
	}
	
	@SuppressWarnings("unused")
	private ArrayList<PortalObject> sortObjectsByType(ArrayList<PortalObject> objects) {
		return objects;
	}
}
