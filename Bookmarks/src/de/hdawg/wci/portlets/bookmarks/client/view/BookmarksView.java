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
package de.hdawg.wci.portlets.bookmarks.client.view;

import java.util.List;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

import de.hdawg.wci.portlets.bookmarks.client.ImageResources;
import de.hdawg.wci.portlets.bookmarks.client.i18n.BookmarkConstants;
import de.hdawg.wci.portlets.bookmarks.client.presenter.BookmarksPresenter;
import de.hdawg.wci.portlets.bookmarks.shared.Bookmark;

/**
 * @author Hauke Wesselmann
 */
public class BookmarksView extends Composite implements BookmarksPresenter.Display {
	private final Button adminButton = new Button();
	private FlexTable bookmarkTable = new FlexTable();
	private final FlexTable contentTable = new FlexTable();
	private BookmarkConstants localizedLabels = GWT.create(BookmarkConstants.class);
	
	public BookmarksView() {
		
		initWidget(contentTable);
		
		HorizontalPanel hPanel = new HorizontalPanel();
		adminButton.setText(localizedLabels.editButtonLabel());
		hPanel.add(adminButton);
		
		contentTable.setWidth("100%");
		contentTable.setCellPadding(2);
		contentTable.setCellSpacing(2);
		contentTable.setBorderWidth(0);
		contentTable.setStylePrimaryName("hd-bookmark-table");
		contentTable.setWidget(0, 0, hPanel);
		contentTable.setWidget(1, 0, bookmarkTable);
	}
	
	@Override
	public HasClickHandlers getAdminButton() {
		return adminButton;
	}
	
	@Override
	public HasClickHandlers getList() {
		return bookmarkTable;
	}

	@Override
	public void setData(List<Bookmark> data) {
		ImageResources IMAGES = GWT.create(ImageResources.class);
		bookmarkTable.removeAllRows();
		
		for(int i = 0; i < data.size(); i++) {
			int tableCellId = 0;
			
			if(data.get(i).hasIcon()) {
				if(data.get(i).getObjectType().equals("folder"))
					bookmarkTable.setWidget(i, tableCellId, new Image(IMAGES.folder()));
				else if(data.get(i).getObjectType().equals("document"))
					bookmarkTable.setWidget(i, tableCellId, new Image(IMAGES.document()));
				else if(data.get(i).getObjectType().equals("community"))
					bookmarkTable.setWidget(i, tableCellId, new Image(IMAGES.comunity()));
				else if(data.get(i).getObjectType().equals("page"))
					bookmarkTable.setWidget(i, tableCellId, new Image(IMAGES.page()));
				else if(data.get(i).getObjectType().equals("link"))
					bookmarkTable.setWidget(i, tableCellId, new Image(IMAGES.weblink()));
				else
					bookmarkTable.setWidget(i, tableCellId, new Image(IMAGES.weblink()));
				
				tableCellId++;
			}
			if(data.get(i).getObjectType().equals("link")) {
				bookmarkTable.setWidget(i, tableCellId, new Anchor(data.get(i).getName(), true, data.get(i).getUri(), "_blank"));
			} else {
				bookmarkTable.setWidget(i, tableCellId, new Anchor(data.get(i).getName(), true, data.get(i).getUri()));
			}
			tableCellId++;
			
			if(data.get(i).getDescription() != null) {
				// TODO fix layout and enter description
			}
		}
	}
	
	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setAdminVisible(boolean visible) {
		adminButton.setVisible(visible);
	}
}
