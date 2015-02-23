package net.jxse.practical.jxta;

import org.osgi.framework.BundleContext;

import net.jxse.osgi.platform.activator.PlatformJxseBuilder;

public class PracticalJxta2Builder extends PlatformJxseBuilder<Object> {

	public PracticalJxta2Builder( String bundle_id, PJ2Examples.Examples example ) {
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