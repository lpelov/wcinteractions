package com.mvp.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;

public class CellTableSortingViewImpl extends Composite implements
		CellTableSortingView {

	SimplePanel viewPanel = new SimplePanel();
	Element nameSpan = DOM.createSpan();
	Presenter presenter;

	public CellTableSortingViewImpl() {
		viewPanel.getElement().appendChild(nameSpan);
		initWidget(viewPanel);
	}

	@Override
	public void setName(String tableName) {
		nameSpan.setInnerText("Table name: " + tableName);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;

	}

}
