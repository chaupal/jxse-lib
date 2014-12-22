package net.jxse.osgi.boot.factory;

import net.jxta.impl.platform.StdPeerGroup;
import net.jxta.module.AbstractModuleBuilder;
import net.jxta.protocol.ModuleImplAdvertisement;

public class StdPeerGroupBuilder extends AbstractModuleBuilder<StdPeerGroup> {

	public static final String S_DESCRIPTION = "General Purpose Peer Group Implementation";
	public static final String S_IDENTIFIER  = "net.jxta.impl.platform.StdPeerGroup";
	public static final String S_MODULE_SPEC = "urn:jxta:uuid-deadbeefdeafbabafeedbabe000000010306";
	
	public StdPeerGroupBuilder() {
		super( S_IDENTIFIER, S_DESCRIPTION );
	}

	@Override
	public StdPeerGroup onBuildModule() {
		return new StdPeerGroup();
	}

	/**
	 * We use this construction for impl advs, because of startup issues
	 * @return
	 */
	@Override
	public ModuleImplAdvertisement getModuleImplAdvertisement() {
		if( super.getModuleImplAdvertisement() == null )
			super.setModuleImplAdvertisement( StdPeerGroup.getDefaultModuleImplAdvertisement() );
		return super.getModuleImplAdvertisement();
	}

	@Override
	public boolean init() {
		super.setModuleSpecID( S_MODULE_SPEC );
		return super.init();
	}
}
