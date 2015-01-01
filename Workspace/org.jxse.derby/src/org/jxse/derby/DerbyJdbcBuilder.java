package org.jxse.derby;

import net.jxta.impl.modulemanager.AbstractJxtaModuleDescriptor;
import net.jxta.impl.modulemanager.AbstractModuleBuilder;
import net.jxta.impl.modulemanager.ModuleException;
import net.jxta.module.IModuleDescriptor;
import net.jxta.peergroup.core.Module;

public class DerbyJdbcBuilder extends AbstractModuleBuilder<Module> {

	public DerbyJdbcBuilder() {
		super.addDescriptor( new DerbyJDBCDescriptor() );
	}

	@Override
	public Module buildModule(IModuleDescriptor descriptor)
			throws ModuleException {
		return null;
	}

	/**
	 * Create the JDBC descriptor
	 * @author Kees
	 *
	 */
	private static class DerbyJDBCDescriptor extends AbstractJxtaModuleDescriptor{

		private static final String S_JDBC_DERBY_IDENTIFIER = "net.jxta.impl.endpoint.netty.NettyTransport";
		private static final String S_JDBC_DERBY_DESCRIPTION = " Reference Implementation of the TCP Message Transport";
		private static final String S_JDBC_DERBY_MODULE_SPEC_ID = "urn:jxta:uuid-deadbeefdeafbabafeedbabe000000090106";
		private static final String S_JDBC_DERBY_VERSION ="9.6.0"; 

		DerbyJDBCDescriptor() {
			super();
		}

		@Override
		public boolean onInitialised() {
			super.setIdentifier(S_JDBC_DERBY_IDENTIFIER);
			super.setRefClass( S_JDBC_DERBY_IDENTIFIER );
			super.setDescription( S_JDBC_DERBY_DESCRIPTION );
			super.setVersion( S_JDBC_DERBY_VERSION );
			super.setSpecID( S_JDBC_DERBY_MODULE_SPEC_ID );
			return true;
		}				
	}
}