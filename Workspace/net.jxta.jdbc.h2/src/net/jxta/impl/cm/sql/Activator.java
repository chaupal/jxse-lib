package net.jxta.impl.cm.sql;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator {

	private static Activator plugin;
	private ServiceTracker<BundleContext,LogService> logServiceTracker;
	private LogService logService;
	
	@Override
	public void start(BundleContext context) throws Exception {
		plugin = this;
		logServiceTracker = 
			new ServiceTracker<BundleContext,LogService>(context, LogService.class.getName(), null);
		logServiceTracker.open();
		logService = logServiceTracker.getService();

		if(logService != null)
			logService.log(LogService.LOG_INFO, "Logging service started");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		if(logService != null)
			logService.log(LogService.LOG_INFO, "Logging service Stopped");
		
		// close the service tracker
		logServiceTracker.close();
		logServiceTracker = null;
		plugin = null;
	}

	public LogService getLog(){
		return this.logService;
	}
	
	public static Activator getDefault(){
		return plugin;
	}
}
