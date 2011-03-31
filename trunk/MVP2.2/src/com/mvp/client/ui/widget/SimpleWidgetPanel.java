package com.mvp.client.ui.widget;

import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Custom widget implementing the HasOneWidget interface.
 * @author L.Pelov
 */
public class SimpleWidgetPanel extends LayoutPanel implements HasOneWidget {

	private Widget widget;

	@Override
	public void setWidget(IsWidget w) {
		setWidget(asWidgetOrNull(w));
	}

	@Override
	public Widget getWidget() {
		return widget;
	}

	@Override
	public void setWidget(Widget w) {
		// Validate
		if (w == widget) {
			return;
		}

		// Detach new child.
		if (w != null) {
			w.removeFromParent();
		}

		// Remove old child.
		if (widget != null) {
			remove(widget);
		}

		// Logical attach.
		widget = w;

		if (w != null) {
			super.add(widget);

			adopt(w);
		}
	}

}
