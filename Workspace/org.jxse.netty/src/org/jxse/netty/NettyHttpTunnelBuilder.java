package org.jxse.netty;

import net.jxta.impl.endpoint.netty.NettyTransport;
import net.jxta.module.AbstractModuleBuilder;

public class NettyHttpTunnelBuilder extends AbstractModuleBuilder<NettyTransport> {

	private static final String S_HTTP_NETTY_IDENTIFIER = "net.jxta.impl.endpoint.netty.http.NettyHttpTunnelTransport";
	private static final String S_HTTP_NETTY_DESCRIPTION = " HTTP tunneling message transport";
	private static final String S_HTTP_NETTY_MODULE_SPEC_ID = "urn:jxta:uuid-E549DB3BCBCF4789A392B6100B78CC55F127AD1AADF0443ABF6FBDFD7909876906";

	public NettyHttpTunnelBuilder() {
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