package net.jxse.practical.jxta;

import java.util.ArrayList;
import java.util.Collection;

import net.osgi.jxse.message.IJxseMessagePrinter;
import net.osgi.jxse.message.IJxseMessagePrinter.MessageTypes;

public class Component {

	public static Collection<IJxseMessagePrinter> printers = new ArrayList<IJxseMessagePrinter>();
	
	public void activate(){ /*NOTHING */};
	public void deactivate(){ /*NOTHING */};
	
	public void addMessagePrinter( IJxseMessagePrinter printer ){
		printers.add( printer );
	}

	public void removeMessagePrinter( IJxseMessagePrinter printer ){
		printers.remove( printer );
	}
	
	/**
	 * Print the provided message. returns true if printers were found that accept the message,
	 * otherwise a false is returned
	 * @param type
	 * @param title
	 * @param message
	 * @return
	 */
	public static boolean printMessage( MessageTypes type, String title, String message ){
		if( printers.isEmpty() )
			return false;
		for( IJxseMessagePrinter printer: printers )
			printer.printMessage( type, title, message);
		return true;
	}

	/**
	 * Print the provided message. returns true if printers were found that accept the message,
	 * otherwise a false is returned
	 * @param title
	 * @param message
	 * @return
	 */
	public static int askQuestion( String title, String message ){
		if( printers.isEmpty() )
			return 0;
		int result = 0;
		for( IJxseMessagePrinter printer: printers ){
			result = printer.askQiestion( title, message);
			if( result != 0 )
				return result;
		}
		return result;
	}

}
