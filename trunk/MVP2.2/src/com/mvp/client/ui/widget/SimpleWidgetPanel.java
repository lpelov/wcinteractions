package com.mvp.client.ui.widget;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.LayoutPanel;

/**
 * Custom widget implementing the HasOneWidget interface.
 * @author L.Pelov
 */
public class SimpleWidgetPanel extends LayoutPanel implements AcceptsOneWidget {

    private IsWidget widget = null;
    
    @Override
    public void setWidget(IsWidget w) {
        if( widget != null) super.remove(widget);
        widget = w;
        if(w != null) super.add(w);
    }
}
