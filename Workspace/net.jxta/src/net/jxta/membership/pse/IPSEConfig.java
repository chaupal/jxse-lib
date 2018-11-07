package net.jxta.membership.pse;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

import net.jxta.id.ID;

public interface IPSEConfig {

	/**
	 * Sets the passphrase to be used when unlocking the keystore.
	 *
	 * @param store_password The passphrase used to unlock the keystore may be
	 *                       {@code null} for keystores with no passphrase.
	 */
	void setKeyStorePassword(char[] store_password);

	/**
	 * Returns {@code true} if the PSE has been initialized (created). Some
	 * keystore formats may not require initialization and may always return
	 * {@code true}. {@code false} may also be returned if the keystore passphrase is
	 * incorrect.
	 *
	 * @return {@code true} if the PSE has been previously initialized
	 *         otherwise {@code false}.
	 */
	boolean isInitialized();

	/**
	 * Initializes the PSE environment.
	 *
	 * @throws KeyStoreException When the wrong keystore has been provided.
	 * @throws IOException       For errors related to processing the keystore.
	 */
	void initialize() throws KeyStoreException, IOException;

	/**
	 * Removes an existing PSE enviroment.
	 *
	 * @throws IOException If the PSE cannot be successfully deleted.
	 */
	void erase() throws IOException;

	/**
	 * Gets a copy of the KeyStore associated with this PSE instance. The
	 * returned KeyStore is a copy and not tied to the instance maintained by
	 * the PSE. Changing the returned keystore will not result in changes to
	 * the PSE.
	 *
	 * @return The keystore or {@code null} if it cannot be retrieved.
	 */
	KeyStore getKeyStore();

	/**
	 * Gets a copy of the KeyStore associated with this PSE instance. The
	 * returned KeyStore is a copy and not tied to the instance maintained by
	 * the PSE. Changing the returned keystore will not result in changes to
	 * the PSE.
	 *
	 * @param store_password The passphrase used to unlock the keystore may be
	 *                       {@code null} for keystores with no passphrase.
	 * @return The keystore.
	 * @throws KeyStoreException When the wrong keystore has been provided.
	 * @throws IOException       For errors related to processing the keystore.
	 * @since JXTA 2.4
	 */
	KeyStore getKeyStore(char[] store_password) throws KeyStoreException, IOException;

	/**
	 * Returns the list of the trusted certificates available in this keystore.
	 *
	 * @return an array of the IDs of the available trusted certificates.
	 * @throws KeyStoreException When the wrong keystore has been provided.
	 * @throws IOException       For errors related to processing the keystore.
	 */
	ID[] getTrustedCertsList() throws KeyStoreException, IOException;

	/**
	 * Returns the list of root certificates for which there is an associated
	 * local private key.
	 *
	 * @return an array of the available keys. May be an empty array.
	 * @throws KeyStoreException When the wrong keystore has been provided.
	 * @throws IOException       For errors related to processing the keystore.
	 */
	ID[] getKeysList() throws KeyStoreException, IOException;

	/**
	 * Returns the ID of the provided certificate or null if the certificate is
	 * not found in the keystore.
	 *
	 * @param cert The certificate who's ID is desired.
	 * @return The ID of the certificate or <tt>null</tt> if no matching
	 *         Certificate was found.
	 * @throws KeyStoreException When the wrong keystore has been provided.
	 * @throws IOException       For errors related to processing the keystore.
	 */
	ID getTrustedCertificateID(X509Certificate cert) throws KeyStoreException, IOException;

	/**
	 * Returns the trusted cert for the specified id.
	 *
	 * @param id The id of the Certificate to retrieve.
	 * @return Certificate for the specified ID or null if the store does not
	 *         contain the specified certificate.
	 * @throws KeyStoreException When the wrong keystore key has been provided.
	 * @throws IOException       For errors related to processing the keystore.
	 */
	X509Certificate getTrustedCertificate(ID id) throws KeyStoreException, IOException;

