package org.jxse.test;

import net.jxse.practical.jxta.AbstractPJ2BundleActivator;

import org.osgi.framework.BundleContext;

public class Activator extends AbstractPJ2BundleActivator {

	private static AbstractPJ2BundleActivator activator;
	
	public Activator() {
		super(Examples.A_100_Starting_And_Stopping_JXTA);
	}

	private static BundleContext context;
	
	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		activator = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		activator = null;
		Activator.context = null;
	}
	
	public static AbstractPJ2BundleActivator getActivator(){
		return activator;
	}
}
