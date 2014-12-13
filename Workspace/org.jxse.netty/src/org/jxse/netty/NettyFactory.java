package org.jxse.netty;

import net.jxta.impl.endpoint.netty.NettyTransport;
import net.jxta.module.AbstractModuleFactory;

public class NettyFactory extends AbstractModuleFactory<NettyTransport> {

	private static final String S_HTTP_NETTY_IDENTIFIER = "net.jxta.impl.endpoint.netty.NettyTransport";
	private static final String S_HTTP_NETTY_DESCRIPTION = " Reference Implementation of the TCP Message Transport";
	private static final String S_HTTP_NETTY_MODULE_SPEC_ID = "urn:jxta:uuid-deadbeefdeafbabafeedbabe000000090106";

	public NettyFactory() {
		super( S_HTTP_NETTY_IDENTIFIER, S_HTTP_NETTY_DESCRIPTION);
	}

	@Override
	public boolean init(String provider) {
		super.setModuleSpecID( S_HTTP_NETTY_MODULE_SPEC_ID );
		return super.init(provider);
	}

	@Override
	public NettyTransport createModule() {
		return new NettyTransport();
	}
}