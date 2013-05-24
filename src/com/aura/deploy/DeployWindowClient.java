package com.aura.deploy;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.aura.base.event.AbstractEvent;
import com.aura.base.event.AuraEventListener;
import com.aura.deploy.engine.DeployGUI;
import com.aura.deploy.engine.image.FrameImageCM;
import com.aura.deploy.swing.AbstractDeployPanel;
import com.aura.deploy.swing.panel.DeployPanelCreate;
import com.aura.deploy.swing.panel.DeployPanelJoin;
import com.aura.deploy.swing.panel.DeployPanelOption;
import com.aura.deploy.swing.panel.TypeDeployPanel;
import com.aura.deploy.swing.views.ActionView;
import com.aura.deploy.swing.views.MenuView;
import com.aura.engine.event.EPEventCM;

public class DeployWindowClient extends DeployGUI implements WindowListener {
	private final Dimension defautDim;	
	
	public static void main(String[] args) {
		new DeployWindowClient(null);
	}
	
	private Component component;
	public Component getComponent() {
		return component;
	}
	
	private Map<TypeDeployPanel, AbstractDeployPanel> panels;
	public AbstractDeployPanel getPanel(TypeDeployPanel type) {
		return panels.get(type);
	}
	public void attach(AbstractDeployPanel panel) {
		panels.put(panel.getType(), panel);
	}
	
	public DeployWindowClient(Component parentFrame) {
		this.defautDim = new Dimension(400, 310);
		this.panels = new HashMap<TypeDeployPanel, AbstractDeployPanel>();
		
		initLookAndFeel();
		initClient(); // Init du client
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		initWindow(parentFrame); // Init du launcher
		
		getPanelMenu().setVisible(true);
		show(null);
	}
	
	private void initWindow(Component parentFrame) {
		// Frame
		if (parentFrame == null) {
			final JFrame frame = new JFrame("xFox");
			frame.setIconImage(getImage(FrameImageCM.FRAME_ICON));
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.getContentPane().add(getPContent());
			frame.getContentPane().setBackground(Color.BLACK);
			component = frame;
		} else { 
			component = parentFrame;
			if (parentFrame instanceof Window) {
				Window w = (Window) parentFrame;
				w.add(getPContent());
			} else if (parentFrame instanceof Canvas) {
				Canvas c = (Canvas) parentFrame;
				c.getParent().add(getPContent());
			} else if (parentFrame instanceof Container) {
				Container c = (Container) parentFrame;
				if (parentFrame instanceof JPanel) {
					GridBagConstraints gbc = new GridBagConstraints();
					gbc.weightx = 1; gbc.weighty = 1;
					gbc.fill = GridBagConstraints.BOTH;
					c.add(getPContent(), gbc);
				} else
					c.add(getPContent());
			}
		}
		
		// Compléments
		if (component instanceof JFrame) {
			JFrame window = (JFrame) component;
			window.setSize(defautDim);
			window.setResizable(false);
			window.addWindowListener(this);
			window.setLocationRelativeTo(null);
		} else if (component instanceof JPanel) {
			Container panel = (Container) component;
			for (int i=0; i<100; i++) {
				panel = panel.getParent();
				boolean lFound = panel instanceof Window;
				if (lFound)
					break;
			}
			Window window = (Window) panel;
			window.setSize(defautDim);
			window.addWindowListener(this);
			window.setLocationRelativeTo(null);
		}
		
		// LANCEMENT DE L'ENGINE
		// FIXME XF(AA) déplacer tout ca dans un splash screen ? (attente de réponse serveur + chargement engine)
		getClient().getEventManager().getDispatchThread().addListener(
			new AuraEventListener(
				getClient().getEvent(EPEventCM.TOKEN_GRANTED)) {
			@Override
			protected void implExecute(AbstractEvent event) {
				getComponent().setVisible(false);
				getClient().getEventManager().addEvent(getClient().createEvent(EPEventCM.LAUNCH_ENGINE));
			}
		});
		
		component.setVisible(true);
	}
	
