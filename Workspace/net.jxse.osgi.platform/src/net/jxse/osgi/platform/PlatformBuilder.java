package net.jxse.osgi.platform;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import net.jxta.impl.loader.RefJxtaLoader;
import net.jxta.impl.modulemanager.AbstractJxtaModuleDescriptor;
import net.jxta.impl.modulemanager.AbstractJxtaModuleBuilder;
import net.jxta.impl.modulemanager.ImplAdvModuleDescriptor;
import net.jxta.module.IJxtaModuleBuilder;
import net.jxta.impl.modulemanager.ImplAdvertisementComparable;
import net.jxta.impl.platform.Platform;
import net.jxta.impl.platform.ShadowPeerGroup;
import net.jxta.impl.peergroup.StdPeerGroup;
import net.jxta.module.IJxtaModuleDescriptor;
import net.jxta.module.IModuleDescriptor;
import net.jxta.peergroup.core.Module;
import net.jxta.peergroup.core.ModuleSpecID;
import net.jxta.protocol.ModuleImplAdvertisement;
import net.jxta.util.cardinality.Cardinality.Denominator;

public class PlatformBuilder extends AbstractJxtaModuleBuilder<Module> implements IJxtaModuleBuilder<Module>{

	//These are skipped
	private enum PlatformModules{
		STD_PEERGROUP,
		SHADOW_PEERGROUP,
		PLATFORM;
		
		@Override
		public String toString() {
			String str = super.toString();
			switch( this ){
			case STD_PEERGROUP:
				str = "net.jxta.impl.platform.StdPeerGroup";
				break;
			case SHADOW_PEERGROUP:
				str = "net.jxta.impl.platform.ShadowPeerGroup";
				break;
			case PLATFORM:
				str = "net.jxta.impl.platform.Platform";
				break;
			default:
				break;
			}
			return str;
		}
	
