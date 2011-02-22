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
package com.mvp.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * Sortable table place
 * 
 * @author L.Pelov
 */
public class CellTableSortingPlace extends Place {

	private String cellTableName;

	public CellTableSortingPlace(String token) {
		this.cellTableName = token;
	}

	public String getCellTableName() {
		return cellTableName;
	}

	public static class Tokenizer implements
			PlaceTokenizer<CellTableSortingPlace> {

		@Override
		public String getToken(CellTableSortingPlace place) {
			return place.getCellTableName();
		}

		@Override
		public CellTableSortingPlace getPlace(String token) {
			return new CellTableSortingPlace(token);
		}
	}
}
