package net.jxse.osgi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

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
public abstract class AbstractJxseActivator implements BundleActivator{

	private JxtaLoaderModuleManager<Module> manager;
	private ExecutorService service = Executors.newSingleThreadExecutor();

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
		
	
	protected AbstractJxseActivator() {
		super();
		manager = JxtaLoaderModuleManager.getRoot( this.getClass() );	
	}


	@Override
	public void stop(BundleContext context) throws Exception {
		service.shutdown();
		manager.stopApp();
		manager = null;		
	}

	/**
	 * Activate the example
	 */
	public void activate(){
		service.execute( example );
	}
	
	/**
	 * Get the module manager
	 * @return
	 */
	protected JxtaLoaderModuleManager<Module> getManager() {
		return manager;
	}

	public void registerBuilder(IModuleBuilder<Module> builder) {
			manager.registerBuilder( builder);
	}

	public void unregisterBuilder( IModuleBuilder<Module> builder ) {
		manager.unregisterBuilder( builder );
	}

	protected abstract void onRunJxse();
	
	public String printRegisteredBuilders(){
		return this.manager.printRegisteredBuilders();
	}
}