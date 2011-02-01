package com.mvp.client.ui;

import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.user.cellview.client.Header;

/**
 * Clickable cell is always string, so our sortable header is getting only
 * string
 * 
 * @author L.Pelov
 */
public abstract class SortableHeader extends Header<String> {

	private boolean reverseSort = false;
	protected boolean sorted = false;
	private String text;

	public SortableHeader(String text) {
		super(new ClickableTextCell());
		this.text = text;
	}

	public boolean getReverseSort() {
		return reverseSort;
	}

	@Override
	public String getValue() {
		return text;
	}

	public void setReverseSort(boolean reverseSort) {
		this.reverseSort = reverseSort;
	}

	public void setSorted(boolean sorted) {
		this.sorted = sorted;
	}

	public void toggleReverseSort() {
		this.reverseSort = !this.reverseSort;
	}

}
