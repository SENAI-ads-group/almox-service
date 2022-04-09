package org.almox.core.config.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class EasyX509TrustManager implements X509TrustManager {
    private X509TrustManager standardTrustManager = null;
    private static final Log LOG = LogFactory.getLog(EasyX509TrustManager.class);

    public EasyX509TrustManager(KeyStore keystore) throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory factory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        factory.init(keystore);
        TrustManager[] trustmanagers = factory.getTrustManagers();
        if (trustmanagers.length == 0) {
            throw new NoSuchAlgorithmException("no trust manager found");
        } else {
            this.standardTrustManager = (X509TrustManager)trustmanagers[0];
        }
    }

    public void checkClientTrusted(X509Certificate[] certificates, String authType) throws CertificateException {
        this.standardTrustManager.checkClientTrusted(certificates, authType);
    }

    public void checkServerTrusted(X509Certificate[] certificates, String authType) throws CertificateException {
        if (certificates != null && LOG.isDebugEnabled()) {
            LOG.debug("Server certificate chain:");

            for(int i = 0; i < certificates.length; ++i) {
                LOG.debug("X509Certificate[" + i + "]=" + certificates[i]);
            }
        }

        if (certificates != null && certificates.length == 1) {
            certificates[0].checkValidity();
        } else {
            this.standardTrustManager.checkServerTrusted(certificates, authType);
        }

    }

    public X509Certificate[] getAcceptedIssuers() {
        return this.standardTrustManager.getAcceptedIssuers();
    }
}
