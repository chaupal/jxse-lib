package org.jxse.jetty;

import net.jxta.impl.endpoint.servlethttp.ServletHttpTransportImpl;
import net.jxta.impl.modulemanager.AbstractModuleBuilder;
import net.jxta.impl.modulemanager.AbstractJxtaModuleDescriptor;
import net.jxta.impl.modulemanager.ModuleException;
import net.jxta.module.IModuleDescriptor;

public class JettyHttpBuilder extends AbstractModuleBuilder<ServletHttpTransportImpl> {

	public JettyHttpBuilder() {
		super.addDescriptor( new JettyHttpDescriptor());
	}

	@Override
	public ServletHttpTransportImpl buildModule(IModuleDescriptor descriptor)
			throws ModuleException {
		return new ServletHttpTransportImpl();
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
		public boolean onInitialised() {
			super.setIdentifier(S_HTTP_JETTY_IDENTIFIER);
			super.setRefClass( S_HTTP_JETTY_IMPL_CLASS);
			super.setDescription( S_HTTP_JETTY_DESCRIPTION );
			super.setVersion( S_HTTP_JETTY_VERSION );
			super.setSpecID( S_HTTP_JETTY_MODULE_SPEC_ID );
			return true;
		}		
	}
}