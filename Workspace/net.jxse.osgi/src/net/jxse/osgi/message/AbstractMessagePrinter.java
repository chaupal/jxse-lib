package net.jxse.osgi.message;

import net.jxse.osgi.message.IJxseMessagePrinter;

public abstract class AbstractMessagePrinter implements IJxseMessagePrinter{

	private String identifier;
	private int priority;

	protected AbstractMessagePrinter( String identifier) {
		this( identifier, 0 );
	}
	
	protected AbstractMessagePrinter( String identifier, int priority) {
		this.identifier = identifier;
		this.priority = priority;
	}

	@Override
	public void printMessage(String[] message) {
		StringBuffer buffer = new StringBuffer();
		for( String str: message )
			buffer.append( str + "\n");
		System.out.println( buffer.toString());
	}

	public void activate(){/* DO NOTHING */}
	
	public void deactivate(){ /* DO NOTHING */}
	
	@Override
	public int getPriority() {
		return this.priority;
	}

	@Override
	public String getIdentifier() {
		return this.identifier;
	}

	@Override
	public String toString() {
		return this.getIdentifier() + "<priority='" + this.getPriority() + "'>";
	}
}