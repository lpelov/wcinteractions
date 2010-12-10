package com.mvp.client.ui;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface CellTableSortingView extends IsWidget {

	void setName(String tableName);

	void setPresenter(Presenter presenter);

	public interface Presenter {
		void goTo(Place place);
	}	
}
