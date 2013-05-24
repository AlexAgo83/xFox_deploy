package com.aura.deploy.swing.views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.aura.deploy.DeployWindowClient;
import com.aura.deploy.swing.AbstractDeployPanel;
import com.aura.deploy.swing.panel.TypeDeployPanel;

public class MenuView extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private final DeployWindowClient win;
	public DeployWindowClient getWindow() {
		return win;
	}
	
	public MenuView(DeployWindowClient win) {
		super(new GridBagLayout());
		this.win = win;
		initialize();
	}
	
	private void initialize() {
		this.groupButtons = new ArrayList<DeployButton>();

		add(getBtCreateGame(), AbstractDeployPanel.createGbc(0, 0, GridBagConstraints.HORIZONTAL));
		add(getBtJoinGame(), AbstractDeployPanel.createGbc(1, 0, GridBagConstraints.HORIZONTAL));
		add(getBtOption(), AbstractDeployPanel.createGbc(2, 0, GridBagConstraints.HORIZONTAL));
		add(getBtExit(), AbstractDeployPanel.createGbc(3, 0, GridBagConstraints.HORIZONTAL));
		
		onClick(null);
	}
	
	protected void onClick(TypeDeployPanel type) {
		if (type == null) {
			resetButton(getBtCreateGame());
			resetButton(getBtJoinGame());
			resetButton(getBtOption());
			resetButton(getBtExit());
			return;
		}
		switch (type) {
			case CREATE: 
				getWindow().show(type);
				resetButton(getBtCreateGame());
				break;
			case JOIN: 
				getWindow().show(type);
				resetButton(getBtJoinGame());
				break;
			case OPTION: 
				getWindow().show(type);
				resetButton(getBtOption());
				break;
		}
	}
	
	private List<DeployButton> groupButtons;
	public void resetButton(DeployButton bt) {
		for (DeployButton b: groupButtons)
			b.setContentAreaFilled(true);
		bt.setContentAreaFilled(false);
	}
	
	private DeployButton btCreateGame;
	public DeployButton getBtCreateGame() {
		if (btCreateGame == null) {
			btCreateGame = new DeployButton(getWindow(), "Create game");
			btCreateGame.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					onClick(TypeDeployPanel.CREATE);
				}
			});
			groupButtons.add(btCreateGame);
		}
		return btCreateGame;
	}
	
	private DeployButton btJoinGame;
	public DeployButton getBtJoinGame() {
		if (btJoinGame == null) {
			btJoinGame = new DeployButton(getWindow(), "Join game");
			btJoinGame.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					onClick(TypeDeployPanel.JOIN);
				}
			});
			groupButtons.add(btJoinGame);
		}
		return btJoinGame;
	}
	
	private DeployButton btOption;
	public DeployButton getBtOption() {
		if (btOption == null) {
			btOption = new DeployButton(getWindow(), "Option");
			btOption.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					onClick(TypeDeployPanel.OPTION);
				}
			});
			groupButtons.add(btOption);
		}
		return btOption;
	}
	
	private DeployButton btExit;
	public DeployButton getBtExit() {
		if (btExit == null) {
			btExit = new DeployButton(getWindow(), "Exit");
			btExit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					resetButton(getBtExit());
					getWindow().close(true);
				}
			});
			groupButtons.add(btExit);
		}
		return btExit;
	}
}