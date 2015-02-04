package org.jxse.test;

import net.jxse.osgi.AbstractJxseBundleComponent;
import net.jxse.osgi.message.IJxseMessagePrinter;

public class Component extends AbstractJxseBundleComponent implements IJxseMessagePrinter{

	public Component() {
		super( Activator.getActivator() );
	}

	@Override
	public void printMessage(String[] message) {
		StringBuffer buffer = new StringBuffer();
		for( String str: message )
			buffer.append( str + "\n");
		System.out.println( buffer.toString());
	}

	@Override
	public void printMessage(MessageTypes type, String title, String message) {
		System.out.println( type + ":" + title + "\n\t"+ message );
	}

	@Override
	public int askQiestion(String title, String message) {
		System.out.println( "QUESTION?:" + title + "\n\t"+ message );
		return 1;
	}
}