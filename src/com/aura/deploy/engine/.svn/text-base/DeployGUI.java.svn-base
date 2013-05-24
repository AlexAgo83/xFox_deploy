package com.aura.deploy.engine;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;

import com.aura.base.Aura;
import com.aura.base.container.AbstractContainerManager.KeyContainerAlreadyUsed;
import com.aura.base.manager.configuration.ConfigCE;
import com.aura.base.thread.AuraThread;
import com.aura.chat.plugin.ChatClientPlugin;
import com.aura.chat.plugin.ChatServerPlugin;
import com.aura.client.AuraClient;
import com.aura.deploy.engine.image.FrameImageCE;
import com.aura.deploy.engine.image.FrameImageCM;
import com.aura.engine.AuraDisplayMode;
import com.aura.engine.AuraScreen;
import com.aura.engine.packet.EPPacketCM;
import com.aura.engine.packet.EPPacketEntityMove;
import com.aura.engine.packet.EntityElementMoveOrientation;
import com.aura.engine.plugin.EngineClientPlugin;
import com.aura.engine.plugin.EngineServerPlugin;
import com.aura.engine.univers.DrawableGarbage;
import com.aura.engine.univers.EntityTokenServer;
import com.aura.engine.univers.UniversServer;
import com.aura.engine.univers.drawable.DEntity;
import com.aura.engine.utils.Location;
import com.aura.engine.utils.Orientation;
import com.aura.server.AuraServer;
import com.jgoodies.looks.LookUtils;
import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.jgoodies.looks.plastic.PlasticTheme;

public class DeployGUI {
	public DeployGUI() {}
	
	private AuraClient client;
	public AuraClient getClient() {
		return client;
	}
	
	public ConfigCE<?> getClientCfg(int id) {
		return getClient().getCfgManager().getConfig(id);
	}
	public void setClientCfg(int id, Object value) {
		ConfigCE<?> ce = getClient().getCfgManager().getConfig(id);
		ce.setObject(value);
		getClient().getCfgManager().save(ce);
	}
	
	private EngineClientPlugin pluginEngine;
	public EngineClientPlugin getPluginEngine() {
		return pluginEngine;
	}
	
	private AuraServer server;
	public AuraServer getServer() {
		return server;
	}
	
	private FrameImageCM frameImageCM;
	public FrameImageCM getFrameImageCM() {
		return frameImageCM;
	}
	public BufferedImage getImage(int id) {
		FrameImageCE ce = getFrameImageCM().getElement(id);
		return ce != null ? ce.getImage() : null;
	}
	
	// INIT
	public void initClient() {
		this.client = new AuraClient() {
			@Override
			public void initPlugin(Aura self) {
				frameImageCM = new FrameImageCM((AuraClient) self);
				pluginEngine = new EngineClientPlugin(this) {
					@Override
					public AuraScreen createScreen(AuraClient a) {
						return new DeployScreen(a.getBuildRevision(), a, new AuraDisplayMode(getAura()));
					}
					@Override
					public boolean load() {
						if (super.load()) {
							try {
								frameImageCM.load();
								return true;
							} catch (KeyContainerAlreadyUsed e) {
								e.printStackTrace();
							}
						}
						return false;
					}
				};
				addPlugin(pluginEngine);
				addPlugin(new ChatClientPlugin(this));
			}
		};
//		AuraLogger.debug(false);
	}
	
	public void initServer() {
		server = new AuraServer() {
			@Override
			public void initPlugin(final Aura self) {
				addPlugin(new EngineServerPlugin(this) {
					public void onSystemLoad() {
						super.onSystemLoad();
						// FIXME XF(AA) [BOT] EXEMPLE A SUPPRIMER
//						DrawableGarbage dg1 = new DrawableGarbage(new EntityTokenServer(-1l, null));
//						test_bot(getAura(), dg1, getUnivers(), 0, 0, 1, 900);
//						test_bot(getAura(), dg1, getUnivers(), 50, 20, 4, 750);
//						
//						DrawableGarbage dg2 = new DrawableGarbage(new EntityTokenServer(-2l, null));
//						test_bot(getAura(), dg2, getUnivers(), -25, 60, 3, 400);
//						test_bot(getAura(), dg2, getUnivers(), 80, -30, 2, 500);
					};
				});
				addPlugin(new ChatServerPlugin(this));
			}
		};
	}
	
	public void test_bot(final AuraServer aura, final DrawableGarbage dg, final UniversServer univers, int x, int y, final double speed, final int timer) {
		// FIXME XF(AA) [BOT] EXEMPLE A SUPPRIMER
		
		long id = DEntity.GENERER_ID();
		final DEntity<AuraServer> e = new DEntity<AuraServer>(aura, 
				id, dg, 
				"BOT_"+id, 
				new Location(x, y), null, 
				univers.getDefaultWorld().getEntityGarbage());
		
		((EntityTokenServer) dg.getToken()).setWorld(univers.getDefaultWorld());
		univers.getDefaultWorld().attach(e);
		
		new AuraThread(aura, "ENTITY_BOT_MOVEMENT_"+e.getId(), timer) {
			@Override public void doOnClose() {}
			@Override public boolean condition() {return aura.isRunning();}
			
			@Override
			public void action() {
				if (e.getMoveThread() == null) {
					e.move(Orientation.IDLE, speed);
				} else {
					Orientation o = e.getMoveThread().getCurrentOrientation();
					switch (o) {
						case IDLE: o = Orientation.NORD; break;
						case NORD: o = Orientation.EST; break;
						case EST: o = Orientation.SUD; break;
						case SUD: o = Orientation.OUEST; break;
						case OUEST: o = Orientation.NORD; break;
					}
					e.move(o, speed);
					
					EPPacketEntityMove em = (EPPacketEntityMove) aura.createPacket(EPPacketCM.ENTITY_MOVE);
					EntityElementMoveOrientation mo = new EntityElementMoveOrientation();
					mo.setDrawableId(e.getId());
					mo.setTokenId(dg.getToken().getId());
					mo.setWorldId(((EntityTokenServer) dg.getToken()).getWorld().getId());
					mo.setLocation(e.getLocation());
					mo.setName(e.getName());
					mo.setSpeed(e.getSpeed());
					mo.setOrientation(o);
					em.attachElements(mo);
					univers.getDefaultWorld().broadCast(em);
				}
			}
		}.start();
		////
	}
	
	public void initLookAndFeel() {
		try {
			LookUtils.setLookAndTheme(
					new Plastic3DLookAndFeel(), 
					new PlasticTheme() {
						
				private final ColorUIResource ress2 = new ColorUIResource(new Color(200,180,100));
				@Override
				protected ColorUIResource getPrimary2() {
					return ress2;
				}
				
				private final ColorUIResource ress3 = new ColorUIResource(new Color(200,180,130));
				@Override
				protected ColorUIResource getPrimary3() {
					return ress3;
				}
			});
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
	}
}