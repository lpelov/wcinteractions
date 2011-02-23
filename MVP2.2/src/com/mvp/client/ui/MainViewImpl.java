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
package com.mvp.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Main Widgets view implementation.
 * 
 * @author L.Pelov
 */
public class MainViewImpl extends Composite implements MainView {

	Presenter presenter;
	SimplePanel viewPanel = new SimplePanel();

	public MainViewImpl() {
		initWidget(viewPanel);
	}

	@Override
	public void setWidgetName(String widgetName) {
		// recognize which widget to load
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

}
