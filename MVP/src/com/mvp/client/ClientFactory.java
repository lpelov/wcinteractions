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
package com.mvp.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.mvp.client.ui.CellTableSortingView;
import com.mvp.client.ui.CellTableView;
import com.mvp.client.ui.GoodbyeView;
import com.mvp.client.ui.HelloView;

public interface ClientFactory {
	EventBus getEventBus();

	PlaceController getPlaceController();

	HelloView getHelloView();

	GoodbyeView getGoodbyeView();

	CellTableView getCellTableView();

	CellTableSortingView getCellTableSortableView();
}
