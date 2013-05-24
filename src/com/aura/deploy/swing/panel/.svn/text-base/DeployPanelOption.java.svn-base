package com.aura.deploy.swing.panel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

import com.aura.base.container.AbstractContainerManager.KeyContainerAlreadyUsed;
import com.aura.base.manager.configuration.ConfigCM;
import com.aura.deploy.DeployWindowClient;
import com.aura.deploy.swing.AbstractDeployPanel;
import com.aura.deploy.swing.validation.Validator;
import com.aura.deploy.swing.validation.ValidatorScrollBar;
import com.aura.deploy.swing.validation.ValidatorTextField;
import com.aura.deploy.swing.views.ColorSelectionView;
import com.aura.deploy.swing.views.ColorSelectionView.ColorSelection;
import com.aura.deploy.swing.views.DisplayModeSelectionView;
import com.aura.deploy.swing.views.DisplayModeSelectionView.DisplayModeSelection;
import com.aura.deploy.swing.views.ScrollView;
import com.aura.engine.configuration.EPConfigCM;
import com.aura.engine.univers.texture.SpriteSurfaceQuality;

public class DeployPanelOption extends AbstractDeployPanel {
	private static final long serialVersionUID = 1L;

	public DeployPanelOption(DeployWindowClient win) {
		super(win, TypeDeployPanel.OPTION, true);
		
		final List<Validator<?,?>> groupValidator = new ArrayList<Validator<?,?>>();
		addValidator(new ValidatorTextField(getTfUsername(), getBtDo(), groupValidator) {
			@Override
			public void implValider(JTextField trigger) throws Exception {
				if (trigger.getText() == null || trigger.getText().trim().length() == 0)
					throw new Exception("Username error.");
				if (trigger.getText().trim().length() < 3)
					throw new Exception("Username length error.");
				if (trigger.getText().trim().length() > 20)
					throw new Exception("Username length error.");
			}
		});
		
		addValidator(new ValidatorTextField(getTfRate(), getBtDo(), groupValidator) {
			@Override
			public void implValider(JTextField trigger) throws Exception {
				if (trigger.getText() == null || trigger.getText().trim().length() == 0)
					throw new Exception("Rate error.");
				Integer.parseInt(trigger.getText());
			}
		});
		
		addValidator(new ValidatorScrollBar(getSvUiColorRed().getBar(), getBtDo(), groupValidator) {
			@Override
			public void implValider(JScrollBar trigger) throws Exception {
				refreshPaintUiColor();
				if (trigger.getValue() < 0 || trigger.getValue() > 255)
					throw new Exception("RED value error, Ex: [0 - 255]");
			}
		});
		
		addValidator(new ValidatorScrollBar(getSvUiColorGreen().getBar(), getBtDo(), groupValidator) {
			@Override
			public void implValider(JScrollBar trigger) throws Exception {
				refreshPaintUiColor();
				if (trigger.getValue() < 0 || trigger.getValue() > 255)
					throw new Exception("GREEN value error, Ex: [0 - 255]");
			}
		});
		
		addValidator(new ValidatorScrollBar(getSvUiColorBlue().getBar(), getBtDo(), groupValidator) {
			@Override
			public void implValider(JScrollBar trigger) throws Exception {
				refreshPaintUiColor();
				if (trigger.getValue() < 0 || trigger.getValue() > 255)
					throw new Exception("BLUE value error, Ex: [0 - 255]");
			}
		});
	}
	
	@Override
	public void doActionM2v(JPanel pAction, JButton btDo, JButton btUndo) {
		btDo.setText("Save"); btDo.setVisible(true);
		btUndo.setText("Cancel"); btUndo.setVisible(true);
	}
	
	@Override
	public void doInitialize() {
		add(getPBorderedGeneral(), createGbc(0, 0, GridBagConstraints.HORIZONTAL, 0, 0, 2));
		add(getPBorderedDisplay(), createGbc(0, 1, GridBagConstraints.HORIZONTAL, 0, 0, 2));
		add(getPBorderedUI(), createGbc(0, 2, GridBagConstraints.HORIZONTAL, 0, 0, 2));
	}
	
