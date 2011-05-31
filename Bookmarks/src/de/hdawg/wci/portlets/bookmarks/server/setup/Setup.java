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
package de.hdawg.wci.portlets.bookmarks.server.setup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdawg.wci.portlets.bookmarks.shared.Bookmark;

/**
 * @author Hauke Wesselmann
 */
public class Setup {

	public static void main(String[] args) {
		try {
			clean();
			setupDB();
			createFixtureData();
			verifyData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void clean() {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			Connection con = DriverManager
					.getConnection("jdbc:derby:war/wcsBookmarks;create=true");

			System.out
					.println("established connection. Begin cleaning schema:");

			Statement st = con.createStatement();
			st.execute("DROP TABLE bookmarks");

			System.out.println("dropped table bookmarks");

			st.execute("DROP TABLE configuration");

			System.out.println("dropped table configuration");

			st.close();
			con.close();

			System.out.println("Closed connection. Finished");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void setupDB() {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			Connection con = DriverManager
					.getConnection("jdbc:derby:war/wcsBookmarks;create=true");

			System.out
					.println("established connection. Begin creating schema:");

			Statement st = con.createStatement();
			st.execute("CREATE TABLE bookmarks "
					+ "(id BIGINT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY, "
					+ "portletid BIGINT NOT NULL, "
					+ "objectid BIGINT NOT NULL, "
					+ "name VARCHAR(100) NOT NULL, "
					+ "objecttype VARCHAR(20) NOT NULL, "
					+ "uri VARCHAR(600) NOT NULL, "
					+ "description VARCHAR(600), "
					+ "positioninlist INT NOT NULL)");

			System.out.println("created table bookmarks");

			st.execute("CREATE TABLE configuration "
					+ "(id BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "
					+ "portletid BIGINT NOT NULL, "
					+ "showdescription SMALLINT, " + "showicon SMALLINT)");

			System.out.println("created table configuration");

			st.close();
			con.close();

			System.out.println("Closed connection. Finished");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void createFixtureData() {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			Connection con = DriverManager
					.getConnection("jdbc:derby:war/wcsBookmarks;create=true");

			System.out
					.println("established connection. Begin insertig some data:");

			Statement st = con.createStatement();
			st.execute("INSERT INTO bookmarks (portletid, objectid, objecttype, positioninlist, name, uri) VALUES (1, 1, 'link', 1, 'Web.de', 'http://www.web.de')");
			st.execute("INSERT INTO bookmarks (portletid, objectid, objecttype, positioninlist, name, uri) VALUES (1, 2, 'community', 2, 'Meine Seite', 'http://www.yahoo.de')");

			System.out.println("created some bookmark samples");

			st.execute("INSERT INTO configuration (portletid, showdescription, showicon) VALUES (1, 0, 1)");

			System.out.println("created sample configuration");

			st.close();
			con.close();

			System.out.println("Closed connection. Finished");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void verifyData() throws Exception {
		ArrayList<Bookmark> bookmarks = new ArrayList<Bookmark>();

		Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
		Connection con = DriverManager
				.getConnection("jdbc:derby:war/wcsBookmarks;create=true");

		Statement stmt = con.createStatement();
		ResultSet rs = stmt
				.executeQuery("SELECT * FROM bookmarks WHERE portletid=1");
		while (rs.next()) {
			Bookmark b = new Bookmark();
			b.setId(rs.getInt("id"));
			b.setObjectId(rs.getInt("objectid"));
			b.setObjectType(rs.getString("objecttype"));
			b.setPortletId(1);
			b.setPositionInList(rs.getInt("positioninlist"));
			b.setName(rs.getString("name"));
			b.setUri(rs.getString("uri"));

			if (true) {
				b.setHasIcon(true);
			}

			bookmarks.add(b);
		}

		stmt.close();
		con.close();
		System.out.println(bookmarks.size());
		for (int i = 0; i < bookmarks.size(); i++) {
			System.out.println(bookmarks.get(i).getName());
		}
	}
}
