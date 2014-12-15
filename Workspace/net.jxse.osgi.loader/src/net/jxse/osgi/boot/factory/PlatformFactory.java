package net.jxse.osgi.boot.factory;

import net.jxta.impl.platform.Platform;
import net.jxta.impl.platform.ShadowPeerGroup;
import net.jxta.impl.platform.StdPeerGroup;
import net.jxta.module.AbstractModuleFactory;
import net.jxta.protocol.ModuleImplAdvertisement;

public class PlatformFactory extends AbstractModuleFactory<Platform> {

	public static final String S_DESCRIPTION = "Standard World PeerGroup Reference Implementation";
	public static final String S_IDENTIFIER = "net.jxta.impl.platform.Platform";
	public static final String S_MODULE_SPEC = "urn:jxta:uuid-deadbeefdeafbabafeedbabe000000010106";

	public PlatformFactory() {
		super( S_IDENTIFIER, S_DESCRIPTION );
	}

	@Override
	public boolean init(String provider) {
		super.setModuleSpecID( S_MODULE_SPEC );
		super.addDependency( ShadowPeerGroup.getDefaultModuleImplAdvertisement() );
		return super.init(provider);
	}

	/**
	 * We use this construction for impl advs, because of startup issues
	 * @return
	 */
	@Override
	public ModuleImplAdvertisement getModuleImplAdvertisement() {
		if( super.getModuleImplAdvertisement() == null )
			super.setModuleImplAdvertisement( Platform.getDefaultModuleImplAdvertisement() );
		return super.getModuleImplAdvertisement();
	}

	
	@Override
	public Class<Platform> createModule() {
		return Platform.class;
	}
}
