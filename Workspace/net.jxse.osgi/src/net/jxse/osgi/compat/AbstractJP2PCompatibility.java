package net.jxse.osgi.compat;

import java.util.ArrayList;
import java.util.Collection;

import net.jxse.osgi.compat.ICompatibilityListener.ChangeEvents;

public abstract class AbstractJP2PCompatibility<T extends Object> implements
		IJP2PCompatibility<T> {

	private IJxtaNode<T> root;
	private String identifier;
	
	private Collection<ICompatibilityListener> listeners;
	
	protected AbstractJP2PCompatibility( String identifier ) {
		super();
		this.identifier = identifier;
		listeners = new ArrayList<ICompatibilityListener>();
	}

	public void addListener( ICompatibilityListener listener ){
		this.listeners.add( listener );
	}

	public void removeListener( ICompatibilityListener listener ){
		this.listeners.remove( listener );
	}

	protected void notifyCompatChanged( ICompatibilityListener.ChangeEvents change ){
		CompatibilityEvent event = new CompatibilityEvent( this, change );
		for( ICompatibilityListener listener: listeners ){
			listener.notifyNodeChanged( event );
		}
	}

	@Override
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * Create the root node
	 * @param root
	 */
	public IJxtaNode<T> createRoot( T node ){
		root = new JxtaNode( node );
		this.notifyCompatChanged( ChangeEvents.NODE_ADDED);
		return root;
	}
	
	
	public IJxtaNode<T> getRoot() {
		return root;
	}

	private class JxtaNode implements IJxtaNode<T>{

		private T module;
		private Collection<IJxtaNode<T>> children;
		
		protected JxtaNode( T module ) {
			super();
			this.module = module;
			this.children = new ArrayList<IJxtaNode<T>>();
		}

		@Override
		public T getModule() {
			return module;
		}

		@Override
		public void addChild( IJxtaNode<T> child ){
			this.children.add( child );
			notifyCompatChanged( ChangeEvents.NODE_ADDED );
		}

		/**
		 * Add a module and return the corresponding node that is created
		 * @param child
		 */
		public IJxtaNode<T> addChild( T module ){
			IJxtaNode<T> child = new JxtaNode( module );
			this.addChild(child);
			return child;
		}

		@Override
		public void removeChild( IJxtaNode<T> child ){
			this.children.remove( child );
			notifyCompatChanged( ChangeEvents.NODE_REMOVED );
		}

		@SuppressWarnings("unchecked")
		@Override
		public T[] getChildren() {
			return (T[]) children.toArray( new Object[this.children.size()]);
		}
		
	}
}
