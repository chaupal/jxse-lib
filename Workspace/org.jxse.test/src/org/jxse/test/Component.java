package org.jxse.test;

import net.jxse.osgi.message.SimpleMessagePrinter;

public class Component extends SimpleMessagePrinter{

	public Component() {
		super( Activator.S_BUNDLE_ID);
	}
}