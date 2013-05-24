package com.aura.deploy.engine;

import com.aura.base.utils.AuraLogger;
import com.aura.client.AuraClient;
import com.aura.engine.AuraEngine;
import com.aura.engine.AuraScreen;
import com.aura.engine.configuration.EPConfigCM;
import com.aura.engine.module.EMConsoleUI;
import com.aura.engine.module.EMDebugUI;
import com.aura.engine.module.EMEntity;
import com.aura.engine.module.EMWorld;
import com.aura.engine.module.ShellContainer;

public class DeployEngine extends AuraEngine {
	public DeployEngine(AuraClient a, AuraScreen s) {
		super(a, s, a.getCfgManager().getConfigIntegerValue(EPConfigCM.DISPLAY_RATE));
	}
	@Override
	public void init() {
		attachModule(0, new EMWorld((AuraClient) getMainAura(), getScreen(), this));
		attachModule(0, new EMEntity((AuraClient) getMainAura(), getScreen(), this));
		attachModule(1, new EMDebugUI((AuraClient) getMainAura(), getScreen(), this));
		attachModule(2, new EMConsoleUI((AuraClient) getMainAura(), getScreen(), this));
		
		new ShellContainer((AuraClient) getMainAura(), getScreen(), this).init();
	}
	@Override
	public void play() {
		super.play();
		
		// à mettre en commentaire
//		setDebug(true);
		AuraLogger.config(getMainAura().getSide(), "Welcome ! Tape /help");
	}
}