package com.aura.deploy.swing.views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.aura.deploy.DeployWindowClient;
import com.aura.deploy.swing.AbstractDeployPanel;

@SuppressWarnings("serial")
public abstract class ActionView extends JPanel {
	
	private final DeployWindowClient window;
	public DeployWindowClient getWindow() {
		return window;
	}
	
	public ActionView(DeployWindowClient window) {
		super(new GridBagLayout());
		this.window = window;
		initialize();
	}
	
	private void initialize() {
		this.add(getBtDo(), AbstractDeployPanel.createGbc(0, 0, GridBagConstraints.HORIZONTAL));
		this.add(getBtUndo(), AbstractDeployPanel.createGbc(1, 0, GridBagConstraints.HORIZONTAL));
	}
	
	private DeployButton btDo;
	public DeployButton getBtDo() {
		if (btDo == null) {
			btDo = new DeployButton(getWindow(), "");
			btDo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					onClickDo();
				}
			});
		}
		return btDo;
	}
	public abstract void onClickDo();
	
	private DeployButton btUndo;
	public DeployButton getBtUndo() {
		if (btUndo == null) {
			btUndo = new DeployButton(getWindow(), "");
			btUndo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					onClickUndo();
				}
			});
		}
		return btUndo;
	}
	public abstract void onClickUndo();
}