package net.jxse.osgi.platform;

import java.net.URL;

import org.osgi.framework.Bundle;

import net.jxta.impl.modulemanager.AbstractJxtaModuleDescriptor;
import net.jxta.impl.modulemanager.AbstractModuleBuilder;
import net.jxta.impl.modulemanager.IJxtaModuleBuilder;
import net.jxta.impl.platform.Platform;
import net.jxta.impl.platform.ShadowPeerGroup;
import net.jxta.impl.platform.StdPeerGroup;
import net.jxta.module.IJxtaModuleDescriptor;
import net.jxta.module.IModuleDescriptor;
import net.jxta.peergroup.core.Module;
import net.jxta.protocol.ModuleImplAdvertisement;

public class PlatformBuilder extends AbstractModuleBuilder<Module> implements IJxtaModuleBuilder<Module>{

	private static final String S_BUNDLE_ID = "net.jxse.osgi.platform";
	private static final String S_URL_BASE = "/bin/net/jxta/impl/platform/";
	
	public PlatformBuilder() {
		super.addDescriptor( new PlatformDescriptor() );
		super.addDescriptor( new ShadowPeerGroupDescriptor() );
		super.addDescriptor( new StandardPeerGroupDescriptor() );
	}
	
	@Override
	protected boolean onInitBuilder(IModuleDescriptor descriptor) {
		if( !descriptor.isInitialised())
			descriptor.init();
		return descriptor.isInitialised();
	}
	
	@Override
	public IJxtaModuleDescriptor getDescriptor(ModuleImplAdvertisement adv) {
		for( IModuleDescriptor descriptor: super.getSupportedDescriptors() ){
			if( !( descriptor instanceof IJxtaModuleDescriptor ))
				continue;
			IJxtaModuleDescriptor jd = (IJxtaModuleDescriptor) descriptor;
			if( adv.equals( jd.getModuleImplAdvertisement()))
				return jd;
		}
		return null;
	}

	public Class<? extends Module> getRepresentedClass( IModuleDescriptor descriptor ){
		if( descriptor instanceof PlatformDescriptor )
			return Platform.class;
		if( descriptor instanceof ShadowPeerGroupDescriptor )
			return ShadowPeerGroup.class;
		if( descriptor instanceof StandardPeerGroupDescriptor )
			return StdPeerGroup.class;
		return null;
		
	}
	
	@Override
	public Module onBuildModule(IModuleDescriptor descriptor) {
		if( descriptor instanceof PlatformDescriptor ){
			PlatformDescriptor pd = (PlatformDescriptor) descriptor;
			pd.prepare();
			return buildPlatform();
		}
		if( descriptor instanceof ShadowPeerGroupDescriptor ){
			ShadowPeerGroupDescriptor pd = (ShadowPeerGroupDescriptor) descriptor;
			pd.prepare();
			return buildPlatform();
		}
		if( descriptor instanceof StandardPeerGroupDescriptor ){
			StandardPeerGroupDescriptor pd = (StandardPeerGroupDescriptor) descriptor;
			pd.prepare();
			return new StdPeerGroup();
		}
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

		/**
		 * Get the resource URL
		 * @return
		 */
		public URL getResourceURL(){
			Bundle bundle = org.eclipse.core.runtime.Platform.getBundle( S_BUNDLE_ID );
			String str = S_URL_BASE + "Platform.class";// + Platform.class.getPackage().getName() + "/";
			URL url = bundle.getEntry( str);
			return url;
		}

		protected void prepare(){
			super.setIdentifier(S_IDENTIFIER);
			super.setRefClass( S_IDENTIFIER );
			super.setDescription( S_DESCRIPTION );
			super.setVersion( S_VERSION );
			super.setSpecID( S_MODULE_SPEC_ID );
		}

		@Override
		public boolean onInitialised() {
			super.setImplAdv( Platform.getDefaultModuleImplAdvertisement() );
			return true;
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

		/**
		 * Get the resource URL
		 * @return
		 */
		public URL getResourceURL(){
			Bundle bundle = org.eclipse.core.runtime.Platform.getBundle( S_BUNDLE_ID );
			String str = S_URL_BASE + "ShadowPeerGroup.class";// + Platform.class.getPackage().getName() + "/";
			URL url = bundle.getEntry( str);
			return url;
		}

		protected void prepare(){
			super.setIdentifier(S_IDENTIFIER);
			super.setRefClass( S_IDENTIFIER );
			super.setDescription( S_DESCRIPTION );
			super.setVersion( S_VERSION );
			super.setSpecID( S_MODULE_SPEC_ID );
		}


		@Override
		public boolean onInitialised() {
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

		/**
		 * Get the resource URL
		 * @return
		 */
		public URL getResourceURL(){
			Bundle bundle = org.eclipse.core.runtime.Platform.getBundle( S_BUNDLE_ID );
			String str = S_URL_BASE + "StdPeerGroup.class";// + Platform.class.getPackage().getName() + "/";
			URL url = bundle.getEntry( str);
			return url;
		}

		protected void prepare(){
			super.setIdentifier(S_IDENTIFIER);
			super.setRefClass( S_IDENTIFIER );
			super.setDescription( S_DESCRIPTION );
			super.setVersion( S_VERSION );
			super.setSpecID( S_MODULE_SPEC_ID );
		}


		@Override
		public boolean onInitialised() {
			super.setImplAdv( StdPeerGroup.getDefaultModuleImplAdvertisement() );
			return true;
		}

	}
}