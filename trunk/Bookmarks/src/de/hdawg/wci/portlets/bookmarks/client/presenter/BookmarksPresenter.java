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
package de.hdawg.wci.portlets.bookmarks.client.presenter;

import java.util.ArrayList;
import java.util.List;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.hdawg.wci.portlets.bookmarks.client.BookmarkServiceAsync;
import de.hdawg.wci.portlets.bookmarks.client.event.AdminBookmarksEvent;
import de.hdawg.wci.portlets.bookmarks.shared.Bookmark;

/**
 * @author Hauke Wesselmann
 */
public class BookmarksPresenter implements Presenter {
	private List<Bookmark> bookmarkList = new ArrayList<Bookmark>();
	
	public interface Display {
		HasClickHandlers getAdminButton();
		HasClickHandlers getList();
		void setAdminVisible(boolean visible);
		void setData(List<Bookmark> data);
		Widget asWidget();
	}
	
	private final BookmarkServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	public BookmarksPresenter(BookmarkServiceAsync rpcService, HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}
	
	public void bind() {
		display.getAdminButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new AdminBookmarksEvent());
			}
		});
	}
	
	@Override
	public void go(HasWidgets container) {
		checkEditRights();
		bind();
		container.clear();
		container.add(display.asWidget());
		fetchBookmarkList();
	}

	public Bookmark getBookmark(int index) {
		return bookmarkList.get(index);
	}

	public void setBookmarkList(List<Bookmark> bookmarkList) {
		this.bookmarkList = bookmarkList;
	}
	
	private void fetchBookmarkList() {
		rpcService.getBookmarks(new AsyncCallback<ArrayList<Bookmark>>() {
			public void onSuccess(ArrayList<Bookmark> result) {
				bookmarkList = result;
				display.setData(bookmarkList);
			}
			
			public void onFailure(Throwable caught) {
				// TODO insert some reasonable error-handling
				Window.alert("Error fetching bookmarks.");
			}
		});
	}
	
	private void checkEditRights() {
		rpcService.hasEditRightsOnPortlet(new AsyncCallback<Boolean>() {
			public void onSuccess(Boolean result) {
				display.setAdminVisible(result);
			}
			
			public void onFailure(Throwable caught) {
				// TODO insert some reasonable error-handling
				Window.alert("Error fetching access rights.");
			}
		});
	}
}
