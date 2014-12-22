package net.jxse.osgi.boot.factory;

import net.jxta.impl.platform.Platform;
import net.jxta.module.AbstractModuleBuilder;
import net.jxta.protocol.ModuleImplAdvertisement;

public class PlatformBuilder extends AbstractModuleBuilder<Platform> {

	public static final String S_DESCRIPTION = "Standard World PeerGroup Reference Implementation";
	public static final String S_IDENTIFIER = "net.jxta.impl.platform.Platform";
	public static final String S_MODULE_SPEC = "urn:jxta:uuid-deadbeefdeafbabafeedbabe000000010106";

	public PlatformBuilder() {
		super( S_IDENTIFIER, S_DESCRIPTION );
	}

	@Override
	public boolean init() {
		super.setModuleSpecID( S_MODULE_SPEC );
		//super.addDependency( ShadowPeerGroup.getDefaultModuleImplAdvertisement() );
		return super.init();
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
	public Platform onBuildModule() {
		Platform platform = null;
		try {
			Class<Platform> clss = Platform.class;
			platform = clss.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return platform;
	}
}
