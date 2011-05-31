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

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

import de.hdawg.wci.portlets.bookmarks.client.event.AdminBookmarksCancelledEvent;
import de.hdawg.wci.portlets.bookmarks.client.event.AdminBookmarksCancelledEventHandler;
import de.hdawg.wci.portlets.bookmarks.client.event.AdminBookmarksEvent;
import de.hdawg.wci.portlets.bookmarks.client.event.AdminBookmarksEventHandler;
import de.hdawg.wci.portlets.bookmarks.client.event.AdminBookmarksSavedEvent;
import de.hdawg.wci.portlets.bookmarks.client.event.AdminBookmarksSavedEventHandler;
import de.hdawg.wci.portlets.bookmarks.client.presenter.AdminBookmarksPresenter;
import de.hdawg.wci.portlets.bookmarks.client.presenter.BookmarksPresenter;
import de.hdawg.wci.portlets.bookmarks.client.presenter.Presenter;
import de.hdawg.wci.portlets.bookmarks.client.view.AdminBookmarksView;
import de.hdawg.wci.portlets.bookmarks.client.view.BookmarksView;

/**
 * @author Hauke Wesselmann
 */
public class AppController implements Presenter, ValueChangeHandler<String> {
	private final HandlerManager eventBus;
	private final BookmarkServiceAsync rpcService;
	private HasWidgets container;

	public AppController(BookmarkServiceAsync rpcService,
			HandlerManager eventBus) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		bind();
	}

	private void bind() {
		History.addValueChangeHandler(this);

		eventBus.addHandler(AdminBookmarksEvent.TYPE,
				new AdminBookmarksEventHandler() {
					@Override
					public void onAdminBookmarks(AdminBookmarksEvent event) {
						doAdminBookmarks();
					}
				});

		eventBus.addHandler(AdminBookmarksSavedEvent.TYPE,
				new AdminBookmarksSavedEventHandler() {
					@Override
					public void onAdminBookmarksSaved(
							AdminBookmarksSavedEvent event) {
						doAdminBookmarksSaved();
					}
				});

		eventBus.addHandler(AdminBookmarksCancelledEvent.TYPE,
				new AdminBookmarksCancelledEventHandler() {
					@Override
					public void onCancelAdmin(AdminBookmarksCancelledEvent event) {
						doAdminBookmarksCancelled();
					}
				});
	}

	private void doAdminBookmarks() {
		History.newItem("admin");
	}

	private void doAdminBookmarksCancelled() {
		History.newItem("list");
	}

	private void doAdminBookmarksSaved() {
		History.newItem("list");
	}

	public void go(final HasWidgets container) {
		this.container = container;

		if ("".equals(History.getToken())) {
			History.newItem("list");
		} else {
			History.fireCurrentHistoryState();
		}
	}

	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();

		if (token != null) {
			Presenter presenter = null;

			if (token.equals("list")) {
				presenter = new BookmarksPresenter(rpcService, eventBus,
						new BookmarksView());
			} else if (token.equals("admin")) {
				presenter = new AdminBookmarksPresenter(rpcService, eventBus,
						new AdminBookmarksView());
			}

			if (presenter != null) {
				presenter.go(container);
			}
		}
	}
}