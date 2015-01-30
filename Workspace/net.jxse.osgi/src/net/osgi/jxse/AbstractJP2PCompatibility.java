package net.osgi.jxse;

import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractJP2PCompatibility<T extends Object> implements
		IJP2PCompatibility<T> {

	IJxtaNode<T> root;
	
	/**
	 * Create the root node
	 * @param root
	 */
	public IJxtaNode<T> createRoot( T node ){
		root = new JxtaNode<T>( node );
		return root;
	}
	
	
	public IJxtaNode<T> getRoot() {
		return root;
	}

	private static class JxtaNode<T extends Object> implements IJxtaNode<T>{

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
		}

		/**
		 * Add a module and return the corresponding node that is created
		 * @param child
		 */
		public IJxtaNode<T> addChild( T module ){
			IJxtaNode<T> child = new JxtaNode<T>( module );
			this.addChild(child);
			return child;
		}

		@Override
		public void removeChild( IJxtaNode<T> child ){
			this.children.remove( child );
		}

		@SuppressWarnings("unchecked")
		@Override
		public T[] getChildren() {
			return (T[]) children.toArray( new Object[this.children.size()]);
		}
		
	}
}