	@Override
	public void m2v() {
		getTfUsername().setText(getWindow().getClientCfg(ConfigCM.SOCKET_LOGIN).getValueToString());
		getDisplayModeView().setSelectedItem(DisplayModeSelection.getBy(
				getWindow().getClientCfg(EPConfigCM.DISPLAY_WIDTH).getValueToInteger(),
				getWindow().getClientCfg(EPConfigCM.DISPLAY_HEIGHT).getValueToInteger(),
				getWindow().getClientCfg(EPConfigCM.DISPLAY_DEPTH).getValueToInteger()
		));
		getTfRate().setText(""+getWindow().getClientCfg(EPConfigCM.DISPLAY_RATE).getValueToInteger());
		getCkWindowed().setSelected(getWindow().getClientCfg(EPConfigCM.DISPLAY_WINDOWED).getValueToBoolean());
		getCkAntiAliasing().setSelected(getWindow().getClientCfg(EPConfigCM.DISPLAY_ANTI_ALIASING).getValueToBoolean());
		getCbQuality().setSelectedItem(SpriteSurfaceQuality.parseInt(getWindow().getClientCfg(EPConfigCM.DISPLAY_TEXTURE_QUALITY).getValueToInteger()));
		getSvUiColorRed().getBar().setValue(getWindow().getClientCfg(EPConfigCM.DISPLAY_UI_COLOR_RED).getValueToInteger());
		getSvUiColorGreen().getBar().setValue(getWindow().getClientCfg(EPConfigCM.DISPLAY_UI_COLOR_GREEN).getValueToInteger());
		getSvUiColorBlue().getBar().setValue(getWindow().getClientCfg(EPConfigCM.DISPLAY_UI_COLOR_BLUE).getValueToInteger());
	}
	
