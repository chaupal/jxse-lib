/*
 * Copyright (c) 2001-2007 Sun Microsystems, Inc.  All rights reserved.
 *
 *  The Sun Project JXTA(TM) Software License
 *
 *  Redistribution and use in source and binary forms, with or without 
 *  modification, are permitted provided that the following conditions are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice, 
 *     this list of conditions and the following disclaimer in the documentation 
 *     and/or other materials provided with the distribution.
 *
 *  3. The end-user documentation included with the redistribution, if any, must 
 *     include the following acknowledgment: "This product includes software 
 *     developed by Sun Microsystems, Inc. for JXTA(TM) technology." 
 *     Alternately, this acknowledgment may appear in the software itself, if 
 *     and wherever such third-party acknowledgments normally appear.
 *
 *  4. The names "Sun", "Sun Microsystems, Inc.", "JXTA" and "Project JXTA" must 
 *     not be used to endorse or promote products derived from this software 
 *     without prior written permission. For written permission, please contact 
 *     Project JXTA at http://www.jxta.org.
 *
 *  5. Products derived from this software may not be called "JXTA", nor may 
 *     "JXTA" appear in their name, without prior written permission of Sun.
 *
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 *  INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND 
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL SUN 
 *  MICROSYSTEMS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, 
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT 
 *  LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, 
 *  OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
 *  LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  JXTA is a registered trademark of Sun Microsystems, Inc. in the United 
 *  States and other countries.
 *
 *  Please see the license information page at :
 *  <http://www.jxta.org/project/www/license.html> for instructions on use of 
 *  the license in source files.
 *
 *  ====================================================================
 *
 *  This software consists of voluntary contributions made by many individuals 
 *  on behalf of Project JXTA. For more information on Project JXTA, please see 
 *  http://www.jxta.org.
 *
 *  This license is based on the BSD license adopted by the Apache Foundation. 
 */

package net.jxta.impl.membership.pse;

import net.jxta.id.ID;
import net.jxta.id.IDFactory;
import net.jxta.logging.Logging;
import net.jxta.membership.pse.IPSEConfig;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Logger;

/**
 * Manages the state of a Personal Security Enviroment.
 */
public final class PSEConfig implements IPSEConfig {

    /**
     * Log4J Logger
     */
    private final static transient Logger LOG = Logger.getLogger(PSEConfig.class.getName());

    /**
     * Manager for the keystore we are using.
     */
    private final KeyStoreManager keystore_manager;

    /**
     * The keystore passphrase.
     */
    private char[] keystore_password = null;

    /**
     * Standard constructor.
     *
     * @param storeManager   The StoreManager to be used for this PSEConfig
     *                       instance.
     * @param store_password The passphrase for the keystore or <tt>null</tt>.
     *                       The passphrase may be set independantly via
     *                       {@link #setKeyStorePassword(char[])}.
     */
    PSEConfig(KeyStoreManager storeManager, char[] store_password) {
        this.keystore_manager = storeManager;
        setKeyStorePassword(store_password);
    }

