package org.jxse.netty;

import net.jxta.impl.endpoint.netty.NettyTransport;
import net.jxta.module.AbstractModuleBuilder;

public class NettyTcpBuilder extends AbstractModuleBuilder<NettyTransport> {

	private static final String S_HTTP_NETTY_IDENTIFIER = "net.jxta.impl.endpoint.netty.NettyTransport";
	private static final String S_HTTP_NETTY_DESCRIPTION = " Reference Implementation of the TCP Message Transport";
	private static final String S_HTTP_NETTY_MODULE_SPEC_ID = "urn:jxta:uuid-deadbeefdeafbabafeedbabe000000090106";

	public NettyTcpBuilder() {
		super( S_HTTP_NETTY_IDENTIFIER, S_HTTP_NETTY_DESCRIPTION);
	}

	@Override
	public boolean init() {
		super.setModuleSpecID( S_HTTP_NETTY_MODULE_SPEC_ID );
		return super.init();
	}

	@Override
	public NettyTransport onBuildModule() {
		return new NettyTransport();
	}
}