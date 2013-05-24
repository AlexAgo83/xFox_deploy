package com.aura.deploy.swing.validation;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComponent;

public abstract class ValidatorCheckBox extends Validator<JCheckBox, JComponent> {
	public ValidatorCheckBox(JCheckBox trigger, JComponent activator, List<Validator<?,?>> group) {
		super(trigger, activator, group);
		if (group != null)
			group.add(this);
	}
	public ValidatorCheckBox(JCheckBox trigger, JComponent activator) {
		this(trigger, activator, null);
	}
	@Override
	public void attachListener(JCheckBox c) {
		c.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				valider();
			}
		});
	}
	@Override
	public void implOnError(JComponent activator, boolean isError) {
		activator.setEnabled(!isError);
	}
}