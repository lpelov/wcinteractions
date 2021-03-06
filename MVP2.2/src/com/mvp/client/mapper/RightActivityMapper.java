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
package com.mvp.client.mapper;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.mvp.client.ClientFactory;
import com.mvp.client.activity.CellTableActivity;
import com.mvp.client.activity.CellTableSortingActivity;
import com.mvp.client.activity.GoodbyeActivity;
import com.mvp.client.place.RootPlace;

public class RightActivityMapper implements ActivityMapper {

	private ClientFactory clientFactory;

	public RightActivityMapper(ClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		// The activity you will get here, is the one from the RootView!!!

		if (place instanceof RootPlace) {
			String name = ((RootPlace) place).getRootName();
			if (name.equalsIgnoreCase("table")) {
				return new CellTableActivity("table", clientFactory);
			}

			if (name.equalsIgnoreCase("table2")) {
				return new CellTableSortingActivity(null, clientFactory);
			}

		}
		return new GoodbyeActivity(clientFactory);

	}

}