	@Override
	public void v2m() {
		getWindow().setClientCfg(ConfigCM.SOCKET_LOGIN, getTfUsername().getText().trim());
		
		DisplayModeSelection dm = getDisplayModeView().getSelectedItem();
		if (dm != null) {
			getWindow().setClientCfg(EPConfigCM.DISPLAY_WIDTH, dm.x);
			getWindow().setClientCfg(EPConfigCM.DISPLAY_HEIGHT, dm.y);
			getWindow().setClientCfg(EPConfigCM.DISPLAY_DEPTH, dm.depth);
		}
		
		getWindow().setClientCfg(EPConfigCM.DISPLAY_RATE, Integer.parseInt(getTfRate().getText()));
		getWindow().setClientCfg(EPConfigCM.DISPLAY_WINDOWED, getCkWindowed().isSelected());
		getWindow().setClientCfg(EPConfigCM.DISPLAY_ANTI_ALIASING, getCkAntiAliasing().isSelected());
		getWindow().setClientCfg(EPConfigCM.DISPLAY_TEXTURE_QUALITY, ((SpriteSurfaceQuality) getCbQuality().getSelectedItem()).getId());
		
		getWindow().setClientCfg(EPConfigCM.DISPLAY_UI_COLOR_RED, getSvUiColorRed().getBar().getValue());
		getWindow().setClientCfg(EPConfigCM.DISPLAY_UI_COLOR_GREEN, getSvUiColorGreen().getBar().getValue());
		getWindow().setClientCfg(EPConfigCM.DISPLAY_UI_COLOR_BLUE, getSvUiColorBlue().getBar().getValue());
		
		try {
			getWindow().getPluginEngine().getSpriteCM().reload();
		} catch (KeyContainerAlreadyUsed e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public void cancel() {
		m2v();
	}
	
	private JPanel pBorderedGeneral;
	public JPanel getPBorderedGeneral() {
		if (pBorderedGeneral == null) {
			pBorderedGeneral = createBorderedPanel("General");
			
			pBorderedGeneral.add(getLUsername(), createGbc(0, 0, GridBagConstraints.NONE));
			pBorderedGeneral.add(getTfUsername(), createGbc(1, 0, GridBagConstraints.HORIZONTAL));
		}
		return pBorderedGeneral;
	}
	
	private JLabel lUsername;
	public JLabel getLUsername() {
		if (lUsername == null) {
			lUsername = new JLabel("Username");
		}
		return lUsername;
	}
	
	private JTextField tfUsername;
	public JTextField getTfUsername() {
		if (tfUsername == null) {
			tfUsername = new JTextField();
		}
		return tfUsername;
	}
	
	private JPanel pBorderedDisplay;
	public JPanel getPBorderedDisplay() {
		if (pBorderedDisplay == null) {
			pBorderedDisplay = createBorderedPanel("Display");
			
			pBorderedDisplay.add(getLDisplayMode(), createGbc(0, 1, GridBagConstraints.NONE));
			pBorderedDisplay.add(getDisplayModeView(), createGbc(1, 1, GridBagConstraints.NONE));
			
			pBorderedDisplay.add(getLRate(), createGbc(2, 1, GridBagConstraints.NONE));
			pBorderedDisplay.add(getTfRate(), createGbc(3, 1, GridBagConstraints.HORIZONTAL));
			
			pBorderedDisplay.add(getLQuality(), createGbc(0, 2, GridBagConstraints.NONE));
			pBorderedDisplay.add(getCbQuality(), createGbc(1, 2, GridBagConstraints.NONE, 3, 1));
			
			pBorderedDisplay.add(getLAntiAliasing(), createGbc(2, 2, GridBagConstraints.NONE));
			pBorderedDisplay.add(getCkAntiAliasing(), createGbc(3, 2, GridBagConstraints.HORIZONTAL));
			
			pBorderedDisplay.add(getLWindowed(), createGbc(0, 3, GridBagConstraints.NONE));
			pBorderedDisplay.add(getCkWindowed(), createGbc(1, 3, GridBagConstraints.NONE));
			
		}
		return pBorderedDisplay;
	}
	
	private JLabel lDisplayMode;
	public JLabel getLDisplayMode() {
		if (lDisplayMode == null) {
			lDisplayMode = new JLabel("Mode");
		}
		return lDisplayMode;
	}
	
	private DisplayModeSelectionView dmsView;
	public DisplayModeSelectionView getDisplayModeView() {
		if (dmsView == null) {
			dmsView = new DisplayModeSelectionView();
		}
		return dmsView;
	}
	
	private JLabel lWindowed;
	public JLabel getLWindowed() {
		if (lWindowed == null) {
			lWindowed = new JLabel("Windowed");
		}
		return lWindowed;
	}
	
	private JCheckBox ckWindowed;
	public JCheckBox getCkWindowed() {
		if (ckWindowed == null) {
			ckWindowed = new JCheckBox();
			ckWindowed.setOpaque(false);
			ckWindowed.setSelected(false);
		}
		return ckWindowed;
	}
	
	private JLabel lAntiAliasing;
	public JLabel getLAntiAliasing() {
		if (lAntiAliasing == null) {
			lAntiAliasing = new JLabel("Anti-Aliasing");
		}
		return lAntiAliasing;
	}
	
	private JCheckBox ckAntiAliasing;
	public JCheckBox getCkAntiAliasing() {
		if (ckAntiAliasing == null) {
			ckAntiAliasing = new JCheckBox();
			ckAntiAliasing.setOpaque(false);
			ckAntiAliasing.setSelected(false);
		}
		return ckAntiAliasing;
	}
	
	private JLabel lQuality;
	public JLabel getLQuality() {
		if (lQuality == null) 
			lQuality = new JLabel("Texture Quality");
		return lQuality;
	}
	
	private JComboBox cbQuality;
	public JComboBox getCbQuality() {
		if (cbQuality == null) {
			cbQuality = new JComboBox(SpriteSurfaceQuality.values());
		}
		return cbQuality;
	}
	
	private JLabel lRate;
	public JLabel getLRate() {
		if (lRate == null) {
			lRate = new JLabel("Target Rate");
		}
		return lRate;
	}
	
	private JTextField tfRate;
	public JTextField getTfRate() {
		if (tfRate == null) {
			tfRate = new JTextField();
		}
		return tfRate;
	}
	
	private JPanel pBorderedUI;
	public JPanel getPBorderedUI() {
		if (pBorderedUI == null) {
			pBorderedUI = createBorderedPanel("UI");
			
			pBorderedUI.add(getLUiColorRed(), createGbc(0, 4, GridBagConstraints.NONE));
			pBorderedUI.add(getSvUiColorRed(), createGbc(1, 4, GridBagConstraints.HORIZONTAL));
			
			pBorderedUI.add(getLUiColorGreen(), createGbc(0, 5, GridBagConstraints.NONE));
			pBorderedUI.add(getSvUiColorGreen(), createGbc(1, 5, GridBagConstraints.HORIZONTAL));
			
			pBorderedUI.add(getLUiColorBlue(), createGbc(0, 6, GridBagConstraints.NONE));
			pBorderedUI.add(getSvUiColorBlue(), createGbc(1, 6, GridBagConstraints.HORIZONTAL));
			
			pBorderedUI.add(getColorSelectionView(), createGbc(2, 4, GridBagConstraints.NONE, 2, 1));
			GridBagConstraints gbcPaint = createGbc(2, 5, GridBagConstraints.BOTH, 2, 2);
			gbcPaint.weightx = 0; gbcPaint.weighty = 0;
			pBorderedUI.add(getPaintUiColor(), gbcPaint);
		}
		return pBorderedUI;
	}
	
	private JLabel lUiColorRed;
	public JLabel getLUiColorRed() {
		if (lUiColorRed == null) {
			lUiColorRed = new JLabel("Color RED");
		}
		return lUiColorRed;
	}
	
	private ScrollView svUiColorRed;
	public ScrollView getSvUiColorRed() {
		if (svUiColorRed == null) {
			svUiColorRed = new ScrollView(0, 265) {
				private static final long serialVersionUID = 1L;
				@Override
				public void onAdjustementValue() {
					getLabel().setForeground(new Color(getBar().getValue(), 0, 0));
				}
			};
		}
		return svUiColorRed;
	}
	
	private JLabel lUiColorGreen;
	public JLabel getLUiColorGreen() {
		if (lUiColorGreen == null) {
			lUiColorGreen = new JLabel("Color GREEN");
		}
		return lUiColorGreen;
	}
	
	private ScrollView svUiColorGreen;
	public ScrollView getSvUiColorGreen() {
		if (svUiColorGreen == null) {
			svUiColorGreen = new ScrollView(0, 265) {
				private static final long serialVersionUID = 2L;
				@Override
				public void onAdjustementValue() {
					getLabel().setForeground(new Color(0, getBar().getValue(), 0));
				}
			};
		}
		return svUiColorGreen;
	}
	
	private JLabel lUiColorBlue;
	public JLabel getLUiColorBlue() {
		if (lUiColorBlue == null) {
			lUiColorBlue = new JLabel("Color BLUE");
		}
		return lUiColorBlue;
	}
	
	private ScrollView svUiColorBlue;
	public ScrollView getSvUiColorBlue() {
		if (svUiColorBlue == null) {
			svUiColorBlue = new ScrollView(0, 265) {
				private static final long serialVersionUID = 3L;
				@Override
				public void onAdjustementValue() {
					getLabel().setForeground(new Color(0, 0, getBar().getValue()));
				}
			};
		}
		return svUiColorBlue;
	}
	
	private ColorSelectionView csView;
	public ColorSelectionView getColorSelectionView() {
		if (csView == null) {
			csView = new ColorSelectionView();
			csView.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent arg0) {
					ColorSelection cs = getColorSelectionView().getSelectedItem();
					if (cs != null && cs.getColor() != null) {
						getSvUiColorRed().getBar().setValue(cs.getColor().getRed());
						getSvUiColorGreen().getBar().setValue(cs.getColor().getGreen());
						getSvUiColorBlue().getBar().setValue(cs.getColor().getBlue());
					}
				}
			});
		}
		return csView;
	}
	
	private JPanel paintUiColor;
	public JPanel getPaintUiColor() {
		if (paintUiColor == null) {
			paintUiColor = new JPanel();
			paintUiColor.setOpaque(true);
			paintUiColor.setBackground(Color.RED);
		}
		return paintUiColor;
	}
	
	private void refreshPaintUiColor() {
		try {
			getColorSelectionView().setSelectedItem(ColorSelection.PRESELECT);
			Integer red = getSvUiColorRed().getBar().getValue();
			Integer green = getSvUiColorGreen().getBar().getValue();
			Integer blue = getSvUiColorBlue().getBar().getValue();
			getPaintUiColor().setBackground(new Color(red, green, blue));
		} catch (Exception e) {
			getPaintUiColor().setBackground(Color.BLACK);
		}
	}
}