package com.aura.deploy.swing.validation;

import java.awt.Color;
import java.util.List;

import javax.swing.JComponent;

public abstract class Validator<E extends JComponent, F extends JComponent> {
	private final E trigger;
	private final Color colorBg;
	
	private final F activator;

	private final List<Validator<?, ?>> group;
	
	public Validator(E trigger, F activator, List<Validator<?, ?>> group) {
		this.trigger = trigger;
		this.colorBg = trigger.getBackground();
		this.activator = activator;
		this.group = group;
		attachListener(trigger);
	}
	public Validator(E trigger, F activator) {
		this(trigger, activator, null);
	}
	
	public E getTrigger() {
		return trigger;
	}
	public F getActivator() {
		return activator;
	}
	public List<Validator<?, ?>> getGroup() {
		return group;
	}
	
	public String valider() {
		String log = getError();
		if (log != null) {
			trigger.setBackground(Color.PINK);
			trigger.setToolTipText(log);
		} else {
			trigger.setBackground(colorBg);
			trigger.setToolTipText(null);
		}
		if (log == null && group != null) {
			for (Validator<?, ?> v: group) {
				if (!v.equals(this)) {
					String temp = v.getError();
					if (temp != null) {
						log = temp;
						break;
					}
				}
			}
		}
		implOnError(activator, log != null);
		return log;
	}
	
	private String getError() {
		try {
			implValider(trigger);
		} catch (Exception e) {
			return e.getMessage();
		}
		return null;
	}
	
	public abstract void attachListener(E trigger);
	public abstract void implValider(E trigger) throws Exception;
	public abstract void implOnError(F activator, boolean isError);
}