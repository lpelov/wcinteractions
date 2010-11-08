package com.mvp.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class CellTablePlace extends Place {

	private String cellTableName;

	public CellTablePlace(String token) {
		this.cellTableName = token;
	}

	public String getCellTableName() {
		return cellTableName;
	}

	public static class Tokenizer implements PlaceTokenizer<CellTablePlace> {
		@Override
		public String getToken(CellTablePlace place) {
			return place.getCellTableName();
		}

		@Override
		public CellTablePlace getPlace(String token) {
			return new CellTablePlace(token);
		}
	}
}
