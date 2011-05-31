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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

import de.hdawg.wci.portlets.bookmarks.client.ImageResources;
import de.hdawg.wci.portlets.bookmarks.client.i18n.BookmarkConstants;
import de.hdawg.wci.portlets.bookmarks.client.presenter.AdminBookmarksPresenter;
import de.hdawg.wci.portlets.bookmarks.client.ui.EditBookmarksWidget;
import de.hdawg.wci.portlets.bookmarks.client.ui.Hyperlink;
import de.hdawg.wci.portlets.bookmarks.shared.Bookmark;

/**
 * @author Hauke Wesselmann
 */
public class AdminBookmarksView extends Composite implements AdminBookmarksPresenter.Display {

	private final Hyperlink addFolderButton = new Hyperlink();
	private final Hyperlink addDocumentButton = new Hyperlink();
	private final Hyperlink addPageButton = new Hyperlink();
	private final Hyperlink addCommunityButton = new Hyperlink();
	private final Hyperlink addWeblinkButton = new Hyperlink();
	private final Button saveButton = new Button();
	private final Button cancelButton = new Button();
	private FlexTable bookmarkTable = new FlexTable();
	private FlexTable actionTable = new FlexTable();
	private final FlexTable contentTable = new FlexTable();
	private final DialogBox editViewDialog = new DialogBox();
	private EditBookmarksWidget editBookmarkWidget = new EditBookmarksWidget();
	
	public AdminBookmarksView() {
		BookmarkConstants localizedLabels = GWT.create(BookmarkConstants.class);
		ImageResources IMAGES = GWT.create(ImageResources.class);
		initWidget(contentTable);
		
		addCommunityButton.setTitle(localizedLabels.addCommunityButtonTitle());
		addCommunityButton.setResource(IMAGES.comunity_add());
		addCommunityButton.setStyleName("hd-bookmark-imagelink");
		
		addPageButton.setTitle(localizedLabels.addPageButtonTitle());
		addPageButton.setResource(IMAGES.page_add());
		addPageButton.setStyleName("hd-bookmark-imagelink");
		
		addFolderButton.setTitle(localizedLabels.addFolderButtonTitle());
		addFolderButton.setResource(IMAGES.folder_add());
		addFolderButton.setStyleName("hd-bookmark-imagelink");
		
		addDocumentButton.setTitle(localizedLabels.addDocumentButtonTitle());
		addDocumentButton.setResource(IMAGES.document_add());
		addDocumentButton.setStyleName("hd-bookmark-imagelink");
		
		addWeblinkButton.setTitle(localizedLabels.addWeblinkButtonTitle());
		addWeblinkButton.setResource(IMAGES.weblink_add());
		addWeblinkButton.setStyleName("hd-bookmark-imagelink");
		
		actionTable.setWidget(0, 0, addCommunityButton);
		actionTable.setWidget(0, 1, addPageButton);
		actionTable.setWidget(0, 2, addFolderButton);
		actionTable.setWidget(0, 3, addDocumentButton);
		actionTable.setWidget(0, 4, addWeblinkButton);
		
		HorizontalPanel hPanelBottom = new HorizontalPanel();
		saveButton.setText(localizedLabels.saveButtonLabel());
		hPanelBottom.add(saveButton);
		
		cancelButton.setText(localizedLabels.cancelButtonLabel());
		hPanelBottom.add(cancelButton);
		
		contentTable.setWidth("100%");
		contentTable.setCellPadding(2);
		contentTable.setCellSpacing(2);
		contentTable.setBorderWidth(0);
		contentTable.setStylePrimaryName("hd-bookmark-table");
		contentTable.setWidget(0, 0, actionTable);
		contentTable.setWidget(1, 0, bookmarkTable);
		contentTable.setWidget(2, 0, hPanelBottom);
		
		editViewDialog.setModal(true);
		editViewDialog.setGlassEnabled(true);
		editViewDialog.setText(localizedLabels.editPopupHeading());
		editViewDialog.add(editBookmarkWidget);
		editViewDialog.setAnimationEnabled(true);
	}
	
	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getCancelButton() {
		return cancelButton;
	}

