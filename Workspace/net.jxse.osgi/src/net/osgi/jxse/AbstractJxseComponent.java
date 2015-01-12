package net.osgi.jxse;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
public abstract class AbstractJxseComponent implements CommandProvider{

	private JxtaLoaderModuleManager<Module> manager;
	private static ExecutorService service = Executors.newSingleThreadExecutor();
	private boolean started;
	
	private Runnable example = new Runnable(){

		@Override
		public void run() {
			try{
				 onRunJxse();
			}
			catch( Exception ex ){
				ex.printStackTrace();
			}
		}
		
	};
		
	public AbstractJxseComponent(){
		manager = JxtaLoaderModuleManager.getRoot( AbstractJxseComponent.class );	
		this.started = false;
	}
	
	public void activate(){
		this.started = true;
		service.execute(example);
	}
	public void deactivate(){
		this.started = false;
		service.shutdown();
		manager.stopApp();
		manager = null;		
	}

	public void registerBuilder(IModuleBuilder<Module> builder) {
			manager.registerBuilder( builder);
	}

	public void unregisterBuilder( IModuleBuilder<Module> builder ) {
		manager.unregisterBuilder( builder );
	}

	protected abstract void onRunJxse();
	
	public synchronized Object _jxse(CommandInterpreter ci) {
		ci.println("Bundle " + this.getClass().getPackage() + "has started: " + this.started + "\n");
		ci.println( "\t and registered the following modules: \n");
		ci.println( manager.printRegisteredBuilders());
		ci.println( "\n");
		return null;
	}
	
	public String getHelp() {
		return "\tjxse - Provides information about the registered JXSE builders.";
	}
}