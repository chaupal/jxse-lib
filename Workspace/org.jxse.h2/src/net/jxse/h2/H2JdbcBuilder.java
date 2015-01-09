package net.jxse.h2;

import net.jxta.impl.modulemanager.AbstractJxtaModuleDescriptor;
import net.jxta.impl.modulemanager.AbstractModuleBuilder;
import net.jxta.module.IModuleDescriptor;
import net.jxta.peergroup.core.Module;

public class H2JdbcBuilder extends AbstractModuleBuilder<Module> {

	public H2JdbcBuilder() {
		super.addDescriptor( new H2JDBCDescriptor() );
	}

	@Override
	protected boolean onInitBuilder(IModuleDescriptor descriptor) {
		return false;
	}

	@Override
	public Module onBuildModule(IModuleDescriptor descriptor){
		return null;
	}

	/**
	 * Create the JDBC descriptor
	 * @author Kees
	 *
	 */
	private static class H2JDBCDescriptor extends AbstractJxtaModuleDescriptor{

		private static final String S_JDBC_H2_IDENTIFIER = "net.jxta.impl.endpoint.netty.NettyTransport";
		private static final String S_JDBC_H2_DESCRIPTION = " Reference Implementation of the TCP Message Transport";
		private static final String S_JDBC_H2_MODULE_SPEC_ID = "urn:jxta:uuid-deadbeefdeafbabafeedbabe000000090106";
		private static final String S_JDBC_H2_VERSION ="9.6.0"; 

		H2JDBCDescriptor() {
			super();
		}

		@Override
		protected void prepare(){
			super.setIdentifier(S_JDBC_H2_IDENTIFIER);
			super.setRefClass( S_JDBC_H2_IDENTIFIER );
			super.setDescription( S_JDBC_H2_DESCRIPTION );
			super.setVersion( S_JDBC_H2_VERSION );
			super.setSpecID( S_JDBC_H2_MODULE_SPEC_ID );
		}
		
		public boolean onInitialised() {
			return true;
		}
	}
}