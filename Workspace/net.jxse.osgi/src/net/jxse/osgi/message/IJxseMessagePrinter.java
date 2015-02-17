package net.jxse.osgi.message;

public interface IJxseMessagePrinter {

	public enum MessageTypes{
		INFO,
		QUESTION,
		WARNING,
		ERROR
	}

	public enum Answers{
		YES,
		NO
	}

	/**
	 * Print a message. Ideally this should convert to:
	 * - Message type, title and comment
	 * @param message
	 */
	public void printMessage( String[] message );

	/**
	 * Print a message. Ideally this should convert to:
	 * - Message type, title and comment
	 * @param message
	 */
	public void printMessage( MessageTypes type, String title, String message );

	/**
	 * Ask a question.
	 * @param message
	 */
	public int askQuestion( String title, String message );

}
