package com.mvp.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class CellTableSortingPlace extends Place {

	private String cellTableName;

	public CellTableSortingPlace(String token) {
		this.cellTableName = token;
	}

	public String getCellTableName() {
		return cellTableName;
	}

	public static class Tokenizer implements PlaceTokenizer<CellTableSortingPlace> {
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
