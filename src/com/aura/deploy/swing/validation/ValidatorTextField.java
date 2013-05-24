package com.aura.deploy.swing.validation;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public abstract class ValidatorTextField extends Validator<JTextField, JComponent> {
	public ValidatorTextField(JTextField trigger, JComponent activator, List<Validator<?,?>> group) {
		super(trigger, activator, group);
		if (group != null)
			group.add(this);
	}
	public ValidatorTextField(JTextField trigger, JComponent activator) {
		this(trigger, activator, null);
	}
	@Override
	public void attachListener(JTextField c) {
		c.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				valider();
			}
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				valider();
			}
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				valider();
			}
		});
	}
	@Override
	public void implOnError(JComponent activator, boolean isError) {
		activator.setEnabled(!isError);
	}
}