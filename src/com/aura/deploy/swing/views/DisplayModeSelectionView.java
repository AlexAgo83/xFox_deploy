package com.aura.deploy.swing.views;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class DisplayModeSelectionView extends JComboBox {
	public DisplayModeSelectionView() {
		super(DisplayModeSelection.values());
	}
	
	public enum DisplayModeSelection {
		R_640_480_16(640,480,16),
		R_640_480_32(640,480,32),
		R_800_600_16(800,600,16),
		R_800_600_32(800,600,32),
		R_1024_600_16(1024,600,16),
		R_1024_600_32(1024,600,32),
		R_1024_840_16(1024,768,16),
		R_1024_840_32(1024,768,32),
		R_1152_864_16(1152,864,16),
		R_1152_864_32(1152,864,32),
		R_1280_720_16(1280,720,16),
		R_1280_720_32(1280,720,32),
		R_1280_768_16(1280,768,16),
		R_1280_768_32(1280,768,32),
		R_1280_800_16(1280,800,16),
		R_1280_800_32(1280,800,32),
		R_1280_960_16(1280,960,16),
		R_1280_960_32(1280,960,32),
		R_1280_1024_16(1280,1024,16),
		R_1280_1024_32(1280,1024,32);
		
		public final int x;
		public final int y;
		public final int depth;
		private DisplayModeSelection(int x, int y, int depth) {
			this.x = x;
			this.y = y;
			this.depth = depth;
		}
		public String toString() {
			return x + "x" + y + " - " + depth+"";
		};
		public static DisplayModeSelection getBy(int x, int y, int depth) {
			for (DisplayModeSelection dm: DisplayModeSelection.values()) {
				if (dm.x == x && dm.y == y && dm.depth == depth) {
					return dm;
				}
			}
			return null;
		}
	}
	@Override
	public DisplayModeSelection getSelectedItem() {
		return (DisplayModeSelection) super.getSelectedItem();
	}
	public void setSelectedItem(DisplayModeSelection dm) {
		super.setSelectedItem(dm);
	};
}