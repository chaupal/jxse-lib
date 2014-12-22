package net.jxse.osgi.boot;
import java.util.logging.Logger;

import net.jxse.osgi.boot.factory.PlatformBuilder;
import net.jxse.osgi.boot.factory.ShadowPeerGroupBuilder;
import net.jxse.osgi.boot.factory.StdPeerGroupBuilder;
import net.jxta.impl.peergroup.JxtaLoaderModuleManager;
import net.jxta.module.IJxtaModuleBuilder;
import net.jxta.module.IModuleBuilder;
import net.jxta.module.IModuleManager;
import net.jxta.peergroup.core.Module;
import net.jxta.peergroup.core.ModuleSpecID;
import net.jxta.protocol.ModuleImplAdvertisement;

public class Component implements IModuleManager<Module>{

	private static String S_WRN_NOT_REGISTERED = "The factory is not registered in the module manager: ";
	
	private JxtaLoaderModuleManager manager;
	
	private static Logger logger = Logger.getLogger( Component.class.getName() );

	public Component(){
		manager = JxtaLoaderModuleManager.getRoot();
		IJxtaModuleBuilder factory = new StdPeerGroupBuilder();
		factory.init();
		manager.registerBuilder( factory);
		factory = new ShadowPeerGroupBuilder();
		factory.init();
		manager.registerBuilder( factory);
		factory = new PlatformBuilder(); 
		factory.init();
		manager.registerBuilder( factory);		
	}
	
	public void activate(){
		
	}
	
	public void deactivate(){
		manager = null;		
	}
	
	@Override
	public void registerBuilder(IModuleBuilder<? extends Module> factory) {
		if( !( factory instanceof IJxtaModuleBuilder)){
			logger.warning( S_WRN_NOT_REGISTERED + factory.getIdentifier());
		}else{
			manager.registerBuilder( factory);
		}
	}

	@Override
	public void unregisterBuilder( IModuleBuilder<? extends Module> factory) {
		manager.unregisterBuilder(factory);
	}

	@Override
	public ModuleImplAdvertisement findModuleImplAdvertisement(ModuleSpecID msid) {
		return manager.findModuleImplAdvertisement(msid);
	}
}