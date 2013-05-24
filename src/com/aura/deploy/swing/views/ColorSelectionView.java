package com.aura.deploy.swing.views;

import java.awt.Color;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class ColorSelectionView extends JComboBox {
	public ColorSelectionView() {
		super(ColorSelection.values());
		
	}
	
	public enum ColorSelection {
		PRESELECT(0, null),
		BLUE(1, Color.BLUE),
		CYAN(2, Color.CYAN),
		GRAY(4, Color.GRAY),
		GREEN(5, Color.GREEN),
		LIGHT_GRAY(6, Color.LIGHT_GRAY),
		MAGENTA(7, Color.MAGENTA),
		ORANGE(8, Color.ORANGE),
		PINK(9, Color.PINK),
		RED(10, Color.RED),
		WHITE(11, Color.WHITE),
		YELLOW(12, Color.YELLOW);
		
		private int id;
		private Color color;
		private ColorSelection(int id, Color color) {
			this.id = id;
			this.color = color;
		}
		
		public int getId() {
			return id;
		}
		public Color getColor() {
			return color;
		}

		@Override
		public String toString() {
			switch (this) {
				case PRESELECT:
					return "Preselect...";
				default: 
					return super.toString();
			}
		}
		
		public static ColorSelection getBy(int red, int green, int blue) {
			for (ColorSelection cm: ColorSelection.values()) {
				if (cm.color != null 
						&& cm.color.getRed() == red 
						&& cm.color.getGreen() == green 
						&& cm.color.getBlue() == blue) {
					return cm;
				}
			}
			return ColorSelection.PRESELECT;
		}
	}
	
	@Override
	public ColorSelection getSelectedItem() {
		return (ColorSelection) super.getSelectedItem();
	}
	public void setSelectedItem(ColorSelection dm) {
		super.setSelectedItem(dm);
	}
}