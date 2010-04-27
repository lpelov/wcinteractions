package com.oracle.wci.user.registration.client.i18n;

import com.google.gwt.i18n.client.ConstantsWithLookup;

public interface RegistrationValidationConstants extends ConstantsWithLookup {

	public String positiveInteger();

	public String integerInRangeMinus5000Plus5000();

	public String anyInteger();

	public String stringLengthSmaller5();

	public String stringLengthBetween2And5();

	public String notEmpty();

	public String userName();
	
	// Description
	public String userNameHelp();
	
	public String regexNotMatched();
}
