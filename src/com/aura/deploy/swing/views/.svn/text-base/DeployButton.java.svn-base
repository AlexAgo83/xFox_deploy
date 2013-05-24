package com.aura.deploy.swing.views;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.aura.deploy.engine.DeployGUI;

@SuppressWarnings("serial")
public class DeployButton extends JButton {
	public DeployButton(DeployGUI gui, String text) {
		this(gui, text, 0);	
	}
	public DeployButton(DeployGUI gui, String text, int img) {
		super(text);		
		if (gui.getImage(img) != null)
			setIcon(new ImageIcon(gui.getImage(img)));
	}
}
