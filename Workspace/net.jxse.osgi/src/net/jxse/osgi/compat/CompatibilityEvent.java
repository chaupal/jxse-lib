package net.jxse.osgi.compat;

import java.util.EventObject;

import net.jxse.osgi.compat.ICompatibilityListener.ChangeEvents;

public class CompatibilityEvent extends EventObject {
	private static final long serialVersionUID = 1L;

	private ChangeEvents event;
	
	public CompatibilityEvent( Object source, ChangeEvents event ) {
		super(source);
		this.event = event;
	}

	protected ChangeEvents getEvent() {
		return event;
	}
}
