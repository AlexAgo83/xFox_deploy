package com.aura.deploy.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.aura.deploy.DeployWindowClient;
import com.aura.deploy.swing.panel.TypeDeployPanel;
import com.aura.deploy.swing.validation.Validator;

public abstract class AbstractDeployPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private final DeployWindowClient window;
	private final TypeDeployPanel type;
	private final List<Validator<?,?>> validators;
	private final boolean avecBufferFooter;
	
	public AbstractDeployPanel(final DeployWindowClient window, final TypeDeployPanel type) {
		this(window, type, false);
	}
	public AbstractDeployPanel(final DeployWindowClient window, final TypeDeployPanel type, boolean isAvecBufferFooter) {
		super(new GridBagLayout());
		
		this.window = window;
		this.type = type;
		this.avecBufferFooter = isAvecBufferFooter;
		
		this.validators = new ArrayList<Validator<?,?>>();
		
		initialize();
		getWindow().attach(this);
	}
	
	private void initialize() {
		setOpaque(false);
		if (avecBufferFooter) {
			add(new JLabel(""), createGbc(0, 99, GridBagConstraints.BOTH, 0, 0, 0));
		}
		doInitialize();
	}
	public abstract void doInitialize();
	
	public TypeDeployPanel getType() {
		return type;
	}
	
	public void addValidator(Validator<?, ?> validator) {
		this.validators.add(validator);
	}
	
	public DeployWindowClient getWindow() {
		return window;
	}
	
	public static JPanel createBorderedPanel(String name) {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setOpaque(false);
		panel.setBorder(new TitledBorder(LineBorder.createGrayLineBorder(), name));
		return panel;
	}
	
	public static GridBagConstraints createGbc(int x, int y, int fill) {
		return createGbc(x, y, fill, 0, 0);
	}
	
	public static GridBagConstraints createGbc(int x, int y, int fill, int gridwidth, int gridheight) {
		return createGbc(x, y, fill, gridwidth, gridheight, 2);
	}
	
	public static GridBagConstraints createGbc(int x, int y, int fill, int gridwidth, int gridheight, int inset) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x; gbc.gridy = y;
		gbc.fill = fill;
		
		if (fill == GridBagConstraints.HORIZONTAL || fill == GridBagConstraints.BOTH)
			gbc.weightx = 1;
		if (fill == GridBagConstraints.VERTICAL || fill == GridBagConstraints.BOTH)
			gbc.weighty = 1;
		if (fill == GridBagConstraints.NONE) {
			gbc.weightx = 0;
			gbc.weighty = 0;
		}
		
		gbc.insets = new Insets(inset, inset, inset, inset);
		gbc.anchor = GridBagConstraints.WEST;
		
		if (gridwidth > 0)
			gbc.gridwidth = gridwidth;
		if (gridheight > 0)
			gbc.gridheight = gridheight;
		
		return gbc;
	}

	public JButton getBtDo() {
		return getWindow().getPanelAction().getBtDo(); 
	}
	public JButton getBtUndo() {
		return getWindow().getPanelAction().getBtUndo(); 
	}
	
	public abstract void doActionM2v(JPanel pAction, JButton btDo, JButton btUndo);
	public void doM2v() {
		doActionM2v(
			getWindow().getPanelAction(),
			getWindow().getPanelAction().getBtDo(), 
			getWindow().getPanelAction().getBtUndo());
		m2v();
		for (Validator<?,?> v: validators)
			v.valider();
	}
	public abstract void m2v();
	public abstract void v2m();
	public abstract void cancel();
}