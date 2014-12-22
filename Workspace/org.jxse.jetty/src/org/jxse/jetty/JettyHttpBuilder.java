package org.jxse.jetty;

import net.jxta.impl.endpoint.servlethttp.ServletHttpTransportImpl;
import net.jxta.module.AbstractModuleBuilder;

public class JettyHttpBuilder extends AbstractModuleBuilder<ServletHttpTransportImpl> {

	private static final String S_HTTP_JETTY_IDENTIFIER = "net.jxta.impl.servlethttp.jetty.reference";
	private static final String S_HTTP_JETTY_DESCRIPTION = " Reference Implementation of the HTTP Message Transport";
	private static final String S_HTTP_JETTY_MODULE_SPEC_ID = "urn:jxta:uuid-deadbeefdeafbabafeedbabe0000000A0106";
	private static final String S_HTTP_JETTY_IMPL_CLASS ="net.jxta.impl.endpoint.servlethttp.ServletHttpTransportImpl"; 

	public JettyHttpBuilder() {
		super( S_HTTP_JETTY_IDENTIFIER, S_HTTP_JETTY_DESCRIPTION);
	}

	@Override
	public boolean init() {
		super.setModuleSpecID( S_HTTP_JETTY_MODULE_SPEC_ID );
		super.setRepresentedClassName(S_HTTP_JETTY_IMPL_CLASS);
		return super.init();
	}

	@Override
	public ServletHttpTransportImpl onBuildModule() {
		return new ServletHttpTransportImpl();
	}
}