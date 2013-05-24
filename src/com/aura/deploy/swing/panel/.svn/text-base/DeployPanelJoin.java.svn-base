package com.aura.deploy.swing.panel;

import java.awt.GridBagConstraints;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.aura.base.manager.configuration.ConfigCM;
import com.aura.deploy.DeployWindowClient;
import com.aura.deploy.swing.AbstractDeployPanel;
import com.aura.deploy.swing.validation.Validator;
import com.aura.deploy.swing.validation.ValidatorTextField;

public class DeployPanelJoin extends AbstractDeployPanel {
	private static final long serialVersionUID = 1L;

	public DeployPanelJoin(DeployWindowClient win) {
		super(win, TypeDeployPanel.JOIN, true);

		final List<Validator<?,?>> groupValidator = new ArrayList<Validator<?,?>>();
		addValidator(new ValidatorTextField(getTfHostname(), getBtDo(), groupValidator) {
			@Override
			public void implValider(JTextField trigger) throws Exception {
				if (trigger.getText() == null || trigger.getText().trim().length() == 0)
					throw new Exception("Hostname error.");
			}
		});
		addValidator(new ValidatorTextField(getTfPort(), getBtDo(), groupValidator) {
			@Override
			public void implValider(JTextField trigger) throws Exception {
				if (trigger.getText() == null 
						|| trigger.getText().trim().length() == 0)
					throw new Exception("Port error.");
				Integer.parseInt(trigger.getText());
			}
		});
		addValidator(new ValidatorTextField(getTfPassword(), getBtDo(), groupValidator) {
			@Override
			public void implValider(JTextField trigger) throws Exception {
				if (trigger.getText() != null && trigger.getText().trim().length() > 20)
					throw new Exception("Password error.");
			}
		});
	}
	
	@Override
	public void doActionM2v(JPanel pAction, JButton btDo, JButton btUndo) {
		btDo.setText("Connect & Play !");
		btDo.setVisible(true);
		btUndo.setVisible(false);
	}
	
	@Override
	public void doInitialize() {
		add(getPBordered(), createGbc(0, 0, GridBagConstraints.HORIZONTAL, 0, 0, 2));
	}
	
	@Override
	public void m2v() {
		getTfHostname().setText(getWindow().getClientCfg(ConfigCM.SOCKET_HOSTNAME).getValueToString());
		getTfPort().setText(""+getWindow().getClientCfg(ConfigCM.SOCKET_PORT).getValueToInteger());
		getTfPassword().setText(null);
	}
	
	@Override
	public void v2m() {
		try {
			getWindow().getClient().getNetworkManager().connect(getTfPassword().getText().trim());
		} catch (ConnectException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override public void cancel() {}
	
	private JPanel pBordered;
	public JPanel getPBordered() {
		if (pBordered == null) {
			pBordered = createBorderedPanel("");
			
			pBordered.add(getLHostname(), createGbc(0, 0, GridBagConstraints.NONE));
			pBordered.add(getTfHostname(), createGbc(1, 0, GridBagConstraints.HORIZONTAL));
			
			pBordered.add(getLPort(), createGbc(0, 1, GridBagConstraints.NONE));
			pBordered.add(getTfPort(), createGbc(1, 1, GridBagConstraints.HORIZONTAL));
			
			pBordered.add(getLPassword(), createGbc(0, 2, GridBagConstraints.NONE));
			pBordered.add(getTfPassword(), createGbc(1, 2, GridBagConstraints.HORIZONTAL));
		}
		return pBordered;
	}
	
	private JLabel lHostname;
	public JLabel getLHostname() {
		if (lHostname == null) {
			lHostname = new JLabel("Hostname");
		}
		return lHostname;
	}
	
	private JTextField tfHostname;
	public JTextField getTfHostname() {
		if (tfHostname == null) {
			tfHostname = new JTextField();
		}
		return tfHostname;
	}
	
	private JLabel lPort;
	public JLabel getLPort() {
		if (lPort == null) {
			lPort = new JLabel("Port");
		}
		return lPort;
	}
	
	private JTextField tfPort;
	public JTextField getTfPort() {
		if (tfPort == null) {
			tfPort = new JTextField();
		}
		return tfPort;
	}
	
	private JLabel lPassword;
	public JLabel getLPassword() {
		if (lPassword == null) {
			lPassword = new JLabel("Password");
		}
		return lPassword;
	}
	
	private JTextField tfPassword;
	public JTextField getTfPassword() {
		if (tfPassword == null) {
			tfPassword = new JTextField();
		}
		return tfPassword;
	}
}