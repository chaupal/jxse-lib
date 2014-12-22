package net.jxse.osgi.boot.factory;

import net.jxta.impl.platform.ShadowPeerGroup;
import net.jxta.impl.platform.StdPeerGroup;
import net.jxta.module.AbstractModuleBuilder;
import net.jxta.protocol.ModuleImplAdvertisement;

public class ShadowPeerGroupBuilder extends AbstractModuleBuilder<ShadowPeerGroup> {

	public static final String S_DESCRIPTION = "Default Network PeerGroup Reference Implementation";
	public static final String S_IDENTIFIER = "net.jxta.impl.platform.ShadowPeerGroup";
	public static final String S_MODULE_SPEC = "urn:jxta:uuid-deadbeefdeafbabafeedbabe000000010206";
	
	public ShadowPeerGroupBuilder() {
		super( S_IDENTIFIER, S_DESCRIPTION );
	}

	@Override
	public ShadowPeerGroup onBuildModule() {
		return new ShadowPeerGroup();
	}

	/**
	 * We use this construction for impl advs, because of startup issues
	 * @return
	 */
	@Override
	public ModuleImplAdvertisement getModuleImplAdvertisement() {
		if( super.getModuleImplAdvertisement() == null )
			super.setModuleImplAdvertisement( ShadowPeerGroup.getDefaultModuleImplAdvertisement() );
		return super.getModuleImplAdvertisement();
	}

	@Override
	public boolean init() {
		super.setModuleSpecID( S_MODULE_SPEC );
		//super.addDependency( StdPeerGroup.getDefaultModuleImplAdvertisement() );
		return super.init();
	}
}
