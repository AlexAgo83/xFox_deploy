package com.aura.deploy.swing.views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import com.aura.deploy.swing.AbstractDeployPanel;

@SuppressWarnings("serial")
public class ScrollView extends JPanel {
	final int min, max;
	
	public ScrollView(int min, int max) {
		super(new GridBagLayout());
		
		this.setOpaque(false);
		this.min = min;
		this.max = max;
		
		initialize();
	}
	
	private void initialize() {
		add(getBar(), AbstractDeployPanel.createGbc(0, 0, GridBagConstraints.HORIZONTAL));
		add(getLabel(), AbstractDeployPanel.createGbc(1, 0, GridBagConstraints.NONE));
	}
	
	private JLabel label;
	public JLabel getLabel() {
		if (label == null) {
			label = new JLabel("000");
		}
		return label;
	}
	
	private JScrollBar bar;
	public JScrollBar getBar() {
		if (bar == null) {
			bar = new JScrollBar(JScrollBar.HORIZONTAL);
			bar.setOpaque(true);
			bar.setMinimum(min);
			bar.setMaximum(max);
			bar.addAdjustmentListener(new AdjustmentListener() {
				@Override
				public void adjustmentValueChanged(AdjustmentEvent e) {
					int value = getBar().getValue();
					String txt = "";
					
					if (value < 10) {
						txt = "00"+value;
					} else if (value < 100) {
						txt = "0"+value;
					} else {
						txt = ""+value;
					}
					
					getLabel().setText(txt);
					onAdjustementValue();
				}
			});
		}
		return bar;
	}
	public void onAdjustementValue() {
		// a surcharger...
	}
}