	private JPanel pContent;
	public JPanel getPContent() {
		if (pContent == null) {
			pContent = new JPanel(new GridBagLayout());
			pContent.setOpaque(true);
			pContent.setBackground(Color.WHITE);
			
			pContent.add(getPanelMenu(), AbstractDeployPanel.createGbc(0, 0, GridBagConstraints.HORIZONTAL, 0, 0, 0));
			pContent.add(getSpDetail(), AbstractDeployPanel.createGbc(0, 1, GridBagConstraints.BOTH, 0, 0, 0));
			pContent.add(getPanelAction(), AbstractDeployPanel.createGbc(0, 2, GridBagConstraints.HORIZONTAL, 0, 0, 0));
		}
		return pContent;
	}
	
	private MenuView panelMenu;
	public MenuView getPanelMenu() {
		if (panelMenu == null) {
			panelMenu = new MenuView(this);
			panelMenu.setVisible(false);
		}
		return panelMenu;
	}
	
	private JScrollPane spDetail;
	public JScrollPane getSpDetail() {
		if (spDetail == null) {
			spDetail = new JScrollPane();
			spDetail.setViewportView(getPDetail());
		}
		return spDetail;
	}
	
	private JPanel panelDetail;
	public JPanel getPDetail() {
		if (panelDetail == null) {
			panelDetail = new JPanel(new GridBagLayout());
			panelDetail.setOpaque(true);
			panelDetail.setBackground(Color.WHITE);
			
			panelDetail.add(new DeployPanelCreate(this), AbstractDeployPanel.createGbc(0, 1, GridBagConstraints.HORIZONTAL));
			panelDetail.add(new DeployPanelJoin(this), AbstractDeployPanel.createGbc(0, 2, GridBagConstraints.HORIZONTAL));
			panelDetail.add(new DeployPanelOption(this), AbstractDeployPanel.createGbc(0, 3, GridBagConstraints.HORIZONTAL));
		}
		return panelDetail;
	}
	
	private ActionView panelAction;
	public ActionView getPanelAction() {
		if (panelAction == null) {
			panelAction = new ActionView(this) {
				private static final long serialVersionUID = 1L;
				@Override
				public void onClickDo() {
					if (focusedType != null)
						getPanel(focusedType).v2m();
				}
				@Override
				public void onClickUndo() {
					if (focusedType != null)
						getPanel(focusedType).cancel();
				}
			};
			panelAction.setVisible(false);
		}
		return panelAction;
	}
	
	private TypeDeployPanel focusedType;
	public TypeDeployPanel getFocusedType() {
		return focusedType;
	}
	
	public void show(TypeDeployPanel type) {
		this.focusedType = type;
		
		for (TypeDeployPanel t: TypeDeployPanel.values()) {
			AbstractDeployPanel focus = getPanel(t);
			if (t == type) {
				focus.setVisible(true);
				focus.doM2v();
			} else {
				focus.setVisible(false);
			}
		}
		getPanelAction().setVisible(type != null);
	}
	
	public void close(boolean force) {
		if (getServer() != null && getServer().isRunning())
			getServer().turnOff(false);
		if (getClient() != null && getClient().isRunning())
			getClient().turnOff(false);
		if (getComponent() != null)
			getComponent().setVisible(false);
		if (force)
			System.exit(0);
	}
	
	@Override 
	public void windowClosing(WindowEvent e) {
		close(false);
	}
	
	@Override public void windowActivated(WindowEvent e) {}
	@Override public void windowClosed(WindowEvent e) {}
	@Override public void windowDeactivated(WindowEvent e) {}
	@Override public void windowDeiconified(WindowEvent e) {}
	@Override public void windowIconified(WindowEvent e) {}
	@Override public void windowOpened(WindowEvent e) {}
}