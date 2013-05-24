package com.aura.deploy.engine;

import com.aura.client.AuraClient;
import com.aura.engine.AuraDisplayMode;
import com.aura.engine.AuraEngine;
import com.aura.engine.AuraScreen;

public class DeployScreen extends AuraScreen {
	public DeployScreen(String title, AuraClient aura, AuraDisplayMode displayMode) {
		super(title, aura, displayMode);
	}
	@Override
	public AuraEngine createEngine(final AuraClient client, final AuraScreen screen) {
		return new DeployEngine(client, screen);
	}
}