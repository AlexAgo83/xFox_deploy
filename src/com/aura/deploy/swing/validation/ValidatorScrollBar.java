package com.aura.deploy.swing.validation;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JScrollBar;

public abstract class ValidatorScrollBar extends Validator<JScrollBar, JComponent> {
	public ValidatorScrollBar(JScrollBar trigger, JComponent activator, List<Validator<?,?>> group) {
		super(trigger, activator, group);
		if (group != null)
			group.add(this);
	}
	public ValidatorScrollBar(JScrollBar trigger, JComponent activator) {
		this(trigger, activator, null);
	}
	@Override
	public void attachListener(JScrollBar c) {
		c.addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent arg0) {
				valider();
			}
		});
	}
	@Override
	public void implOnError(JComponent activator, boolean isError) {
		activator.setEnabled(!isError);
	}
}