	@Override
	public HasClickHandlers getList() {
		return bookmarkTable;
	}

	@Override
	public HasClickHandlers getSaveButton() {
		return saveButton;
	}
	
	@Override
	public void setData(List<Bookmark> data) {
		ImageResources IMAGES = GWT.create(ImageResources.class);
		bookmarkTable.removeAllRows();
		
		for(int i = 0; i < data.size(); i++) {
			int tableCellId = 0;
			
			FlexCellFormatter cellFormatter = bookmarkTable.getFlexCellFormatter();
			
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
				
			cellFormatter.setVerticalAlignment(i, tableCellId, HasVerticalAlignment.ALIGN_TOP);
				
			tableCellId++;
			
			String nameAndDescriptionContent = data.get(i).getName()
					+ "<br/><span class='hd-object-description-small'>"
					+ data.get(i).getDescription()
					+ "</span><br/><span class='hd-object-uri-small'>"
					+ data.get(i).getUri()
					+ "</span>";
			HTML nameAndDescription = new HTML(nameAndDescriptionContent);
			bookmarkTable.setWidget(i, tableCellId, nameAndDescription);
			tableCellId++;
			
			Hyperlink sort_up = new Hyperlink();
			sort_up.setResource(IMAGES.sort_up());
			
			Hyperlink sort_down = new Hyperlink();
			sort_down.setResource(IMAGES.sort_down());
			
			Hyperlink delete = new Hyperlink();
			delete.setResource(IMAGES.delete());
			
			bookmarkTable.setWidget(i, tableCellId, sort_up);
			cellFormatter.setVerticalAlignment(i, tableCellId, HasVerticalAlignment.ALIGN_TOP);
			tableCellId++;
			bookmarkTable.setWidget(i, tableCellId, sort_down);
			cellFormatter.setVerticalAlignment(i, tableCellId, HasVerticalAlignment.ALIGN_TOP);
			tableCellId++;
			bookmarkTable.setWidget(i, tableCellId, delete);
			cellFormatter.setVerticalAlignment(i, tableCellId, HasVerticalAlignment.ALIGN_TOP);
		}	
	}

	@Override
	public int getClickedAction(ClickEvent event) {
		HTMLTable.Cell cell = actionTable.getCellForEvent(event);
		return cell.getCellIndex();
	}
	
	@Override
	public int getClickedColumn(ClickEvent event) {
		HTMLTable.Cell cell = bookmarkTable.getCellForEvent(event);
		return cell.getCellIndex();
	}

	@Override
	public int getClickedRow(ClickEvent event) {
		HTMLTable.Cell cell = bookmarkTable.getCellForEvent(event);
		if(cell.getCellIndex() >= 0)
			return cell.getRowIndex();
		else
			return -1;
	}

	@Override
	public HasClickHandlers getAction() {
		return actionTable;
	}

	@Override
	public DialogBox getEditPopup() {
		return editViewDialog;
	}

	@Override
	public HasClickHandlers getEditWidgetCancelButton() {
		return editBookmarkWidget.getCancelButton();
	}

	@Override
	public HasClickHandlers getEditWidgetSaveButton() {
		return editBookmarkWidget.getSaveButton();
	}

	@Override
	public HasClickHandlers getEditWidgetRefreshButton() {
		return editBookmarkWidget.getUpdateMetaDataButton();
	}

	@Override
	public HasClickHandlers getEditWidgetTargetButton() {
		return editBookmarkWidget.getSelectTargetButton();
	}

	@Override
	public void initEditWidgetFields(String objectType) {
		editBookmarkWidget.setObjectType(objectType);
		editBookmarkWidget.setData();
	}

	@Override
	public Bookmark getEditWidgetBookmark() {
		return editBookmarkWidget.getBookmark();
	}

	@Override
	public void setEditWidgetBookmark(Bookmark bookmark) {
		editBookmarkWidget.setBookmark(bookmark);
	}
}
