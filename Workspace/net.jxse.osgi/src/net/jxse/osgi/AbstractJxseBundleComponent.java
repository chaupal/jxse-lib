package net.jxse.osgi;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;

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
public abstract class AbstractJxseBundleComponent implements CommandProvider{

	private AbstractJxseActivator activator;
	private boolean started;
			
	protected AbstractJxseBundleComponent( AbstractJxseActivator activator ){
		this.activator = activator;	
		this.started = false;
	}
	
	public void activate(){
		this.started = true;
		this.activator.activate();
	}
	public void deactivate(){
		this.started = false;
	}

	public void registerBuilder(IModuleBuilder<Module> builder) {
		this.activator.registerBuilder( builder);
	}

	public void unregisterBuilder( IModuleBuilder<Module> builder ) {
		this.activator.unregisterBuilder( builder );
	}

	public synchronized Object _jxse(CommandInterpreter ci) {
		ci.println("Bundle " + this.getClass().getPackage() + "has started: " + this.started + "\n");
		ci.println( "\t and registered the following modules: \n");
		ci.println( activator.printRegisteredBuilders());
		ci.println( "\n");
		return null;
	}
	
	public String getHelp() {
		return "\tjxse - Provides information about the registered JXSE builders.";
	}
}