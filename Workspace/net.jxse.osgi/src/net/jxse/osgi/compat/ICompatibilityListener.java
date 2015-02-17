package net.jxse.osgi.compat;

public interface ICompatibilityListener {

	public enum ChangeEvents{
		NODE_ADDED,
		NODE_REMOVED
	}
	
	public void notifyNodeChanged( CompatibilityEvent event );
}