    /* (non-Javadoc)
	 * @see net.jxta.impl.membership.pse.IPSEConfig#setKeyStorePassword(char[])
	 */
    @Override
	public final void setKeyStorePassword(char[] store_password) {
        if (null != this.keystore_password) {
            Arrays.fill(this.keystore_password, '\0');
        }

        if (null == store_password) {
            this.keystore_password = null;
        } else {
            this.keystore_password = store_password.clone();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void finalize() throws Throwable {
        if (null != keystore_password) {
            Arrays.fill(keystore_password, '\0');
        }

        super.finalize();
    }

    /* (non-Javadoc)
	 * @see net.jxta.impl.membership.pse.IPSEConfig#isInitialized()
	 */
    @Override
	public boolean isInitialized() {
        try {
            if (keystore_password != null) {
                return keystore_manager.isInitialized(keystore_password);
            } else {
                return keystore_manager.isInitialized();
            }
        } catch (Exception ignored) {
            return false;
        }
    }

    /* (non-Javadoc)
	 * @see net.jxta.impl.membership.pse.IPSEConfig#initialize()
	 */
    @Override
	public void initialize() throws KeyStoreException, IOException {

        Logging.logCheckedInfo(LOG, "Initializing new PSE keystore...");

        synchronized (keystore_manager) {
            try {

                if (keystore_manager.isInitialized(keystore_password)) return;

                keystore_manager.createKeyStore(keystore_password);

            } catch (KeyStoreException failed) {

                Logging.logCheckedSevere(LOG, "Failure accessing or creating keystore.\n", failed);
                keystore_manager.eraseKeyStore();
                throw failed;

            }
        }
    }

    /* (non-Javadoc)
	 * @see net.jxta.impl.membership.pse.IPSEConfig#erase()
	 */
    @Override
	public void erase() throws IOException {
        synchronized (keystore_manager) {
            keystore_manager.eraseKeyStore();
        }
    }

    /* (non-Javadoc)
	 * @see net.jxta.impl.membership.pse.IPSEConfig#getKeyStore()
	 */
    @Override
	public KeyStore getKeyStore() {

        Throwable failure;

        try {

            return getKeyStore(keystore_password);

        } catch (KeyStoreException failed) {

            failure = failed;

        } catch (IOException failed) {

            failure = failed;

        }

        Logging.logCheckedWarning(LOG, "Failure recovering keystore : \n", failure);

        return null;
    }

    /* (non-Javadoc)
	 * @see net.jxta.impl.membership.pse.IPSEConfig#getKeyStore(char[])
	 */
    @Override
	public KeyStore getKeyStore(char[] store_password) throws KeyStoreException, IOException {
        synchronized (keystore_manager) {
            KeyStore store = keystore_manager.loadKeyStore(store_password);

            return store;
        }
    }

    /**
     * Check if the provided passwords are correct for the specified identity.
     *
     * @param id             The identity to be validated.
     * @param store_password The passphrase used to unlock the keystore may be
     *                       {@code null} for keystores with no passphrase.
     * @param key_password   The passphrase associated with the private key or
     *                       {@code null} if the key has no passphrase.
     * @return {@code true} if the passwords were valid for the given id
     *         otherwise {@code false}.
     */
    public boolean validPasswd(ID id, char[] store_password, char[] key_password) {

        if (null == id) {
            Logging.logCheckedFine(LOG, "null id");
            return false;
        }

        Throwable failure;

        try {
            synchronized (keystore_manager) {
                KeyStore store;

                if (null != store_password) {
                    store = keystore_manager.loadKeyStore(store_password);
                } else {
                    if (null != keystore_password) {
                        store = keystore_manager.loadKeyStore(keystore_password);
                    } else {
                        throw new UnrecoverableKeyException("KeyStore passphrase not initialized");
                    }
                }

                String alias = id.toString();

                Key key = store.getKey(alias, key_password);

                if ( key == null )
                    Logging.logCheckedFine(LOG, "Can't retrieve key for alias: ", alias);

                return (null != key);
            }

        } catch (UnrecoverableKeyException failed) {

            failure = failed;

        } catch (NoSuchAlgorithmException failed) {

            failure = failed;

        } catch (KeyStoreException failed) {

            failure = failed;

        } catch (IOException failed) {

            failure = failed;

        }

        Logging.logCheckedWarning(LOG, "Failure checking passphrase : \n", failure);

        return false;
    }

    /* (non-Javadoc)
	 * @see net.jxta.impl.membership.pse.IPSEConfig#getTrustedCertsList()
	 */
    @Override
	public ID[] getTrustedCertsList() throws KeyStoreException, IOException {
        List<ID> trustedCertsList = new ArrayList<ID>();

        synchronized (keystore_manager) {
            KeyStore store = keystore_manager.loadKeyStore(keystore_password);

            Enumeration<String> eachAlias = store.aliases();

            while (eachAlias.hasMoreElements()) {
                String anAlias = eachAlias.nextElement();

                if (store.isCertificateEntry(anAlias) || store.isKeyEntry(anAlias)) {
                    try {
                        URI id = new URI(anAlias);

                        trustedCertsList.add(IDFactory.fromURI(id));
                    } catch (URISyntaxException badID) {// ignored
                    }
                }
            }

            return trustedCertsList.toArray(new ID[trustedCertsList.size()]);
        }
    }

    /* (non-Javadoc)
	 * @see net.jxta.impl.membership.pse.IPSEConfig#getKeysList()
	 */
    @Override
	public ID[] getKeysList() throws KeyStoreException, IOException {
        return getKeysList(keystore_password);
    }

    /**
     * Returns the list of root certificates for which there is an associated
     * local private key.
     *
     * @param store_password The passphrase used to unlock the keystore may be
     *                       {@code null} for keystores with no passphrase.
     * @return an array of the available keys. May be an empty array.
     * @throws KeyStoreException When the wrong keystore has been provided.
     * @throws IOException       For errors related to processing the keystore.
     */
    public ID[] getKeysList(char[] store_password) throws KeyStoreException, IOException {
        List<ID> keyedRootsList = new ArrayList<ID>();

        synchronized (keystore_manager) {
            KeyStore store = keystore_manager.loadKeyStore(store_password);

            Enumeration<String> eachAlias = store.aliases();

            while (eachAlias.hasMoreElements()) {
                String anAlias = eachAlias.nextElement();

                if (store.isKeyEntry(anAlias)) {
                    try {
                        URI id = new URI(anAlias);

                        keyedRootsList.add(IDFactory.fromURI(id));
                    } catch (URISyntaxException badID) {// ignored
                    }
                }
            }

            return keyedRootsList.toArray(new ID[keyedRootsList.size()]);
        }
    }

    /* (non-Javadoc)
	 * @see net.jxta.impl.membership.pse.IPSEConfig#getTrustedCertificateID(java.security.cert.X509Certificate)
	 */
    @Override
	public ID getTrustedCertificateID(X509Certificate cert) throws KeyStoreException, IOException {

        String anAlias = null;

        synchronized (keystore_manager) {
            KeyStore store = keystore_manager.loadKeyStore(keystore_password);

            anAlias = store.getCertificateAlias(cert);
        }

        // not found.
        if (null == anAlias) {
            return null;
        }

        try {
            URI id = new URI(anAlias);

            return IDFactory.fromURI(id);
        } catch (URISyntaxException badID) {
            return null;
        }
    }

    /* (non-Javadoc)
	 * @see net.jxta.impl.membership.pse.IPSEConfig#getTrustedCertificate(net.jxta.id.ID)
	 */
    @Override
	public X509Certificate getTrustedCertificate(ID id) throws KeyStoreException, IOException {

        return getTrustedCertificate(id, keystore_password);
    }

    /**
     * Returns the trusted cert for the specified id.
     *
     * @param id             The id of the Certificate to retrieve.
     * @param store_password The passphrase used to unlock the keystore may be
     *                       {@code null} for keystores with no passphrase.
     * @return Certificate for the specified ID or null if the store does not
     *         contain the specified certificate.
     * @throws KeyStoreException When the wrong keystore has been provided.
     * @throws IOException       For errors related to processing the keystore.
     */
    public X509Certificate getTrustedCertificate(ID id, char[] store_password) throws KeyStoreException, IOException {

        String alias = id.toString();

        synchronized (keystore_manager) {
            KeyStore store = keystore_manager.loadKeyStore(store_password);

            if (!store.containsAlias(alias)) {
                return null;
            }

            return (X509Certificate) store.getCertificate(alias);
        }
    }

    /* (non-Javadoc)
	 * @see net.jxta.impl.membership.pse.IPSEConfig#getTrustedCertificateChain(net.jxta.id.ID)
	 */
    @Override
	public X509Certificate[] getTrustedCertificateChain(ID id) throws KeyStoreException, IOException {

        String alias = id.toString();

        synchronized (keystore_manager) {
            KeyStore store = keystore_manager.loadKeyStore(keystore_password);

            if (!store.containsAlias(alias)) {
                return null;
            }

            Certificate certs[] = store.getCertificateChain(alias);

            if (null == certs) {
                return null;
            }

            X509Certificate x509certs[] = new X509Certificate[certs.length];

            System.arraycopy(certs, 0, x509certs, 0, certs.length);

            return x509certs;
        }
    }

    /* (non-Javadoc)
	 * @see net.jxta.impl.membership.pse.IPSEConfig#getKey(net.jxta.id.ID, char[])
	 */
    @Override
	public PrivateKey getKey(ID id, char[] key_password) throws KeyStoreException, IOException {

        String alias = id.toString();

        try {
            synchronized (keystore_manager) {
                KeyStore store = keystore_manager.loadKeyStore(keystore_password);

                if (!store.containsAlias(alias) || !store.isKeyEntry(alias)) {
                    return null;
                }

                return (PrivateKey) store.getKey(alias, key_password);
            }

        } catch (NoSuchAlgorithmException failed) {

            Logging.logCheckedSevere(LOG, "Something failed\n", failed);

            KeyStoreException failure = new KeyStoreException("Something Failed");

            failure.initCause(failed);
            throw failure;

        } catch (UnrecoverableKeyException failed) {

            Logging.logCheckedSevere(LOG, "Key passphrase failure\n", failed);

            KeyStoreException failure = new KeyStoreException("Key passphrase failure");
            failure.initCause(failed);
            throw failure;

        }
    }

    /* (non-Javadoc)
	 * @see net.jxta.impl.membership.pse.IPSEConfig#isKey(net.jxta.id.ID)
	 */
    @Override
	public boolean isKey(ID id) throws KeyStoreException, IOException {
        return isKey(id, keystore_password);
    }

    /* (non-Javadoc)
	 * @see net.jxta.impl.membership.pse.IPSEConfig#isKey(net.jxta.id.ID, char[])
	 */
    @Override
	public boolean isKey(ID id, char[] store_password) throws KeyStoreException, IOException {
        String alias = id.toString();

        synchronized (keystore_manager) {
            KeyStore store = keystore_manager.loadKeyStore(store_password);

            return store.containsAlias(alias) & store.isKeyEntry(alias);
        }
    }

    /* (non-Javadoc)
	 * @see net.jxta.impl.membership.pse.IPSEConfig#setTrustedCertificate(net.jxta.id.ID, java.security.cert.X509Certificate)
	 */
    @Override
	public void setTrustedCertificate(ID id, X509Certificate cert) throws KeyStoreException, IOException {
        String alias = id.toString();

        synchronized (keystore_manager) {
            KeyStore store = keystore_manager.loadKeyStore(keystore_password);

            store.deleteEntry(alias);

            store.setCertificateEntry(alias, cert);

            keystore_manager.saveKeyStore(store, keystore_password);
        }
    }

    /* (non-Javadoc)
	 * @see net.jxta.impl.membership.pse.IPSEConfig#setKey(net.jxta.id.ID, java.security.cert.Certificate[], java.security.PrivateKey, char[])
	 */
    @Override
	public void setKey(ID id, Certificate[] certchain, PrivateKey key, char[] key_password) throws KeyStoreException, IOException {

        String alias = id.toString();

        synchronized (keystore_manager) {
            KeyStore store = keystore_manager.loadKeyStore(keystore_password);

            // Remove any existing entry.
            if (store.isKeyEntry(alias))
                store.deleteEntry(alias);

            store.setKeyEntry(alias, key, key_password, certchain);

            keystore_manager.saveKeyStore(store, keystore_password);
        }
    }

    /* (non-Javadoc)
	 * @see net.jxta.impl.membership.pse.IPSEConfig#erase(net.jxta.id.ID)
	 */
    @Override
	public void erase(ID id) throws KeyStoreException, IOException {
        String alias = id.toString();

        synchronized (keystore_manager) {
            KeyStore store = keystore_manager.loadKeyStore(keystore_password);

            store.deleteEntry(alias);

            keystore_manager.saveKeyStore(store, keystore_password);
        }
    }
}
