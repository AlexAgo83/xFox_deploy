package com.aura.deploy.swing.panel;

import java.awt.GridBagConstraints;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.aura.base.manager.configuration.ConfigCM;
import com.aura.deploy.DeployWindowClient;
import com.aura.deploy.swing.AbstractDeployPanel;
import com.aura.deploy.swing.validation.Validator;
import com.aura.deploy.swing.validation.ValidatorTextField;
import com.aura.engine.configuration.EPConfigCM;

public class DeployPanelCreate extends AbstractDeployPanel {
	private static final long serialVersionUID = 1L;

	private ValidatorTextField validatorPassword;
	
	public DeployPanelCreate(DeployWindowClient win) {
		super(win, TypeDeployPanel.CREATE, true);
		
		final List<Validator<?,?>> groupValidator = new ArrayList<Validator<?,?>>();
		validatorPassword = new ValidatorTextField(getTfPassword(), getBtDo(), groupValidator) {
			@Override
			public void implValider(JTextField trigger) throws Exception {
				if (getCkPassword().isSelected()) {
					if (trigger.getText() == null || trigger.getText().trim().length() == 0)
						throw new Exception("Password error.");
					if (trigger.getText().trim().length() < 3)
						throw new Exception("Password length error.");
					if (trigger.getText().trim().length() > 20)
						throw new Exception("Password length error.");
				}
			}
		};
		addValidator(validatorPassword);
		
		addValidator(new ValidatorTextField(getTfDefaultSeed(), getBtDo(), groupValidator) {
			@Override
			public void implValider(JTextField trigger) throws Exception {
				if (trigger.getText() == null || trigger.getText().trim().length() == 0)
					throw new Exception("Default seed error.");
				Integer.parseInt(trigger.getText());
			}
		});
		
		addValidator(new ValidatorTextField(getTfDefaultWidth(), getBtDo(), groupValidator) {
			@Override
			public void implValider(JTextField trigger) throws Exception {
				try {
					if (trigger.getText() == null || trigger.getText().trim().length() == 0)
						throw new Exception("Default width error.");
					Integer.parseInt(trigger.getText());
				} catch (Exception e) {
					clearTargetSize();
					throw e;
				}
				refreshTargetSize();
			}
		});
		
		addValidator(new ValidatorTextField(getTfDefaultHeight(), getBtDo(), groupValidator) {
			@Override
			public void implValider(JTextField trigger) throws Exception {
				try {
					if (trigger.getText() == null || trigger.getText().trim().length() == 0)
						throw new Exception("Default height error.");
					Integer.parseInt(trigger.getText());
				} catch (Exception e) {
					clearTargetSize();
					throw e;
				}
				refreshTargetSize();
			}
		});
	}
	
	@Override
	public void doActionM2v(JPanel pAction, JButton btDo, JButton btUndo) {
		btDo.setText("Play !");
		btDo.setVisible(true);
		btUndo.setVisible(false);
	}
	
	@Override
	public void doInitialize() {
		add(getPBordered(), createGbc(0, 0, GridBagConstraints.HORIZONTAL, 0, 0, 2));
	}
	
	@Override
	public void m2v() {
		getTfPassword().setText(null);
		getCkPassword().setSelected(false);
		
		getTfDefaultSeed().setText("1");
		getTfDefaultWidth().setText("10000");
		getTfDefaultHeight().setText("10000");
	}
	
	@Override
	public void v2m() {
		getWindow().initServer();
		
		// Align le client sur le mm host/port que le serveur.
		getWindow().setClientCfg(ConfigCM.SOCKET_HOSTNAME, "localhost");
		getWindow().setClientCfg(
			ConfigCM.SOCKET_PORT, 
			getWindow().getServer().getCfgManager().getConfigIntegerValue(ConfigCM.SOCKET_PORT)
		);

		// Enregistrement du "Default" seed, width et height.
		Integer defaultSeed = Integer.parseInt(getTfDefaultSeed().getText());
		Integer defaultWidth = Integer.parseInt(getTfDefaultWidth().getText());
		Integer defaultHeight = Integer.parseInt(getTfDefaultHeight().getText());
		
		getWindow().getServer().getCfgManager().setConfigIntegerValue(EPConfigCM.WORLD_DEFAULT_SEED, defaultSeed, true);
		getWindow().getServer().getCfgManager().setConfigIntegerValue(EPConfigCM.WORLD_DEFAULT_WIDTH, defaultWidth, true);
		getWindow().getServer().getCfgManager().setConfigIntegerValue(EPConfigCM.WORLD_DEFAULT_HEIGHT, defaultHeight, true);
		
		// Affect le password
		String password = getCkPassword().isSelected() ? getTfPassword().getText().trim() : "";
		getWindow().getServer().getCfgManager().setConfigStringValue(ConfigCM.SOCKET_PASSWORD, password, true);
		getWindow().getServer().getNetworkManager().online();
		getWindow().getPluginEngine().setSelfServer(getWindow().getServer());
		
		try {
			getWindow().getClient().getNetworkManager().connect(password);
		} catch (ConnectException e1) {
			e1.printStackTrace();
		}		
	}
	
