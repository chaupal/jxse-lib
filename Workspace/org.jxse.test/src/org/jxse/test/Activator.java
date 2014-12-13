package org.jxse.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import Examples.A_JXTA_Connection_And_Local_Configuration._100_Starting_And_Stopping_JXTA_Example;

public class Activator implements BundleActivator {

	private static BundleContext context;
	
	private static ExecutorService service = Executors.newSingleThreadExecutor();
	
	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
	}

	public static void runExample(){
		RunExample example = new RunExample();
		service.execute(example);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

	private static class RunExample implements Runnable{
		
		@Override
		public void run() {
			try{
				 _100_Starting_And_Stopping_JXTA_Example.main(null);
			}
			catch( Exception ex ){
				ex.printStackTrace();
			}
		};
	}
}
