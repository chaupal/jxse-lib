package org.jxse.jetty;

import java.net.URI;

import net.jxta.document.Advertisement;
import net.jxta.impl.endpoint.servlethttp.ServletHttpTransportImpl;
import net.jxta.impl.protocol.PlatformConfig;
import net.jxta.module.IJxtaModuleFactory;
import net.jxta.module.IModuleFactory;
import net.jxta.peergroup.core.ModuleClassID;
import net.jxta.peergroup.core.ModuleSpecID;
import net.jxta.protocol.ModuleImplAdvertisement;

public class JettyHttpFactory implements IJxtaModuleFactory<ServletHttpTransportImpl> {

	private static final String S_HTTP_JETTY_IDENTIFIER = "net.jxta.impl.servlethttp.jetty.reference";
	private static final String S_HTTP_JETTY_DESCRIPTION = " Reference Implementation of the HTTP Message Transport";
	private static final String S_HTTP_JETTY_MODULE_CLASS_ID = "urn:jxta:uuid-deadbeefdeafbabafeedbabe0000000A0106";
	private static final String S_HTTP_JETTY_IMPL_CLASS ="net.jxta.impl.endpoint.servlethttp.ServletHttpTransportImpl"; 

	public void activate(){
		
	}

	public void deactivate(){
		
	}

	@Override
	public String getIdentifier() {
		return S_HTTP_JETTY_IDENTIFIER;
	}

	@Override
	public String getDescription() {
		return S_HTTP_JETTY_DESCRIPTION;
	}

	@Override
	public ModuleClassID getModuleClassID() {
		return null;
	}

	@Override
	public ModuleSpecID getModuleSpecID() {
		return ModuleSpecID.create(URI.create( S_HTTP_JETTY_MODULE_CLASS_ID ));
	}

	@Override
	public ModuleImplAdvertisement getModuleImplAdvertisement() {
		return null;
	}

	@Override
	public String getRepresentedClassName() {
		return S_HTTP_JETTY_IMPL_CLASS;
	}

	@Override
	public ServletHttpTransportImpl createModule() {
		return new ServletHttpTransportImpl();
	}

	@Override
	public Advertisement getAdvertisement(PlatformConfig config) {
		// TODO Auto-generated method stub
		return null;
	}
}
