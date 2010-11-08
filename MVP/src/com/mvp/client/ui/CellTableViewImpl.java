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
package com.mvp.client.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import com.mvp.client.ui.ContactDatabase.Category;
import com.mvp.client.ui.ContactDatabase.ContactInfo;

public class CellTableViewImpl extends Composite implements HasText,
		CellTableView {

	private static CellTableViewImplUiBinder uiBinder = GWT
			.create(CellTableViewImplUiBinder.class);

	interface CellTableViewImplUiBinder extends
			UiBinder<Widget, CellTableViewImpl> {
	}

	// public CellTableViewImpl() {
	// initWidget(uiBinder.createAndBindUi(this));
	// }

	@UiField
	Button button;

	@UiField
	TextBox mytextbox;

	/**
	 * The main CellTable.
	 */
	@UiField(provided = true)
	CellTable<ContactInfo> cellTable;

	/**
	 * The pager used to change the range of data.
	 */
	@UiField(provided = true)
	SimplePager pager;

	// UI Binders
	private Presenter presenter;

	public CellTableViewImpl(/* String firstName */) {
		// Create a CellTable.

		// Set a key provider that provides a unique key for each contact. If
		// key is
		// used to identify contacts when fields (such as the name and address)
		// change.
		cellTable = new CellTable<ContactDatabase.ContactInfo>(
				ContactDatabase.ContactInfo.KEY_PROVIDER);

		// Create a Pager to control the table.
		SimplePager.Resources pagerResources = GWT
				.create(SimplePager.Resources.class);
		pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0,
				true);
		pager.setDisplay(cellTable);

		// Add a selection model so we can select cells.
		final SingleSelectionModel<ContactInfo> selectionModel = new SingleSelectionModel<ContactInfo>(
				ContactInfo.KEY_PROVIDER);
		cellTable.setSelectionModel(selectionModel);

		// Initialize the columns.
		initTableColumns(selectionModel);

		// Add the CellList to the adapter in the database.
		ContactDatabase.get().addDataDisplay(cellTable);

		// Create the UiBinder.
		// Binder uiBinder = GWT.create(Binder.class);
		// Widget widget = uiBinder.createAndBindUi(this);

		initWidget(uiBinder.createAndBindUi(this));
		button.setText("Click me");
	}

	@UiHandler("button")
	void onClick(ClickEvent e) {
		// Window.alert("Hello!");
		// this.presenter.goTo(new HelloPlace("after table"));
		presenter.onButtonClicked();
	}

	public void setText(String text) {
		// button.setText(text);
	}

	public String getText() {
		// return button.getText();
		return "";
	}

	public HasText getTextBox() {
		return mytextbox;
	}

	@Override
	public void setName(String helloName) {

	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	/**
	 * Add the columns to the table.
	 */
	private void initTableColumns(
			final SelectionModel<ContactInfo> selectionModel) {
		// Checkbox column. This table will uses a checkbox column for
		// selection.
		// Alternatively, you can call cellTable.setSelectionEnabled(true) to
		// enable
		// mouse selection.
		Column<ContactInfo, Boolean> checkColumn = new Column<ContactInfo, Boolean>(
				new CheckboxCell(true)) {
			@Override
			public Boolean getValue(ContactInfo object) {
				// Get the value from the selection model.
				return selectionModel.isSelected(object);
			}
		};
		checkColumn.setFieldUpdater(new FieldUpdater<ContactInfo, Boolean>() {
			public void update(int index, ContactInfo object, Boolean value) {
				// Called when the user clicks on a checkbox.
				selectionModel.setSelected(object, value);
			}
		});
		cellTable
				.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br>"));

		// First name.
		// Column<ContactInfo, String> firstNameColumn = new Column<ContactInfo,
		// String>(
		// new EditTextCell()) {
		// @Override
		// public String getValue(ContactInfo object) {
		// return object.getFirstName();
		// }
		// };

		// TextHeader firstNameHeader = new TextHeader("First name");
		// firstNameHeader.setUpdater(new ValueUpdater<String>() {
		// @Override
		// public void update(String value) {
		// Window.alert("Update the header");
		// }
		// });
		//
		// cellTable.addColumn(firstNameColumn, firstNameHeader);
		//
		// firstNameColumn
		// .setFieldUpdater(new FieldUpdater<ContactInfo, String>() {
		// public void update(int index, ContactInfo object,
		// String value) {
		// // Called when the user changes the value.
		// object.setFirstName(value);
		// ContactDatabase.get().refreshDisplays();
		// }
		// });

		addColumn("First name", new TextCell(),
				new GetValue<ContactInfo, String>() {
					public String getValue(ContactInfo object) {
						return object.getFirstName();
					}
				}, "Firstname");

		// Last name.
		Column<ContactInfo, String> lastNameColumn = new Column<ContactInfo, String>(
				new EditTextCell()) {
			@Override
			public String getValue(ContactInfo object) {
				return object.getLastName();
			}
		};
		cellTable.addColumn(lastNameColumn, "Last name");
		lastNameColumn.setFieldUpdater(new FieldUpdater<ContactInfo, String>() {
			public void update(int index, ContactInfo object, String value) {
				// Called when the user changes the value.
				object.setLastName(value);
				ContactDatabase.get().refreshDisplays();
			}
		});

		// Category.
		final Category[] categories = ContactDatabase.get().queryCategories();
		List<String> categoryNames = new ArrayList<String>();
		for (Category category : categories) {
			categoryNames.add(category.getDisplayName());
		}
		SelectionCell categoryCell = new SelectionCell(categoryNames);
		Column<ContactInfo, String> categoryColumn = new Column<ContactInfo, String>(
				categoryCell) {
			@Override
			public String getValue(ContactInfo object) {
				return object.getCategory().getDisplayName();
			}
		};
		cellTable.addColumn(categoryColumn, "Category");
		categoryColumn.setFieldUpdater(new FieldUpdater<ContactInfo, String>() {
			public void update(int index, ContactInfo object, String value) {
				for (Category category : categories) {
					if (category.getDisplayName().equals(value)) {
						object.setCategory(category);
					}
				}
				ContactDatabase.get().refreshDisplays();
			}
		});

		// Address.
		addColumn("Address", new TextCell(),
				new GetValue<ContactInfo, String>() {
					public String getValue(ContactInfo object) {
						return object.getAddress();
					}
				}, "Address");

		// cellTable.addColumn(new Column<ContactInfo, String>(new TextCell()) {
		// @Override
		// public String getValue(ContactInfo object) {
		// return object.getAddress();
		// }
		// }, "Address");
	}

	public interface GetValue<T, C> {
		C getValue(T object);
	}

	private final List<SortableHeader> allHeaders = new ArrayList<SortableHeader>();

	/**
	 * The field to sort by.
	 */
	// private String orderBy = "Address";

	/**
	 * Add a column of a {@link Comparable} type using default comparators.
	 * 
	 * @param <C>
	 *            the column type
	 * @param table
	 *            the table
	 * @param text
	 *            the header text
	 * @param cell
	 *            the cell used to render values
	 * @param getter
	 *            the {@link GetValue} used to retrieve cell values
	 * @return the new column
	 */

	private <C extends Comparable<C>> Column<ContactInfo, C> addColumn(
			final String text, final Cell<C> cell,
			final GetValue<ContactInfo, C> getter, final String property) {

		return addColumn(text, cell, getter, property,
				createColumnComparator(getter, false),
				createColumnComparator(getter, true));
	}

	/**
	 * Add a sortable column to the table.
	 * 
	 * @param <C>
	 *            the data type for the column
	 * @param text
	 *            the header text
	 * @param cell
	 *            the cell used to render the column
	 * @param getter
	 *            the getter to retrieve the value for the column
	 * @param property
	 *            the property to sort by
	 * @return the column
	 */
	private <C> Column<ContactInfo, C> addColumn(final String text,
			final Cell<C> cell, final GetValue<ContactInfo, C> getter,
			final String property, final Comparator<ContactInfo> ascComparator,
			final Comparator<ContactInfo> descComparator) {

		final Column<ContactInfo, C> column = new Column<ContactInfo, C>(cell) {
			@Override
			public C getValue(ContactInfo object) {
				return getter.getValue(object);
			}
		};

		final SortableHeader header = new SortableHeader(text);
		allHeaders.add(header);

		// Sort created by default.
		// if ("created".equals(property)) {
		// header.setSorted(true);
		// header.setReverseSort(true);
		// orderBy = "created" + " DESC";
		// }

		header.setUpdater(new ValueUpdater<String>() {
			public void update(String value) {
				header.setSorted(true);
				header.toggleReverseSort();

				for (SortableHeader otherHeader : allHeaders) {
					if (otherHeader != header) {
						otherHeader.setSorted(false);
						otherHeader.setReverseSort(true);
					}
				}

				sortExpenses(ContactDatabase.get().getDataProvider().getList(),
						header.getReverseSort() ? descComparator
								: ascComparator);

				cellTable.redrawHeaders();

				// Request sorted rows.
				// orderBy = property;
				// if (header.getReverseSort()) {
				// orderBy += " DESC";
				// }

				// Go to the first page of the newly-sorted results
				// pager.firstPage();
				// ContactDatabase.get().sort();

				// Window.alert("sort here now");
			}
		});
		cellTable.addColumn(column, header);
		return column;
	}

	// private Comparator<ContactInfo> lastComparator;

	private void sortExpenses(List<ContactInfo> list,
			final Comparator<ContactInfo> comparator) {
		// lastComparator = comparator;
		Collections.sort(list, comparator);
	}

	private <C extends Comparable<C>> Comparator<ContactInfo> createColumnComparator(
			final GetValue<ContactInfo, C> getter, final boolean descending) {
		return new Comparator<ContactInfo>() {
			public int compare(ContactInfo o1, ContactInfo o2) {
				// Null check the row object.
				if (o1 == null && o2 == null) {
					return 0;
				} else if (o1 == null) {
					return descending ? 1 : -1;
				} else if (o2 == null) {
					return descending ? -1 : 1;
				}

				// Compare the column value.
				C c1 = getter.getValue(o1);
				C c2 = getter.getValue(o2);
				if (c1 == null && c2 == null) {
					return 0;
				} else if (c1 == null) {
					return descending ? 1 : -1;
				} else if (c2 == null) {
					return descending ? -1 : 1;
				}
				int comparison = c1.compareTo(c2);
				return descending ? -comparison : comparison;
			}
		};
	}

}
