package net.jxse.osgi.message;

import net.jxse.osgi.message.AbstractMessagePrinter;

public class SimpleMessagePrinter extends AbstractMessagePrinter{

	protected SimpleMessagePrinter( String identifier ) {
		super( identifier);
	}

	@Override
	public void printMessage(MessageTypes type, String title, String message) {
		System.out.println( type + ":" + title + "\n\t"+ message );
	}

	@Override
	public int askQuestion(String title, String message) {
		System.out.println( "QUESTION?:" + title + "\n\t"+ message );
		return 1;
	}
}