package org.almox.core.config.security;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClientError;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.SecureProtocolSocketFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.net.SocketFactory;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.SecureRandom;

public class EasySSLProtocolSocketFactory implements SecureProtocolSocketFactory {
    private static final Log LOG = LogFactory.getLog(EasySSLProtocolSocketFactory.class);
    private SSLContext sslcontext = null;

    public EasySSLProtocolSocketFactory() {
    }

    private static SSLContext createEasySSLContext() {
        try {
            SSLContext context = SSLContext.getInstance("SSL");
            context.init((KeyManager[]) null, new TrustManager[]{new EasyX509TrustManager((KeyStore) null)}, (SecureRandom) null);
            return context;
        } catch (Exception var1) {
            LOG.error(var1.getMessage(), var1);
            throw new HttpClientError(var1.toString());
        }
    }

    private SSLContext getSSLContext() {
        if (this.sslcontext == null) {
            this.sslcontext = createEasySSLContext();
        }

        return this.sslcontext;
    }

    public Socket createSocket(String host, int port, InetAddress clientHost, int clientPort) throws IOException, UnknownHostException {
        return this.getSSLContext().getSocketFactory().createSocket(host, port, clientHost, clientPort);
    }

    public Socket createSocket(String host, int port, InetAddress localAddress, int localPort, HttpConnectionParams params) throws IOException, UnknownHostException, ConnectTimeoutException {
        if (params == null) {
            throw new IllegalArgumentException("Parameters may not be null");
        } else {
            int timeout = params.getConnectionTimeout();
            SocketFactory socketfactory = this.getSSLContext().getSocketFactory();
            if (timeout == 0) {
                return socketfactory.createSocket(host, port, localAddress, localPort);
            } else {
                Socket socket = socketfactory.createSocket();
                SocketAddress localaddr = new InetSocketAddress(localAddress, localPort);
                SocketAddress remoteaddr = new InetSocketAddress(host, port);
                socket.bind(localaddr);
                socket.connect(remoteaddr, timeout);
                return socket;
            }
        }
    }

    public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
        return this.getSSLContext().getSocketFactory().createSocket(host, port);
    }

    public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
        return this.getSSLContext().getSocketFactory().createSocket(socket, host, port, autoClose);
    }

    public boolean equals(Object obj) {
        return obj != null && obj.getClass().equals(EasySSLProtocolSocketFactory.class);
    }

    public int hashCode() {
        return EasySSLProtocolSocketFactory.class.hashCode();
    }
}
