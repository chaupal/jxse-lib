package org.jxse.test;

import net.jxta.module.IModuleManager;

public class JxtaComponent {

	private static IModuleManager<?,?> moduleManager;
	
	public void activate(){
		
	}

	public void deactivate(){
		
	}

	public void setModuleManager( IModuleManager<?,?> manager ){
		moduleManager = manager;
	}

	public void unsetModuleManager( IModuleManager<?,?> manager ){
		moduleManager = null;
	}
	
	private static IModuleManager<?,?> getManager(){
		return moduleManager;
	}
}
