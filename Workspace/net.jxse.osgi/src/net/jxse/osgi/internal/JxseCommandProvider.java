package net.jxse.osgi.internal;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;

import net.jxta.impl.loader.JxtaLoaderModuleManager;
import net.jxta.module.IModuleBuilder;
import net.jxta.peergroup.core.Module;

/**
 * This component allows for a core JXSE component to be strated, for instance as a declarative service.
 * The code is run in a separate thread, so that startup issues do not interfere with the regular OSGI
 * startup.
 *  
 * @author Kees
 *
 */
public class JxseCommandProvider implements CommandProvider{

	private JxtaLoaderModuleManager<Module> manager;
			
	public JxseCommandProvider(){
		manager = JxtaLoaderModuleManager.getRoot( JxseCommandProvider.class, true );	
	}
	
	/**
	 * Get the module manager
	 * @return
	 */
	protected JxtaLoaderModuleManager<Module> getManager() {
		return manager;
	}


	public void activate(){
	}
	
	public void deactivate(){
		manager.stopApp();
		manager = null;		
	}

	public void registerBuilder(IModuleBuilder<Module> builder) {
		manager.registerBuilder( builder);
	}

	public void unregisterBuilder( IModuleBuilder<Module> builder ) {
		manager.unregisterBuilder( builder );
	}

	public Object _jxse(CommandInterpreter ci) {
		StringBuffer buffer = new StringBuffer();
		buffer.append( manager.printRegisteredBuilders());
		buffer.append( "\n");
		return buffer.toString();
	}
	
	public String getHelp() {
		return "\tjxse - Provides information about the registered JXSE builders.";
	}
}