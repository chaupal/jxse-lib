package org.jxse.test;

import net.jxse.practical.jxta.AbstractPJ2BundleActivator;
import net.jxse.practical.jxta.PJ2Examples;

import org.osgi.framework.BundleContext;

public class Activator extends AbstractPJ2BundleActivator {

	public static final String S_BUNDLE_ID = "org.jxse.test";
	
	private static AbstractPJ2BundleActivator activator;
	
	public Activator() {
		super( S_BUNDLE_ID, PJ2Examples.Examples.B_RendezVous_Jack);
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
		super.start(bundleContext);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		super.stop(bundleContext);
		activator = null;
		Activator.context = null;
	}
	
	public static AbstractPJ2BundleActivator getActivator(){
		return activator;
	}
}
