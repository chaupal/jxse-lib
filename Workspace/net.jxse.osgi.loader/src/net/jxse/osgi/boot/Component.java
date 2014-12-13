package net.jxse.osgi.boot;
import java.util.logging.Logger;

import net.jxse.osgi.boot.factory.PlatformFactory;
import net.jxse.osgi.boot.factory.ShadowPeerGroupFactory;
import net.jxse.osgi.boot.factory.StdPeerGroupFactory;
import net.jxta.module.IJxtaModuleFactory;
import net.jxta.module.IJxtaModuleManager;
import net.jxta.module.IModuleFactory;
import net.jxta.module.IModuleManager;
import net.jxta.module.ModuleException;
import net.jxta.peergroup.core.JxtaLoaderModuleManager;
import net.jxta.peergroup.core.Module;
import net.jxta.peergroup.core.ModuleSpecID;
import net.jxta.protocol.ModuleImplAdvertisement;


public class Component implements IModuleManager<Module, IModuleFactory<Module>>{

	private static String S_WRN_NOT_REGISTERED = "The factory is not registered in the module manager: ";
	
	private IJxtaModuleManager manager;
	
	private static Logger logger = Logger.getLogger( Component.class.getName() );

	public Component(){
		manager = JxtaLoaderModuleManager.getRoot();
		manager.registerFactory( new PlatformFactory());
		manager.registerFactory( new ShadowPeerGroupFactory());
		manager.registerFactory( new StdPeerGroupFactory());		
	}
	
	public void activate(){
		
	}
	
	public void deactivate(){
		manager = null;		
	}
	
	public void register( IModuleFactory<?> factory){
		if( !( factory instanceof IJxtaModuleFactory)){
			logger.warning( S_WRN_NOT_REGISTERED + factory.getIdentifier());
		}else{
			manager.registerFactory(factory);
		}
	}

	public void unregister( IModuleFactory<?> factory){
		manager.unregisterFactory(factory);
	}

	@Override
	public void registerFactory(IModuleFactory<Module> factory) {
		manager.registerFactory(factory);
	}

	@Override
	public void unregisterFactory(IModuleFactory<Module> factory) {
		manager.unregisterFactory(factory);
	}

	@Override
	public ModuleImplAdvertisement findModuleImplAdvertisement(ModuleSpecID msid) {
		return manager.findModuleImplAdvertisement(msid);
	}

	@Override
	public Module getModule(ModuleImplAdvertisement adv) throws ModuleException {
		return manager.getModule(adv);
	}

	@Override
	public Module[] getModules(ModuleSpecID id) {
		return getModules(id);
	}

}