	/**
	 * Returns the trusted cert chain for the specified id.
	 *
	 * @param id The ID of the certificate who's certificate chain is desired.
	 * @return Certificate chain for the specified ID or null if the PSE does
	 *         not contain the specified certificate.
	 * @throws KeyStoreException When the wrong keystore has been provided.
	 * @throws IOException       For errors related to processing the keystore.
	 */
	X509Certificate[] getTrustedCertificateChain(ID id) throws KeyStoreException, IOException;

	/**
	 * Returns the private key for the specified ID.
	 *
	 * @param id           The ID of the requested private key.
	 * @param key_password The passphrase associated with the private key or
	 *                     {@code null} if the key has no passphrase.
	 * @return PrivateKey for the specified ID.
	 * @throws KeyStoreException When the wrong keystore has been provided.
	 * @throws IOException       For errors related to processing the keystore.
	 */
	PrivateKey getKey(ID id, char[] key_password) throws KeyStoreException, IOException;

	/**
	 * Returns <tt>true</tt> if the specified id is associated with a private
	 * key.
	 *
	 * @param id The ID of the requested private key.
	 * @return <tt>true</tt> if a private key with the specified ID is present
	 *         otherwise <tt>false</tt>
	 * @throws KeyStoreException When the wrong keystore has been provided.
	 * @throws IOException       For errors related to processing the keystore.
	 */
	boolean isKey(ID id) throws KeyStoreException, IOException;

	/**
	 * Returns <tt>true</tt> if the specified id is associated with a private
	 * key.
	 *
	 * @param id             The ID of the requested private key.
	 * @param store_password The passphrase used to unlock the keystore may be
	 *                       {@code null} for keystores with no passphrase.
	 * @return <tt>true</tt> if a private key with the specified ID is present
	 *         otherwise <tt>false</tt>
	 * @throws KeyStoreException When the wrong keystore has been provided.
	 * @throws IOException       For errors related to processing the keystore.
	 */
	boolean isKey(ID id, char[] store_password) throws KeyStoreException, IOException;

	/**
	 * Adds a trusted certificate with the specified id to the key store. The
	 * certificate replaces any existing certificate or private key stored at
	 * this ID.
	 *
	 * @param id   The ID under which the certificate will be stored.
	 * @param cert Certificate for the specified ID.
	 * @throws KeyStoreException When the wrong keystore has been provided.
	 * @throws IOException       For errors related to processing the keystore.
	 */
	void setTrustedCertificate(ID id, X509Certificate cert) throws KeyStoreException, IOException;

	/**
	 * Adds a private key to the PSE using the specified ID. The key replaces
	 * any existing certificate or private key stored at this ID. The key is
	 * stored using the provided key passphrase.
	 *
	 * @param id           The ID under which the certificate chain and private key will be stored.
	 * @param certchain    The certificate chain matching the private key.
	 * @param key          The private key to be stored in the kestore.
	 * @param key_password The passphrase associated with the private key or
	 *                     {@code null} if the key has no passphrase.
	 * @throws KeyStoreException When the wrong keystore key has been provided.
	 * @throws IOException       For errors related to processing the keystore.
	 */
	void setKey(ID id, Certificate[] certchain, PrivateKey key, char[] key_password)
			throws KeyStoreException, IOException;

	/**
	 * Erases the specified id from the keystore.
	 *
	 * @param id The ID of the key or certificate to be deleted.
	 * @throws KeyStoreException When the wrong keystore password has been
	 *                           provided.
	 * @throws IOException       For errors related to processing the keystore.
	 */
	void erase(ID id) throws KeyStoreException, IOException;

	X509Certificate getTrustedCertificate(ID aPeer, char[] store_password) throws IOException, KeyStoreException;

	boolean validPasswd(ID identity, char[] store_password, char[] key_password);

	ID[] getKeysList(char[] store_password) throws IOException, KeyStoreException;

}