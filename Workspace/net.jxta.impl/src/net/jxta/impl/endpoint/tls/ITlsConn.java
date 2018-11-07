package net.jxta.impl.endpoint.tls;

interface ITlsConn {

	boolean isClosing();

	/**
	 * @inheritDoc <p/>An implementation which is useful for debugging.
	 */
	String toString();

}