package org.jxse.test;

import net.jxse.practical.jxta.AbstractPJ2Component;
import net.osgi.jxse.message.IJxseMessagePrinter;

public class Component extends AbstractPJ2Component implements IJxseMessagePrinter{

	public Component() {
		super( Examples.L_810_Adhoc_Pong );
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