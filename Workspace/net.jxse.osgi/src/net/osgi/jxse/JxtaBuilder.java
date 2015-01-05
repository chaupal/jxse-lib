package net.osgi.jxse;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import net.jxta.impl.loader.RefJxtaLoader;
import net.jxta.impl.modulemanager.AbstractImplAdvModuleDescriptor;
import net.jxta.impl.modulemanager.AbstractJxtaModuleDescriptor;
import net.jxta.impl.modulemanager.AbstractModuleBuilder;
import net.jxta.impl.modulemanager.IJxtaModuleBuilder;
import net.jxta.module.IJxtaModuleDescriptor;
import net.jxta.module.IModuleDescriptor;
import net.jxta.peergroup.core.Module;
import net.jxta.peergroup.core.ModuleSpecID;
import net.jxta.protocol.ModuleImplAdvertisement;

public class JxtaBuilder extends AbstractModuleBuilder<Module> implements IJxtaModuleBuilder<Module>{

	private static String S_WRN_DESCRIPTER_NOT_REGISTERED = "THe descriptor is not registered because the class was not found on the class path: ";
	
	private Logger logger = Logger.getLogger( JxtaBuilder.class.getName() );
	
	public JxtaBuilder() {
		this.prepare();
	}
	
	protected void prepare(){
		String hashHex = Integer.toString( this.hashCode(), 16);
		URL url = JxtaBuilder.class.getResource( "/" + RefJxtaLoader.S_RESOURCE_LOCATION );
		Collection<ModuleImplAdvertisement> implAdvs = RefJxtaLoader.locateModuleImplementations( hashHex, url );
		for( ModuleImplAdvertisement implAdv: implAdvs ){
			try {
				if( Class.forName(implAdv.getCode(), false, JxtaBuilder.class.getClassLoader()) != null )
					super.addDescriptor( new ImplAdvDescriptor( implAdv ));
			} catch (ClassNotFoundException e) {
				logger.warning( S_WRN_DESCRIPTER_NOT_REGISTERED + implAdv.getCode());
			}
		}
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

	@Override
	public Class<? extends Module> getRepresentedClass(
			IModuleDescriptor descriptor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Module onBuildModule(IModuleDescriptor descriptor) {
		return null;
	}
	
	/**
	 * Create a descriptor from the given impl advertisement
	 * @author Kees
	 *
	 */
	private static class ImplAdvDescriptor extends AbstractImplAdvModuleDescriptor{

		private static final String VERSION = "2.8.0";
		
		protected ImplAdvDescriptor(ModuleImplAdvertisement implAdv ) {
			super(implAdv, VERSION, getResource( implAdv ));
		}

		protected static URL getResource( ModuleImplAdvertisement implAdv ) {
			return AbstractJxtaModuleDescriptor.getResource( JxtaBuilder.class, implAdv.getCode());
		}		
	}
}
