package net.jxta.jdbc.derby.service;

import org.eclipselabs.osgi.ds.broker.service.AbstractAttendeeProviderComponent;


public class AttendeeComponent extends AbstractAttendeeProviderComponent {

	@Override
	protected void initialise() {
		super.addAttendee( new JdbcProxyProvider() );
	}

	
}
