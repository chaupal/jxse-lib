package net.osgi.jxse;

public interface IJP2PCompatibility<T extends Object> {

	/**
	 * Get the node which contains the relevant JXTA modules
	 * @return
	 */
	public IJxtaNode<T> getRoot();
	
	/**
	 * A standard JXTa application is usually run from main, so we allow this
	 * @param args
	 */
	public void main( String[] args);
	
	/**
	 * Deactivate the modules
	 */
	public void deactivate();
}
