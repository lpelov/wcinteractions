package com.mvp.client.ui.widget;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Custom widget implementing the HasOneWidget interface.
 * 
 * @author L.Pelov
 */
public class SimpleWidgetPanel extends LayoutPanel implements AcceptsOneWidget {

	private IsWidget IsWidget = null;

	@Override
	public void setWidget(IsWidget w) {

		if (w == IsWidget) {
			return;
		}

		Widget widget = asWidgetOrNull(w);
		
		if (widget != null) {
			super.remove(widget);
		}

		IsWidget = widget;

		if (widget != null) {
			super.add(widget);
		}

	}
}
