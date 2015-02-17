package net.jxse.practical.jxta;

import org.osgi.framework.BundleContext;

import net.jxse.osgi.platform.activator.AbstractPlatformBundleActivator;

public class AbstractPJ2BundleActivator extends AbstractPlatformBundleActivator<Object> {

	protected AbstractPJ2BundleActivator( String bundle_id, PJ2Examples.Examples example ) {
		super( bundle_id, PJ2Examples.getJxseCompatible(example));
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		super.stop(bundleContext);
	}
	
	
}