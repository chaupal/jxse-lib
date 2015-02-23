/*******************************************************************************
 * Copyright (c) 2013 Condast and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Kees Pieters - initial API and implementation
 *******************************************************************************/
package net.jxse.osgi.compat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import net.jxta.impl.loader.JxtaLoaderModuleManager;
import net.jxta.module.IModuleBuilder;
import net.jxta.module.IModuleDescriptor;
import net.jxta.module.IModuleManager;
import net.jxta.peergroup.core.Module;

public abstract class AbstractJxseBuilder{

	private static final String S_ERR_CANNOT_ACTIVATE = " Cannot activvate this descriptor, because the required modules are not loaded: ";
	private static final String S_STARTING_JXSE = " Activating JXSE Service: ";
	
	private String filter = "(objectclass=" + IModuleBuilder.class.getName() + ")";
		
	private IModuleManager<Module> manager;
	private IModuleDescriptor descriptor;
	private String bundle_id;
	
	private BundleContext context;
	
	private ExecutorService service = Executors.newSingleThreadExecutor();
	private ServiceTracker<?,?> moduleTracker;
	private boolean started;
	
	
	private Runnable example = new Runnable(){

		@Override
		public void run() {
			try{
				logger.info( S_STARTING_JXSE + bundle_id ); 
				onRunJxse();
			}
			catch( Exception ex ){
				ex.printStackTrace();
			}
		}
		
	};

	
	private Logger logger = Logger.getLogger( this.getClass().getName() );

	private ServiceListener sl = new ServiceListener() {

		@SuppressWarnings("unchecked")
		public void serviceChanged(ServiceEvent ev) {
			ServiceReference<?> sr = ev.getServiceReference();
			Object obj = context.getService(sr);
			if( obj == null )
				return;
			System.out.println( obj.getClass().getName());
					
			IModuleBuilder<Module> builder = (IModuleBuilder<Module>)obj;
			switch(ev.getType()) {
			case ServiceEvent.REGISTERED:
				manager.registerBuilder(builder);
				if( manager.canBuild(descriptor))
					activate();
				break;
			case ServiceEvent.UNREGISTERING:
				manager.unregisterBuilder( builder);
				break;
			default:
				break;
			}
		}
	};
			
	//private 
	protected AbstractJxseBuilder(String bundle_id, IModuleDescriptor descriptor ) {
		this.bundle_id = bundle_id;
		this.descriptor = descriptor;
		this.started = false;
		manager = JxtaLoaderModuleManager.getRoot( this.getClass(), true );
	}

	protected void activate(){
		if(started)
			return;
		if( !manager.canBuild( descriptor))
			logger.severe( S_ERR_CANNOT_ACTIVATE + descriptor );
		service.execute(example);
		started = true;
	}

	/**
	 * Run the code in this method in a separate thread
	 */
	protected abstract void onRunJxse();
	
	public void start(final BundleContext bundleContext) throws Exception {
		context = bundleContext;
		moduleTracker = new ServiceTracker<Object, Object>( bundleContext, IModuleBuilder.class.getName(), null );
		moduleTracker.open();
		bundleContext.addServiceListener(sl, filter);
		ServiceReference<?>[] srl = bundleContext.getServiceReferences(( String )null, filter);
		for( ServiceReference<?> sr:  srl ) {
			sl.serviceChanged(new ServiceEvent(ServiceEvent.REGISTERED, sr));
		}
	}

	public void stop(BundleContext bundleContext) throws Exception {
		this.started = false;
		bundleContext.removeServiceListener( sl );
		service.shutdown();
		moduleTracker.close();
	}

	/**
	 * Get the module manager
	 * @return
	 */
	protected IModuleManager<Module> getManager() {
		return manager;
	}

	public void registerBuilder(IModuleBuilder<Module> builder) {
			manager.registerBuilder( builder);
	}

	public void unregisterBuilder( IModuleBuilder<Module> builder ) {
		manager.unregisterBuilder( builder );
	}

	public String printRegisteredBuilders(){
		return this.manager.printRegisteredBuilders();
	}
}