package net.jxse.jetty;

import java.util.ArrayList;
import java.util.Collection;

import net.jxta.impl.endpoint.servlethttp.ServletHttpTransportImpl;
import net.jxta.impl.modulemanager.AbstractJxtaModuleBuilder;
import net.jxta.impl.modulemanager.AbstractJxtaModuleDescriptor;
import net.jxta.module.IJxtaModuleBuilder;
import net.jxta.module.IJxtaModuleDescriptor;
import net.jxta.module.IModuleDescriptor;
import net.jxta.peergroup.core.ModuleSpecID;
import net.jxta.protocol.ModuleImplAdvertisement;

public class JettyHttpBuilder extends AbstractJxtaModuleBuilder<ServletHttpTransportImpl> implements IJxtaModuleBuilder<ServletHttpTransportImpl>{

	public JettyHttpBuilder() {
		super.addDescriptor( new JettyHttpDescriptor());
	}

	@Override
	public ServletHttpTransportImpl onBuildModule(IModuleDescriptor descriptor){
		return new ServletHttpTransportImpl();
	}

	@Override
	protected boolean onInitBuilder(IModuleDescriptor descriptor) {
		return true;
	}

	/**
	 * Create the descriptor
	 * @author Kees
	 *
	 */
	private static class JettyHttpDescriptor extends AbstractJxtaModuleDescriptor{

		private static final String S_HTTP_JETTY_IDENTIFIER = "net.jxta.impl.servlethttp.jetty.reference";
		private static final String S_HTTP_JETTY_DESCRIPTION = " Reference Implementation of the HTTP Message Transport";
		private static final String S_HTTP_JETTY_MODULE_SPEC_ID = "urn:jxta:uuid-deadbeefdeafbabafeedbabe0000000A0106";
		private static final String S_HTTP_JETTY_IMPL_CLASS ="net.jxta.impl.endpoint.servlethttp.ServletHttpTransportImpl"; 
		private static final String S_HTTP_JETTY_VERSION ="8.0.0"; 

		JettyHttpDescriptor() {
			super();
		}

		@Override
		public void prepare() {
			super.setIdentifier(S_HTTP_JETTY_IDENTIFIER);
			super.setRefClass( S_HTTP_JETTY_IMPL_CLASS);
			super.setDescription( S_HTTP_JETTY_DESCRIPTION );
			super.setVersion( S_HTTP_JETTY_VERSION );
			super.setSpecID( S_HTTP_JETTY_MODULE_SPEC_ID );
		}
		
		@Override
		public boolean onInitialised() {
			return true;
		}
		
	}

	public IJxtaModuleDescriptor[] getDescriptors(ModuleSpecID specID) {
		Collection<IJxtaModuleDescriptor> results = new ArrayList<IJxtaModuleDescriptor>();
		for( IModuleDescriptor descriptor: super.getSupportedDescriptors() ){
			if( !( descriptor instanceof IJxtaModuleDescriptor ))
				continue;
			IJxtaModuleDescriptor jdesc = (IJxtaModuleDescriptor) descriptor;
			if( specID.equals( jdesc.getModuleSpecID() ))
				results.add( jdesc );
		}
		return results.toArray( new IJxtaModuleDescriptor[ results.size()]);
	}

	public IJxtaModuleDescriptor getDescriptor(ModuleImplAdvertisement implAdv) {
		for( IModuleDescriptor descriptor: super.getSupportedDescriptors() ){
			if( !( descriptor instanceof IJxtaModuleDescriptor ))
				continue;
			IJxtaModuleDescriptor jdesc = (IJxtaModuleDescriptor) descriptor;
			if( implAdv.equals( jdesc.getModuleImplAdvertisement() ))
				return jdesc;
		}
		return null;
	}

	@Override
	public Class<? extends ServletHttpTransportImpl> getRepresentedClass(
			IModuleDescriptor descriptor) {
		return ServletHttpTransportImpl.class;
	}
}