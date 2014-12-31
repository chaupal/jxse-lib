package net.jxse.osgi.platform;
import java.util.logging.Logger;

import net.jxse.osgi.pltform.builder.PlatformBuilder;
import net.jxta.impl.modulemanager.ModuleException;
import net.jxta.module.IModuleBuilder;
import net.jxta.module.IModuleManager;
import net.jxta.peergroup.core.JxtaLoaderModuleManager;
import net.jxta.peergroup.core.Module;
import net.jxta.peergroup.core.ModuleSpecID;
import net.jxta.protocol.ModuleImplAdvertisement;

public class Component implements IModuleManager<Module>{

	private static String S_WRN_NOT_REGISTERED = "The factory is not registered in the module manager: ";
	
	private JxtaLoaderModuleManager<Module> manager;
	
	private static Logger logger = Logger.getLogger( Component.class.getName() );

	public Component(){
		manager = JxtaLoaderModuleManager.getRoot();
		IModuleBuilder<Module> builder = new PlatformBuilder();
		manager.registerBuilder( builder);		
	}
	
	public void activate(){
		
	}
	
	public void deactivate(){
		manager = null;		
	}

	
	@Override
	public void registerBuilder(IModuleBuilder<Module> factory) {
			manager.registerBuilder( factory);
	}

	@Override
	public void unregisterBuilder( IModuleBuilder<Module> factory) {
		manager.unregisterBuilder(factory);
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
		return manager.getModules(id);
	}
}