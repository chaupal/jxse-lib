package net.jxse.osgi.platform.activator;

import java.util.logging.Logger;

import org.eclipse.core.runtime.Platform;

import net.jxse.osgi.compat.AbstractJxseBundleActivator;
import net.jxse.osgi.compat.IJP2PCompatibility;
import net.jxta.impl.modulemanager.AbstractJxtaModuleDescriptor;
import net.jxta.module.IModuleDescriptor;
import net.jxta.util.cardinality.Cardinality.Denominator;

public class AbstractPlatformBundleActivator<T extends Object> extends AbstractJxseBundleActivator {

	private IJP2PCompatibility<T> compat;

	static Logger logger = Logger.getLogger( AbstractPlatformBundleActivator.class.getName());

	
	protected AbstractPlatformBundleActivator( String bundle_id, IModuleDescriptor descriptor, IJP2PCompatibility<T> compat ) {
		super( bundle_id, descriptor );
		this.compat = compat;
	}

	protected AbstractPlatformBundleActivator( String bundle_id, IJP2PCompatibility<T> compat ) {
		this( bundle_id, new PlatformDescriptor(), compat );
	}

	@Override
	protected void onRunJxse() {
		String[] args = Platform.getCommandLineArgs();
		compat.main( args );
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
		}

		@Override
		protected boolean onInitialised() {
			return true;
		}

	}

}