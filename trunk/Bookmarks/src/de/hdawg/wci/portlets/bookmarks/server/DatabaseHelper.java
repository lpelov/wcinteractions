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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdawg.wci.portlets.bookmarks.shared.Bookmark;

/**
 * @author Hauke Wesselmann
 */
public class DatabaseHelper {
	
	private Connection con = null;
	private Statement stmt;
	private ResultSet rs;
	
	private void establishConnection() throws Exception {
		if(con == null) {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			con = DriverManager.getConnection("jdbc:derby:wcsBookmarks;create=true");
		}
	}
	
	private void closeConnection() throws Exception {
		if(con != null) {
			//DriverManager.getConnection("jdbc:derby:wcsBookmarks;shutdown=true");
		}
	}
	
	public Configuration retrieveConfigurationForPortlet(int portletId) throws Exception {
		Configuration config = new Configuration();
		establishConnection();
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * FROM configuration WHERE portletid=" + portletId);
		if(rs.next()) {
			config.setId(rs.getInt("id"));
			config.setPortletId(portletId);
			config.setShowDescription(rs.getBoolean("showdescription"));
			config.setShowIcon(rs.getBoolean("showicon"));
		}
		rs.close();
		stmt.close();
		closeConnection();
		
		return config;
	}
	
	public void storeConfigurationForPortlet(Configuration config) throws Exception {
		String sql;
		establishConnection();
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * FROM configuration WHERE portletid=" + config.getPortletId());
		if(rs.next()) {
			sql = "UPDATE configuration SET showdescription='" + config.isShowDescription() + "', showicon='" + config.isShowIcon() + "' WHERE portletid=" + config.getPortletId();
		} else {
			sql = "INSERT INTO configuration (portletid, showdescription, showicon) VALUES('" + config.getPortletId() + "', '" + config.isShowDescription() + "', '" + config.isShowIcon() + "')";
		}
		
		rs.close();
		stmt.execute(sql);
		
		stmt.close();
		closeConnection();
	}
	
	public ArrayList<Bookmark> retrieveBookmarksForPortlet(Configuration config) throws Exception {
		ArrayList<Bookmark> bookmarks = new ArrayList<Bookmark>();
		establishConnection();
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * FROM bookmarks WHERE portletid=" + config.getPortletId() + " ORDER BY positioninlist");
		while(rs.next()) {
			Bookmark b = new Bookmark();
			b.setId(rs.getInt("id"));
			b.setObjectId(rs.getInt("objectid"));
			b.setObjectType(rs.getString("objecttype"));
			b.setPortletId(config.getPortletId());
			b.setPositionInList(rs.getInt("positioninlist"));
			b.setUri(rs.getString("uri"));
			b.setName(rs.getString("name"));
			
			if(config.isShowIcon()) {
				b.setHasIcon(true);
			} else {
				b.setHasIcon(false);
			}
			
			if(config.isShowDescription()) {
				b.setDescription(rs.getString("description"));
			}
			bookmarks.add(b);
		}
		
		stmt.close();
		con.close();
		
		return bookmarks;
	}
	
	public ArrayList<Bookmark> updateBookmarksForPortlet(ArrayList<Bookmark> bookmarks, Configuration config) throws Exception {
		establishConnection();
		String sql;
		stmt = con.createStatement();
		stmt.execute("DELETE FROM bookmarks WHERE portletid='" + config.getPortletId() + "'");
		
		for(int i = 0; i < bookmarks.size(); i++) {
			Bookmark currBookmark = bookmarks.get(i); 
			sql = "INSERT INTO bookmarks (portletid, objectid, objecttype, positioninlist, name, uri, description) VALUES (" + currBookmark.getPortletId() + ", " + currBookmark.getObjectId() + ", '" + currBookmark.getObjectType() + "', " + (i + 1) + ", '" + currBookmark.getName() + "', '" + currBookmark.getUri() + "', '" + currBookmark.getDescription() + "')";
			stmt.execute(sql);
		}
		
		stmt.close();
		con.close();
		
		return bookmarks;
	}
}