	@Override public void cancel() {}
	
	private JPanel pBordered;
	public JPanel getPBordered() {
		if (pBordered == null) {
			pBordered = createBorderedPanel("");
			
			pBordered.add(getLPassword(), createGbc(0,0, GridBagConstraints.NONE));
			pBordered.add(getCkPassword(), createGbc(1,0, GridBagConstraints.NONE));
			pBordered.add(getTfPassword(), createGbc(2,0, GridBagConstraints.HORIZONTAL, 2, 1));
			
			pBordered.add(getLDefaultSeed(), createGbc(0,1, GridBagConstraints.NONE));
			pBordered.add(getTfDefaultSeed(), createGbc(1,1, GridBagConstraints.HORIZONTAL, 3, 1));
			
			pBordered.add(getLDefaultSize(), createGbc(0,2, GridBagConstraints.NONE));
			pBordered.add(getTfDefaultWidth(), createGbc(1,2, GridBagConstraints.HORIZONTAL, 2, 1));
			pBordered.add(getTfDefaultHeight(), createGbc(3,2, GridBagConstraints.HORIZONTAL));
			pBordered.add(getLTargetSize(), createGbc(1,3, GridBagConstraints.HORIZONTAL, 3, 1));
		}
		return pBordered;
	}
	
	private JLabel lPassword;
	public JLabel getLPassword() {
		if (lPassword == null) {
			lPassword = new JLabel("Password");
		}
		return lPassword;
	}
	
	private JCheckBox ckPassword;
	public JCheckBox getCkPassword() {
		if (ckPassword == null) {
			ckPassword = new JCheckBox();
			ckPassword.setOpaque(false);
			ckPassword.setSelected(false);
			ckPassword.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent arg0) {
					getTfPassword().setEditable(getCkPassword().isSelected());
					getTfPassword().setText("");
					validatorPassword.valider();
				}
			});
		}
		return ckPassword;
	}
	
	private JTextField tfPassword;
	public JTextField getTfPassword() {
		if (tfPassword == null) {
			tfPassword = new JTextField();
			tfPassword.setEditable(false);
		}
		return tfPassword;
	}
	
	private JLabel lDefaultSeed;
	public JLabel getLDefaultSeed() {
		if (lDefaultSeed == null) {
			lDefaultSeed = new JLabel("Default Seed");
		}
		return lDefaultSeed;
	}
	
	private JTextField tfDefaultSeed;
	public JTextField getTfDefaultSeed() {
		if (tfDefaultSeed == null) {
			tfDefaultSeed = new JTextField();
		}
		return tfDefaultSeed;
	}
	
	private JLabel lDefaultSize;
	public JLabel getLDefaultSize() {
		if (lDefaultSize == null) {
			lDefaultSize = new JLabel("Default Size");
		}
		return lDefaultSize;
	}
	
	private JTextField tfDefaultWidth;
	public JTextField getTfDefaultWidth() {
		if (tfDefaultWidth == null) {
			tfDefaultWidth = new JTextField();
		}
		return tfDefaultWidth;
	}
	
	private JTextField tfDefaultHeight;
	public JTextField getTfDefaultHeight() {
		if (tfDefaultHeight == null) {
			tfDefaultHeight = new JTextField();
		}
		return tfDefaultHeight;
	}
	
	private JLabel lTargetSize;
	public JLabel getLTargetSize() {
		if (lTargetSize == null) {
			lTargetSize = new JLabel();
		}
		return lTargetSize;
	}
	private void clearTargetSize() {
		getLTargetSize().setText("");
	}
	private void refreshTargetSize() {
		int w = Integer.parseInt(getTfDefaultWidth().getText());
		int h = Integer.parseInt(getTfDefaultHeight().getText());
		long value = w*h;
		String valueString = "";
		if (value > 1000 * 1000 * 1000) {
			valueString = (long) Math.round(value / 1000 / 1000 / 1000)  + " P";	
		} else if (value > 1000 * 1000) {
			valueString = (long) Math.round(value / 1000 / 1000)  + " T";	
		} else if (value > 1000) {
			valueString = (long) Math.round(value / 1000) + " K";	
		} else {
			valueString = value + " ";
		}
		valueString += "u²";
		getLTargetSize().setText("Unit (w/h): " + valueString + " (" + w + "/" + h + ")");
	}
}