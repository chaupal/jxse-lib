package net.jxse.osgi.test;

import net.jxse.osgi.test.modulemanager.JxtaLoaderModuleManagerTest;
import net.jxta.impl.loader.JxtaLoaderModuleManager;
import net.jxta.peergroup.core.Module;
import net.osgi.jxse.AbstractJxseComponent;

public class Component extends AbstractJxseComponent{

	public static JxtaLoaderModuleManager<Module> manager;
	
	public Component() {
		super();
		manager = super.getManager();
	}

	
	public static JxtaLoaderModuleManager<Module> getModuleManager() {
		return manager;
	}


	@Override
	protected void onRunJxse() {
		JxtaLoaderModuleManagerTest test = new JxtaLoaderModuleManagerTest();
		try {
			Thread.sleep( 2000 );

			test.setup();
			test.testBuild();
			test.tearDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
}