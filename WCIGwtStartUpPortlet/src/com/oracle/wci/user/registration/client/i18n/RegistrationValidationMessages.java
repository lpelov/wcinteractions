package com.oracle.wci.user.registration.client.i18n;

import com.google.gwt.core.client.GWT;
import eu.maydu.gwt.validation.client.i18n.ValidationMessages;
import eu.maydu.gwt.validation.client.i18n.utils.MessageInterpolator;

/**
 * Internationalize the error validator messages.
 * 
 * @author L.Pelov
 */
public class RegistrationValidationMessages extends ValidationMessages {

	private RegistrationValidationConstants msg = GWT.create(RegistrationValidationConstants.class);

	public String getPropertyName(String propertyName) {
		if (propertyName.equals("positiveInteger")) return msg.positiveInteger();
		else if (propertyName.equals("integerInRangeMinus5000Plus5000")) return msg
				.integerInRangeMinus5000Plus5000();
		else if (propertyName.equals("anyInteger")) return msg.anyInteger();
		else if (propertyName.equals("stringLengthSmaller5")) return msg.stringLengthSmaller5();
		else if (propertyName.equals("stringLengthBetween2And5")) return msg.stringLengthBetween2And5();
		else if (propertyName.equals("notEmpty")) return msg.notEmpty();
		else if (propertyName.equals("username")) return msg.userName();

		return "unknown property name";
	}

	public RegistrationValidationConstants getPropertyNameConstants() {
		return msg;
	}

	public String getDescriptionMessage(String key) {
		// if(key.equals("test"))
		// return "Some html description text<br/><font style=\"color:#f00;\">Cool!</font>";

		if (key.equals("userNameHelp")) return msg.userNameHelp();

		return "Unknown key: " + key;
	}

	@Override
	public String getCustomMessage(String key, Object... parameters) {

		if (key.equals("regexNotMatched")) return MessageInterpolator
				.merge(msg.regexNotMatched(), parameters);
		else if (key.equals("userNameHelp")) return msg.userNameHelp();

		return null;

		// return "localization not found for key: "+key;
	}

}
