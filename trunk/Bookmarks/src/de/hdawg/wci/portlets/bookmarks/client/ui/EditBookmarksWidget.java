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
package de.hdawg.wci.portlets.bookmarks.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import de.hdawg.wci.portlets.bookmarks.client.i18n.BookmarkConstants;
import de.hdawg.wci.portlets.bookmarks.shared.Bookmark;

/**
 * @author Hauke Wesselmann
 * @author L.Pelov
 */
public class EditBookmarksWidget extends Composite {

	private String objectType;
	private Bookmark bookmark;

	private final Button saveButton = new Button();
	private final Button cancelButton = new Button();
	private final Button updateMetaDataButton = new Button();
	private final Button selectTargetButton = new Button();
	private FlexTable bookmarkDetailTable = new FlexTable();
	private final FlexTable contentTable = new FlexTable();

	private Label labelForName = new Label();
	private Label labelForDescription = new Label();
	private Label labelForURI = new Label();
	private TextBox descriptionContent = new TextBox();
	private TextBox uriContent = new TextBox();
	private TextBox nameContent = new TextBox();
	private BookmarkConstants localizedLabels = GWT.create(BookmarkConstants.class);
	
	public EditBookmarksWidget() {
		
		initWidget(contentTable);

		HorizontalPanel hPanelTop = new HorizontalPanel();
		saveButton.setText(localizedLabels.saveButtonLabel());
		cancelButton.setText(localizedLabels.cancelButtonLabel());
		hPanelTop.add(saveButton);
		hPanelTop.add(cancelButton);

		labelForName.setText(localizedLabels.labelForName());
		labelForDescription.setText(localizedLabels.labelForDescription());
		labelForURI.setText(localizedLabels.labelForURI());

		bookmarkDetailTable.setWidget(0, 0, labelForName);
		bookmarkDetailTable.setWidget(0, 1, nameContent);
		bookmarkDetailTable.setWidget(1, 0, labelForDescription);
		bookmarkDetailTable.setWidget(1, 1, descriptionContent);
		bookmarkDetailTable.setWidget(2, 0, labelForURI);
		bookmarkDetailTable.setWidget(2, 1, uriContent);

		HorizontalPanel hPanelBottom = new HorizontalPanel();
		selectTargetButton.setText(localizedLabels.targetButtonLabel());
		updateMetaDataButton.setText(localizedLabels.updateButtonLabel());
		hPanelBottom.add(updateMetaDataButton);
		hPanelBottom.add(selectTargetButton);

		contentTable.setWidget(0, 0, hPanelTop);
		contentTable.setWidget(1, 0, bookmarkDetailTable);
		contentTable.setWidget(2, 0, hPanelBottom);
	}

	public HasClickHandlers getSaveButton() {
		return saveButton;
	}

	public HasClickHandlers getCancelButton() {
		return cancelButton;
	}

	public void setData() {
		if (objectType.equalsIgnoreCase("link")) {
			descriptionContent.setReadOnly(false);
			descriptionContent.setEnabled(true);
			uriContent.setReadOnly(false);
			uriContent.setEnabled(true);
			selectTargetButton.setEnabled(false);
			updateMetaDataButton.setEnabled(false);
		} else {
			descriptionContent.setReadOnly(true);
			descriptionContent.setEnabled(false);
			uriContent.setReadOnly(true);
			uriContent.setEnabled(false);
			selectTargetButton.setEnabled(true);
			updateMetaDataButton.setEnabled(true);
		}

		if (bookmark != null) {
			nameContent.setText(bookmark.getName());
			descriptionContent.setText(bookmark.getDescription());
			uriContent.setText(bookmark.getUri());
		} else {
			nameContent.setText("");
			descriptionContent.setText("");
			updateMetaDataButton.setEnabled(false);
			if (objectType.equalsIgnoreCase("link")) {
				uriContent.setText("http://");
			} else {
				uriContent.setText("");
			}
		}
	}

	private void updateData() {
		if (bookmark == null) {
			bookmark = new Bookmark();
		}

		bookmark.setName(nameContent.getText());
		bookmark.setUri(uriContent.getText());
		bookmark.setDescription(descriptionContent.getText());
		bookmark.setObjectType(objectType);
	}

	public HasClickHandlers getUpdateMetaDataButton() {
		return updateMetaDataButton;
	}

	public HasClickHandlers getSelectTargetButton() {
		return selectTargetButton;
	}

	public Widget asWidget() {
		return this;
	}

	public Bookmark getBookmark() {
		updateData();
		return bookmark;
	}

	public void setBookmark(Bookmark bookmark) {
		this.bookmark = bookmark;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
}
