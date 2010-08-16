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
