package com.oracle.wci.user.registration.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Handler bus event, to fire load event.
 * @author L.Pelov
 */
public interface LoadEventHandler extends EventHandler {
  void onLoad(LoadEvent event);  
}
