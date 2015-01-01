package net.jxse.osgi.pltform.builder;

import net.jxta.impl.modulemanager.AbstractJxtaModuleDescriptor;
import net.jxta.impl.modulemanager.AbstractModuleBuilder;
import net.jxta.impl.modulemanager.ModuleException;
import net.jxta.impl.platform.Platform;
import net.jxta.impl.platform.ShadowPeerGroup;
import net.jxta.impl.platform.StdPeerGroup;
import net.jxta.module.IModuleDescriptor;
import net.jxta.peergroup.core.Module;
import net.jxta.protocol.ModuleImplAdvertisement;

public class PlatformBuilder extends AbstractModuleBuilder<Module> {

	public PlatformBuilder() {
		super.addDescriptor( new PlatformDescriptor() );
		super.addDescriptor( new ShadowPeerGroupDescriptor() );
		super.addDescriptor( new StandardPeerGroupDescriptor() );
	}
	
	@Override
	public Module buildModule(IModuleDescriptor descriptor)
			throws ModuleException {
		if(! super.canBuild(descriptor))
			return null;
		if( descriptor instanceof PlatformDescriptor )
			return buildPlatform();
		if( descriptor instanceof ShadowPeerGroupDescriptor )
			return buildPlatform();
		if( descriptor instanceof PlatformDescriptor )
			return new ShadowPeerGroup();
		if( descriptor instanceof StandardPeerGroupDescriptor )
			return new StdPeerGroup();
		return null;
	}

	private final Platform buildPlatform() {
		Platform platform = null;
		try {
			Class<Platform> clss = Platform.class;
			platform = clss.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return platform;
	}
	
	/**
	 * Create the Platform descriptor
	 * @author Kees
	 *
	 */
	private static class PlatformDescriptor extends AbstractJxtaModuleDescriptor{

		public static final String S_DESCRIPTION = "Standard World PeerGroup Reference Implementation";
		public static final String S_IDENTIFIER = "net.jxta.impl.platform.Platform";
		public static final String S_MODULE_SPEC_ID = "urn:jxta:uuid-deadbeefdeafbabafeedbabe000000010106";
		private static final String S_VERSION ="2.8.0"; 

		PlatformDescriptor() {
			super();
		}

		@Override
		public boolean onInitialised() {
			super.setIdentifier(S_IDENTIFIER);
			super.setRefClass( S_IDENTIFIER );
			super.setDescription( S_DESCRIPTION );
			super.setVersion( S_VERSION );
			super.setSpecID( S_MODULE_SPEC_ID );
			super.setImplAdv( Platform.getDefaultModuleImplAdvertisement() );
			return true;
		}

		@Override
		public ModuleImplAdvertisement getModuleImplAdvertisement() {
				//if( super.getModuleImplAdvertisement() == null )
				return super.getModuleImplAdvertisement();
		}				
	}

	/**
	 * Create the Platform descriptor
	 * @author Kees
	 *
	 */
	private static class ShadowPeerGroupDescriptor extends AbstractJxtaModuleDescriptor{

		public static final String S_DESCRIPTION = "Default Network PeerGroup Reference Implementation";
		public static final String S_IDENTIFIER = "net.jxta.impl.platform.ShadowPeerGroup";
		public static final String S_MODULE_SPEC_ID = "urn:jxta:uuid-deadbeefdeafbabafeedbabe000000010206";
		private static final String S_VERSION ="2.8.0"; 

		ShadowPeerGroupDescriptor() {
			super();
		}

		@Override
		public boolean onInitialised() {
			super.setIdentifier(S_IDENTIFIER);
			super.setRefClass( S_IDENTIFIER );
			super.setDescription( S_DESCRIPTION );
			super.setVersion( S_VERSION );
			super.setSpecID( S_MODULE_SPEC_ID );
			super.setImplAdv( ShadowPeerGroup.getDefaultModuleImplAdvertisement() );
			return true;
		}
	}

	/**
	 * Create the Platform descriptor
	 * @author Kees
	 *
	 */
	private static class StandardPeerGroupDescriptor extends AbstractJxtaModuleDescriptor{

		public static final String S_DESCRIPTION = "General Purpose Peer Group Implementation";
		public static final String S_IDENTIFIER  = "net.jxta.impl.platform.StdPeerGroup";
		public static final String S_MODULE_SPEC_ID = "urn:jxta:uuid-deadbeefdeafbabafeedbabe000000010306";
		private static final String S_VERSION ="2.8.0"; 

		StandardPeerGroupDescriptor() {
			super();
		}

		@Override
		public boolean onInitialised() {
			super.setIdentifier(S_IDENTIFIER);
			super.setRefClass( S_IDENTIFIER );
			super.setDescription( S_DESCRIPTION );
			super.setVersion( S_VERSION );
			super.setSpecID( S_MODULE_SPEC_ID );
			super.setImplAdv( StdPeerGroup.getDefaultModuleImplAdvertisement() );
			return true;
		}
	}
}