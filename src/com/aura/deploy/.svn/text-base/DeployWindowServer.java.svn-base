package com.aura.deploy;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.aura.base.Aura;
import com.aura.chat.plugin.ChatServerPlugin;
import com.aura.engine.plugin.EngineServerPlugin;
import com.aura.exemple.server.ExempleServerMonitorView;
import com.aura.server.AuraServer;

// FIXME XF(AA) [Launcher] à refaire en mieux
public class DeployWindowServer {
	public static void main(String[] args) {
		new DeployWindowServer();
	}
	
	public DeployWindowServer() {
		final JFrame frame = new JFrame();
		final JPanel panelMenu = new JPanel(new GridBagLayout());
		frame.setContentPane(panelMenu);
		frame.setSize(new Dimension(300, 200));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		final JPanel pContext = new JPanel(new GridBagLayout());
		
		frame.setVisible(true);
		
		frame.setContentPane(pContext);
		init(pContext);
	}
	
	private void init(JPanel container) {
		final AuraServer selfServer = new AuraServer() {
			@Override
			public void initPlugin(final Aura self) {
				addPlugin(new EngineServerPlugin(this));
				addPlugin(new ChatServerPlugin(this));
			}
		};

		final JPanel panel = container != null ? container : new JPanel(new GridBagLayout());
		if (container == null) {
			JFrame frame = new JFrame();
			frame.setContentPane(panel);
			new ExempleServerMonitorView(selfServer, panel);
			frame.setVisible(true);
		} else {
			new ExempleServerMonitorView(selfServer, panel);
		}
	}
}