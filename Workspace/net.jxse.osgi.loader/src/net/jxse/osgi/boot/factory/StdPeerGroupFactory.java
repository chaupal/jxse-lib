package net.jxse.osgi.boot.factory;

import net.jxta.impl.platform.StdPeerGroup;
import net.jxta.module.AbstractModuleFactory;
import net.jxta.protocol.ModuleImplAdvertisement;

public class StdPeerGroupFactory extends AbstractModuleFactory<StdPeerGroup> {

	public static final String S_DESCRIPTION = "General Purpose Peer Group Implementation";
	public static final String S_IDENTIFIER  = "net.jxta.impl.platform.StdPeerGroup";
	public static final String S_MODULE_SPEC = "urn:jxta:uuid-deadbeefdeafbabafeedbabe000000010306";
	
	public StdPeerGroupFactory() {
		super( S_IDENTIFIER, S_DESCRIPTION );
	}

	@Override
	public Class<StdPeerGroup> createModule() {
		return StdPeerGroup.class;
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
	public boolean init(String provider) {
		super.setModuleSpecID( S_MODULE_SPEC );
		return super.init(provider);
	}
}
