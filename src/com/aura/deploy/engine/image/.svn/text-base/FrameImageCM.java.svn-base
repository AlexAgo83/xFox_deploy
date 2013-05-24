package com.aura.deploy.engine.image;

import com.aura.base.Aura;
import com.aura.base.container.AbstractContainerManager;
import com.aura.base.manager.configuration.ConfigCM;
import com.aura.base.utils.Curseur;
import com.aura.client.AuraClient;

public class FrameImageCM extends AbstractContainerManager<AuraClient, FrameImageCE> {
	public static final String D_UI = "ui";
	
	public static final Curseur CURSEUR = new Curseur(ConfigCM.class);
	
	public static final int FRAME_ICON = CURSEUR.nextVal();
	public static final int FRAME_BG = CURSEUR.nextVal();
	
	public FrameImageCM(AuraClient aura) {
		super(aura);
	}
	
	@Override 
	protected void implLoad() throws KeyContainerAlreadyUsed {
		register(new FrameImageCE(FRAME_ICON, getFolder() + "frame_icon.png"));
		register(new FrameImageCE(FRAME_BG, getFolder() + "frame_bg.png"));
	}
	
	private String folder;
	public String getFolder() {
		if (folder == null) {
			folder = Aura.D_RESS + "\\" + D_UI + "\\";
		}
		return folder;
	}
	
	@Override
	public FrameImageCE getElement(int id) {
		return super.getElement(id);
	}
}