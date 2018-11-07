package net.jxta.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.jxta.platform.JxtaLoader;

public class IOUtils {

	/**
	 * Close an input  stream
	 *
	 * @param stream OutputStream
	 * @deprecated: Use closequitely instead
	*/
	public static void closeQuietly( InputStream stream )
	{
	  Logger logger = Logger.getLogger( IOUtils.class.getName() );
	  if( stream == null )
	    return;
	
	  try{
	    stream.close();
	  }
	  catch( IOException ioex ){
	    logger.log( Level.SEVERE, ioex.getMessage(), ioex );
	  }
	}

	public static void closeQuietly(JxtaLoader loader) {
		  Logger logger = Logger.getLogger( IOUtils.class.getName() );
		  if( loader == null )
		    return;
		
		  try{
		    loader.close();
		  }
		  catch( IOException ioex ){
		    logger.log( Level.SEVERE, ioex.getMessage(), ioex );
		  }
	}

	public static void closeQuietly( ServerSocket socket) {
		  Logger logger = Logger.getLogger( IOUtils.class.getName() );
		  if( socket == null )
		    return;
		
		  try{
		    socket.close();
		  }
		  catch( IOException ioex ){
		    logger.log( Level.SEVERE, ioex.getMessage(), ioex );
		  }
	}

}
