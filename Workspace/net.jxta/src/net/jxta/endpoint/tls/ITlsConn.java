package net.jxta.endpoint.tls;

interface ITlsConn {

    /**
     * Tracks the state of our TLS connection with a remote peer.
     */
    enum HandshakeState {

        /**
         * Handshake is ready to begin. We will be the client side.
         */
        CLIENTSTART, /**
     * Handshake is ready to begin. We will be the server side.
     */
    SERVERSTART, /**
     * Handshake is in progress.
     */
    HANDSHAKESTARTED, /**
     * Handshake failed to complete.
     */
    HANDSHAKEFAILED, /**
     * Handshake completed successfully.
     */
    HANDSHAKEFINISHED, /**
     * Connection is closing.
     */
    CONNECTIONCLOSING, /**
     * Connection has died.
     */
    CONNECTIONDEAD
    }

	boolean isClosing();

	/**
	 * @inheritDoc <p/>An implementation which is useful for debugging.
	 */
	String toString();

	HandshakeState getHandshakeState();

}