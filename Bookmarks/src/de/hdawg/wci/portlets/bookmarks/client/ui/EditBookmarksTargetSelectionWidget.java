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
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.Widget;

import de.hdawg.wci.portlets.bookmarks.client.i18n.BookmarkConstants;

/**
 * @author Hauke Wesselmann
 */
public class EditBookmarksTargetSelectionWidget extends Composite {
	private final Button saveButton = new Button();
	private final Button cancelButton = new Button();
	private FlexTable contentTable = new FlexTable();
	private Tree objectTree = new Tree();
	private BookmarkConstants localizedLabels = GWT.create(BookmarkConstants.class);
	
	public EditBookmarksTargetSelectionWidget() {
		
		initWidget(contentTable);

		HorizontalPanel hPanel = new HorizontalPanel();
		saveButton.setText(localizedLabels.saveButtonLabel());
		cancelButton.setText(localizedLabels.cancelButtonLabel());
		hPanel.add(saveButton);
		hPanel.add(cancelButton);

		ScrollPanel treePanel = new ScrollPanel();
		treePanel.add(objectTree);

		contentTable.setWidget(0, 0, hPanel);
		contentTable.setWidget(1, 0, treePanel);
	}

	public void initTreeData() {

	}

	public Widget asWidget() {
		return this;
	}
}
