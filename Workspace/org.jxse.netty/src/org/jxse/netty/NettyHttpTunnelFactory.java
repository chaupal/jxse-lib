package org.jxse.netty;

import net.jxta.impl.endpoint.netty.NettyTransport;
import net.jxta.module.AbstractModuleFactory;

public class NettyHttpTunnelFactory extends AbstractModuleFactory<NettyTransport> {

	private static final String S_HTTP_NETTY_IDENTIFIER = "net.jxta.impl.endpoint.netty.http.NettyHttpTunnelTransport";
	private static final String S_HTTP_NETTY_DESCRIPTION = " HTTP tunneling message transport";
	private static final String S_HTTP_NETTY_MODULE_SPEC_ID = "urn:jxta:uuid-E549DB3BCBCF4789A392B6100B78CC55F127AD1AADF0443ABF6FBDFD7909876906";

	public NettyHttpTunnelFactory() {
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