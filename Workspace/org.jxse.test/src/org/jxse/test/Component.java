package org.jxse.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Examples.A_JXTA_Connection_And_Local_Configuration._100_Starting_And_Stopping_JXTA_Example;
import net.jxta.module.IModuleBuilder;
import net.jxta.peergroup.core.JxtaLoaderModuleManager;
import net.jxta.peergroup.core.Module;

public class Component{

	private JxtaLoaderModuleManager<Module> manager;
	private static ExecutorService service = Executors.newSingleThreadExecutor();
		
	public Component(){
		manager = JxtaLoaderModuleManager.getRoot( Component.class );	
		//manager.registerBuilder( new PlatformBuilder());
	}
	
	public void activate(){
		runExample();
	}

	public static void runExample(){
		RunExample example = new RunExample();
		service.execute(example);
	}
	

	public void deactivate(){
		manager = null;		
	}

	public void registerBuilder(IModuleBuilder<Module> builder) {
			manager.registerBuilder( builder);
	}

	public void unregisterBuilder( IModuleBuilder<Module> builder ) {
		manager.unregisterBuilder( builder );
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