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
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.hdawg.wci.portlets.bookmarks.client.BookmarkServiceAsync;
import de.hdawg.wci.portlets.bookmarks.client.event.AdminBookmarksCancelledEvent;
import de.hdawg.wci.portlets.bookmarks.client.event.AdminBookmarksSavedEvent;
import de.hdawg.wci.portlets.bookmarks.shared.Bookmark;

/**
 * @author Hauke Wesselmann
 */
public class AdminBookmarksPresenter implements Presenter {
	
	private static int DIRECTION_UP = 0;
	//private static int DIRECTION_DOWN = 1;
	
	private ArrayList<Bookmark> bookmarkList = new ArrayList<Bookmark>();
	private ArrayList<Bookmark> deletedBookmarks = new ArrayList<Bookmark>();
	
	public interface Display {
		HasClickHandlers getSaveButton();
		HasClickHandlers getCancelButton();
		HasClickHandlers getList();
		HasClickHandlers getAction();
		DialogBox getEditPopup();
		HasClickHandlers getEditWidgetCancelButton();
		HasClickHandlers getEditWidgetSaveButton();
		HasClickHandlers getEditWidgetRefreshButton();
		HasClickHandlers getEditWidgetTargetButton();
		void initEditWidgetFields(String objectType);
		Bookmark getEditWidgetBookmark();
		void setEditWidgetBookmark(Bookmark bookmark);
		int getClickedAction(ClickEvent event);
		int getClickedRow(ClickEvent event);
		int getClickedColumn(ClickEvent event);
		void setData(List<Bookmark> data);
		Widget asWidget();
	}
	
	private final BookmarkServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	public AdminBookmarksPresenter(BookmarkServiceAsync rpcService, HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}
	
	public void bind() {
		this.display.getCancelButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new AdminBookmarksCancelledEvent());
			}
		});
		
		this.display.getSaveButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				saveBookmarks();
			}
		});
		
		this.display.getList().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				int row = display.getClickedRow(event);
				if(row != -1) {
					int column = display.getClickedColumn(event);
					if(column == 4) {
						deleteBookmark(row);
					} else if(column == 3 || column == 2) {
						sortBoorkmarks(row, column - 2);
					} else {
						Bookmark bookmark = bookmarkList.get(row);
						display.setEditWidgetBookmark(bookmark);
						display.initEditWidgetFields(bookmark.getObjectType());
						
						display.getEditPopup().center();
						display.getEditPopup().show();
					}
				}
			}
		});
	
		this.display.getAction().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(display.getClickedAction(event) == 0) {
					display.initEditWidgetFields("community");
				} else if(display.getClickedAction(event) == 1) {
					display.initEditWidgetFields("page");
				} else if(display.getClickedAction(event) == 2) {
					display.initEditWidgetFields("folder");
				} else if(display.getClickedAction(event) == 3) {
					display.initEditWidgetFields("document");
				} else if(display.getClickedAction(event) == 4) {
					display.initEditWidgetFields("link");
				} else {
					display.initEditWidgetFields("link");
				}
				
				display.getEditPopup().center();
				display.getEditPopup().show();
			}
		});
		
		this.display.getEditWidgetSaveButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Bookmark bookmark = display.getEditWidgetBookmark();
				display.setEditWidgetBookmark(null);
				if(!bookmark.getName().equalsIgnoreCase("") && bookmark.getName() != null) {
					
					if(bookmarkList.contains(bookmark)) {
						bookmarkList.set(bookmarkList.indexOf(bookmark), bookmark);
					} else {
						bookmarkList.add(bookmark);
					}
					
					display.setEditWidgetBookmark(null);
					display.getEditPopup().hide();
					display.setData(bookmarkList);
				}
			}
		});
		
		this.display.getEditWidgetCancelButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				display.setEditWidgetBookmark(null);
				display.getEditPopup().hide();
			}
		});
		
		this.display.getEditWidgetRefreshButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				refreshObjectData();
			}
		});
	}
	
	@Override
	public void go(HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
		fetchBookmarkList();
	}
	
	public Bookmark getBookmark(int index) {
		return bookmarkList.get(index);
	}

	public void setBookmarkList(ArrayList<Bookmark> bookmarkList) {
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
	
	private void saveBookmarks() {
		if(deletedBookmarks.size() > 0) {
			bookmarkList.addAll(deletedBookmarks);
		}
		rpcService.updateBookmarks(bookmarkList, new AsyncCallback<ArrayList<Bookmark>>() {
			@Override
			public void onSuccess(ArrayList<Bookmark> result) {
				eventBus.fireEvent(new AdminBookmarksSavedEvent());
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO insert some reasonable error-handling
				Window.alert("Error fetching bookmarks.");
			}
		});
	}
	
	private void sortBoorkmarks(int positionInList, int directionToSort) {
		Bookmark tempHelperBookmark;
		if(directionToSort == DIRECTION_UP) {
			if(positionInList > 0) {
				tempHelperBookmark = bookmarkList.get(positionInList - 1);
				bookmarkList.set(positionInList - 1, bookmarkList.get(positionInList));
				bookmarkList.set(positionInList, tempHelperBookmark);
			}
		} else {
			if(positionInList < bookmarkList.size() - 1) {
				tempHelperBookmark = bookmarkList.get(positionInList + 1);
				bookmarkList.set(positionInList + 1, bookmarkList.get(positionInList));
				bookmarkList.set(positionInList, tempHelperBookmark);
			}
		}
		
		display.setData(bookmarkList);
	}
	
	private void deleteBookmark(int positionInList) {
		Bookmark tempHelperBookmark = bookmarkList.get(positionInList);
		tempHelperBookmark.setPositionInList(-1);
		bookmarkList.remove(tempHelperBookmark);
		if(deletedBookmarks == null) {
			deletedBookmarks = new ArrayList<Bookmark>();
		}
		deletedBookmarks.add(tempHelperBookmark);
		display.setData(bookmarkList);
	}
	
	private void refreshObjectData() {
		rpcService.getPortalObjectMetaData(null, new AsyncCallback<String>() {
			@Override
			public void onFailure(Throwable caught) {
				// TODO insert some reasonable error-handling
				Window.alert("Error fetching object-data.");
			}

			@Override
			public void onSuccess(String result) {
				Bookmark bm = display.getEditWidgetBookmark();
				bm.setDescription(result);
				display.setEditWidgetBookmark(bm);
				display.initEditWidgetFields(bm.getObjectType());
			}
		});
		
	}
}
