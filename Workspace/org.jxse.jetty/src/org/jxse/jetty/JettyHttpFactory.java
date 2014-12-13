package org.jxse.jetty;

import net.jxta.impl.endpoint.servlethttp.ServletHttpTransportImpl;
import net.jxta.module.AbstractModuleFactory;

public class JettyHttpFactory extends AbstractModuleFactory<ServletHttpTransportImpl> {

	private static final String S_HTTP_JETTY_IDENTIFIER = "net.jxta.impl.servlethttp.jetty.reference";
	private static final String S_HTTP_JETTY_DESCRIPTION = " Reference Implementation of the HTTP Message Transport";
	private static final String S_HTTP_JETTY_MODULE_SPEC_ID = "urn:jxta:uuid-deadbeefdeafbabafeedbabe0000000A0106";
	private static final String S_HTTP_JETTY_IMPL_CLASS ="net.jxta.impl.endpoint.servlethttp.ServletHttpTransportImpl"; 

	public JettyHttpFactory() {
		super( S_HTTP_JETTY_IDENTIFIER, S_HTTP_JETTY_DESCRIPTION);
	}

	@Override
	public boolean init(String provider) {
		super.setModuleSpecID( S_HTTP_JETTY_MODULE_SPEC_ID );
		super.setRepresentedClassName(S_HTTP_JETTY_IMPL_CLASS);
		return super.init(provider);
	}

	@Override
	public ServletHttpTransportImpl createModule() {
		return new ServletHttpTransportImpl();
	}
}