		/**
		 * Returns true if the code should be included in the given module
		 * @param code
		 * @return
		 */
		public static boolean isPlatform( String code ){
			for( PlatformModules sm: values() ){
				if( !sm.toString().equals( code ))
					return true;
			}
			return false;
		}
	}

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
			ImplAdvertisementComparable comp = new ImplAdvertisementComparable( adv );
			if( comp.compareTo( jd.getModuleImplAdvertisement()) == 0)
				return jd;
		}
		return null;
	}

	public IJxtaModuleDescriptor[] getDescriptors(ModuleSpecID specID) {
		Collection<IJxtaModuleDescriptor> results = new ArrayList<IJxtaModuleDescriptor>();
		for( IModuleDescriptor descriptor: super.getSupportedDescriptors() ){
			if( !( descriptor instanceof IJxtaModuleDescriptor ))
				continue;
			IJxtaModuleDescriptor jdesc = (IJxtaModuleDescriptor) descriptor;
			if( specID.equals( jdesc.getModuleSpecID() ))
				results.add( jdesc );
		}
		return results.toArray( new IJxtaModuleDescriptor[ results.size()]);
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
			return new ShadowPeerGroup();
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
	private static class StandardPeerGroupDescriptor extends AbstractJxtaModuleDescriptor{

		public static final String S_DESCRIPTION = "General Purpose Peer Group Implementation";
		public static final String S_IDENTIFIER  = "net.jxta.impl.platform.StdPeerGroup";
		public static final String S_MODULE_SPEC_ID = "urn:jxta:uuid-deadbeefdeafbabafeedbabe000000010306";
		private static final String S_VERSION ="2.8.0"; 

		StandardPeerGroupDescriptor() {
			super( Denominator.ONE );
		}

		protected void prepare(){
			super.setIdentifier(S_IDENTIFIER);
			super.setRefClass( S_IDENTIFIER );
			super.setDescription( S_DESCRIPTION );
			super.setVersion( S_VERSION );
			super.setSpecID( S_MODULE_SPEC_ID );

			//Load the dependencies
			URL url = PlatformBuilder.class.getResource( "/" + RefJxtaLoader.S_RESOURCE_LOCATION );
			String hashHex = Integer.toString( this.hashCode(), 16);
			Collection<ModuleImplAdvertisement> implAdvs = RefJxtaLoader.locateModuleImplementations( hashHex, url );
			for( ModuleImplAdvertisement implAdv: implAdvs ){
				if( PlatformModules.isPlatform( implAdv.getCode() ))					
					continue;
				super.addDependency( new ImplAdvDescriptor( implAdv ));
			}
		}

		@Override
		public boolean onInitialised() {
			super.setImplAdv( StdPeerGroup.getDefaultModuleImplAdvertisement() );
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
			super( Denominator.ONE );
		}

		protected void prepare(){
			super.setIdentifier(S_IDENTIFIER);
			super.setRefClass( S_IDENTIFIER );
			super.setDescription( S_DESCRIPTION );
			super.setVersion( S_VERSION );
			super.setSpecID( S_MODULE_SPEC_ID );

			//Load the dependencies
			URL url = PlatformBuilder.class.getResource( "/" + RefJxtaLoader.S_RESOURCE_LOCATION );
			String hashHex = Integer.toString( this.hashCode(), 16);
			Collection<ModuleImplAdvertisement> implAdvs = RefJxtaLoader.locateModuleImplementations( hashHex, url );
			for( ModuleImplAdvertisement implAdv: implAdvs ){
				if( PlatformModules.isPlatform( implAdv.getCode() )){					
					if( !StandardPeerGroupDescriptor.S_IDENTIFIER.equals(implAdv.getCode()))
						continue;
				}
				super.addDependency( new ImplAdvDescriptor( implAdv ));
			}
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
	private static class PlatformDescriptor extends AbstractJxtaModuleDescriptor{

		public static final String S_DESCRIPTION = "Standard World PeerGroup Reference Implementation";
		public static final String S_IDENTIFIER = "net.jxta.impl.platform.Platform";
		public static final String S_MODULE_SPEC_ID = "urn:jxta:uuid-deadbeefdeafbabafeedbabe000000010106";
		private static final String S_VERSION ="2.8.0"; 

		PlatformDescriptor() {
			super( Denominator.ONE );
		}

		protected void prepare(){
			super.setIdentifier(S_IDENTIFIER);
			super.setRefClass( S_IDENTIFIER );
			super.setDescription( S_DESCRIPTION );
			super.setVersion( S_VERSION );
			super.setSpecID( S_MODULE_SPEC_ID );

			//Load the dependencies
			URL url = PlatformBuilder.class.getResource( "/" + RefJxtaLoader.S_RESOURCE_LOCATION );
			String hashHex = Integer.toString( this.hashCode(), 16);
			Collection<ModuleImplAdvertisement> implAdvs = RefJxtaLoader.locateModuleImplementations( hashHex, url );
			for( ModuleImplAdvertisement implAdv: implAdvs ){
				if( PlatformModules.isPlatform( implAdv.getCode() )){					
					if( !StandardPeerGroupDescriptor.S_IDENTIFIER.equals(implAdv.getCode()))
						continue;
					if( !ShadowPeerGroupDescriptor.S_IDENTIFIER.equals(implAdv.getCode()))
						continue;
				}
				super.addDependency( new ImplAdvDescriptor( implAdv ));
			}

		}

		@Override
		public boolean onInitialised() {
			super.setImplAdv( Platform.getDefaultModuleImplAdvertisement() );
			return true;
		}		
	}


	/**
	 * Create a descriptor from the given impl advertisement
	 * @author Kees
	 *
	 */
	private static class ImplAdvDescriptor extends ImplAdvModuleDescriptor{
		
		protected ImplAdvDescriptor(ModuleImplAdvertisement implAdv ) {
			super(implAdv);
		}
	}

}