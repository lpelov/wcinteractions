/*
 * Copyright 2010 L.Pelov Licensed under the Apache License, 
 * Version 2.0 (the "License"); you may not use this file except 
 * in compliance with the License. You may obtain a copy of the 
 * License at 
 * 		
 * 			http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions 
 * and limitations under the License.
 */
package com.oracle.wci.portlet168.server;

import java.util.HashSet;
import java.util.Set;

import javax.portlet.PortletPreferences;
import javax.portlet.PreferencesValidator;
import javax.portlet.ValidatorException;

/**
 * PortletPreferencesValidator implements preferences validation and is fired
 * inside processAction(), when you try to save portlet preferences.
 * 
 * @author L.Pelov
 */
public class PortletPreferencesValidator implements PreferencesValidator {

	/**
	 * Validates portlet preferences. It will be called on preferences.store(),
	 * normaly inside the processAction() function.
	 */
	public void validate(PortletPreferences preferences)
			throws ValidatorException {

		Set<String> errorsSet = new HashSet<String>();
		errorsSet.clear();

		// validate the properties you wish...
		String pref1 = preferences.getValue("pref1", "bad value");

		// We've ensured the data is there. Now, just call our QueryBean's
		// execute method.
		// If the SQL parse fails, catch it and ask user to check their form
		// data.
		try {
			
			//# do some string validations or what ever here!
			if (pref1 == null || "".equals(pref1)) {
				throw new Exception("Pref1 could not be null or empty");
			}
		}
		catch (Exception e) {
			
			// exception, you can put your log file here...
			throw new ValidatorException(e.toString(), errorsSet);
		}

	}

}
