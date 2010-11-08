package com.mvp.client.ui;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;

public interface CellTableView extends IsWidget {

	void setName(String helloName);

	HasText getTextBox();
	
	void setPresenter(Presenter presenter);

	public interface Presenter {

		void goTo(Place place);

		void onButtonClicked();
	}

}
