package net.jxse.osgi.loader;

import net.jxse.osgi.loader.factory.PlatformFactory;
import net.jxse.osgi.loader.factory.ShadowPeerGroupFactory;
import net.jxse.osgi.loader.factory.StdPeerGroupFactory;
import net.jxta.module.IModuleManager;
import net.jxta.peergroup.core.JxtaLoaderModuleManager;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class JxseActivator implements BundleActivator {

	@SuppressWarnings("rawtypes")
	IModuleManager manager;
	
	@SuppressWarnings("unchecked")
	@Override
	public void start(BundleContext context) throws Exception {
		manager = JxtaLoaderModuleManager.getRoot( this.getClass() );
		manager.registerFactory( new PlatformFactory());
		manager.registerFactory( new ShadowPeerGroupFactory());
		manager.registerFactory( new StdPeerGroupFactory());
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		manager = null;
	}

}
