package net.jxta.jdbc.derby.service;

import net.jxta.impl.cm.sql.DerbyAdvertisementCache;
import net.jxta.impl.cm.sql.JdbcDriverProxy;

import org.eclipselabs.osgi.ds.broker.service.AbstractPalaver;
import org.eclipselabs.osgi.ds.broker.service.AbstractProvider;

public class JdbcProxyProvider extends AbstractProvider<String, String, JdbcDriverProxy<DerbyAdvertisementCache>> {

	private JdbcDriverProxy<DerbyAdvertisementCache> proxy;
	
	private static final String S_DERBY_DRIVER_STRING = "org.apache.derby.jdbc.EmbeddedDriver"; 
	
	JdbcProxyProvider() {
		super( new JxtaDriverPalaver());
		proxy = new JdbcDriverProxy<DerbyAdvertisementCache>( DerbyAdvertisementCache.class, S_DERBY_DRIVER_STRING );
	}
	
	public JdbcDriverProxy<DerbyAdvertisementCache> getJdbcProxy() {
		return proxy;
	}

	@Override
	protected void onDataReceived(String datum) {
		super.onDataReceived(datum);
		super.provide(this.proxy);
	}	
}

/**
 * The palaver contains the conditions for attendees to create an assembly. In this case, the attendees must
 * pass a string identifier (the package id) and provide a token that is equal
 * @author Kees
 *
 */
class JxtaDriverPalaver extends AbstractPalaver<String>{

	public static final String S_JXTA_JDBC_PROXY_ID = "net.jxse.shared.service.jdbc.proxy";
	public static final String S_IJXTA_TOKEN = "net.jxse.shared.token";
	
	protected JxtaDriverPalaver() {
		super(S_JXTA_JDBC_PROXY_ID);
	}

	@Override
	public String giveToken() {
		return S_IJXTA_TOKEN;
	}

	@Override
	public boolean confirm(Object token) {
		return ( token.equals(S_IJXTA_TOKEN ));
	}	
}