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
package com.oracle.wci.user.registration.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.oracle.wci.user.registration.client.i18n.RegistrationValidationMessages;
import com.oracle.wci.user.registration.client.presenter.HelloPresenter;
import eu.maydu.gwt.validation.client.DefaultValidationProcessor;
import eu.maydu.gwt.validation.client.ValidationProcessor;
import eu.maydu.gwt.validation.client.actions.FocusAction;
import eu.maydu.gwt.validation.client.actions.LabelTextAction;
import eu.maydu.gwt.validation.client.actions.StyleAction;
import eu.maydu.gwt.validation.client.description.PopupDescription;
import eu.maydu.gwt.validation.client.validators.numeric.IntegerValidator;

/**
 * View class with validation, implements on MVP model.
 * 
 * @author L.Pelov
 */
public class HelloView extends Composite implements HelloPresenter.Display {

	final ValidationProcessor validation = new DefaultValidationProcessor(
			new RegistrationValidationMessages());
	final PopupDescription popupDesc = new PopupDescription(new RegistrationValidationMessages());
	FocusAction focusAction = new FocusAction();
	final VerticalPanel vPanel = new VerticalPanel();
	final Button sendButton = new Button("Send");
	final TextBox nameField = new TextBox();
	final TextBox surName = new TextBox();
	final Label errorLabel = new Label();

	public HelloView() {

		nameField.setText("GWT User");
		errorLabel.setStyleName("serverResponseLabelError");
		
		validation.addValidators("username", new IntegerValidator(nameField, "userNameHelp")
				.addActionForFailure(new StyleAction("validationFailedBorder")).addActionForFailure(
						new LabelTextAction(errorLabel)).addActionForFailure(focusAction));

		popupDesc.addDescription("userNameHelp", nameField);

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		vPanel.add(errorLabel);
		vPanel.add(nameField);
		vPanel.add(surName);
		vPanel.add(sendButton);
		

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		super.initWidget(vPanel);
	}

	public Widget asWidget() {
		return this;
	}

	/**
	 * Get view validator
	 */
	public ValidationProcessor getValidator() {
		return validation;
	}

	public HasClickHandlers getSendButton() {
		return sendButton;
	}

	public HasText getTextBox() {
		return nameField;
	}

}
