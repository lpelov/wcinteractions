package com.mvp.client.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.view.client.ListDataProvider;
import com.mvp.client.ui.CellTableViewImpl.GetValue;

/**
 * Provide table with sorting
 * 
 * @author L.Pelov
 * 
 * @param <T>
 *            - custom object you want to expose to the table
 */
public class CellTableSorting<T> extends CellTable<T> {

	private final List<SortableHeader> allHeaders = new ArrayList<SortableHeader>();
	private ListDataProvider<T> dataProvider = new ListDataProvider<T>();

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
	public <C extends Comparable<C>> Column<T, C> addColumn(final String text,
			final Cell<C> cell, final GetValue<T, C> getter) {

		return addColumn(text, cell, getter,
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
	 * @param ascComparator
	 *            ascendent comparator
	 * @param descComparator
	 *            descendant comparator
	 * @return the column
	 */
	private <C> Column<T, C> addColumn(final String text, final Cell<C> cell,
			final GetValue<T, C> getter, final Comparator<T> ascComparator,
			final Comparator<T> descComparator) {

		// gets the cell value
		final Column<T, C> column = new Column<T, C>(cell) {
			@Override
			public C getValue(T object) {
				return getter.getValue(object);
			}
		};

		final SortableHeader header = new SortableHeader(text);
		allHeaders.add(header);

		// call this every time headers is clicked
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

				// sort the clicked column
				sortExpenses(dataProvider.getList(),
						header.getReverseSort() ? descComparator
								: ascComparator);

				// cellTable.redrawHeaders();
				redrawHeaders();

				// Go to the first page of the newly-sorted results, if wished
				// pager.firstPage();
			}
		});

		addColumn(column, header);
		return column;
	}

	/**
	 * Proceed with the sorting operation
	 * 
	 * @param list
	 *            data to sort
	 * @param comparator
	 *            the {@link Comparator} used for the sorting
	 */
	private void sortExpenses(List<T> list, final Comparator<T> comparator) {
		Collections.sort(list, comparator);
	}

	/**
	 * Implements the comparator for our ContactInfo object.
	 * 
	 * @param <C>
	 *            the data type from the column
	 * @param getter
	 *            the {@link GetValue} used to retrieve cell values
	 * @param descending
	 *            way of sorting
	 * @return
	 */
	private <C extends Comparable<C>> Comparator<T> createColumnComparator(
			final GetValue<T, C> getter, final boolean descending) {

		return new Comparator<T>() {
			public int compare(T o1, T o2) {
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
