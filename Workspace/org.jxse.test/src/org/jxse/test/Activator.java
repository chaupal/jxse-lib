package org.jxse.test;

import net.jxse.practical.jxta.PracticalJxta2Builder;
import net.jxse.practical.jxta.PJ2Examples;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator{

	public static final String S_BUNDLE_ID = "org.jxse.test";
	
	private PracticalJxta2Builder builder;
	
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
		builder = new PracticalJxta2Builder( S_BUNDLE_ID, PJ2Examples.Examples.B_RendezVous_Jack);
		builder.start(bundleContext);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		builder.stop(bundleContext);
		builder = null;
		Activator.context = null;
	}
}