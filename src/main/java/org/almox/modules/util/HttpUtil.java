package org.almox.modules.util;

import org.almox.core.config.security.EasySSLProtocolSocketFactory;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.protocol.Protocol;

import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

public final class HttpUtil {
    private static boolean hasProxy;
    private static boolean hasCredentialProxy;
    private static String proxyHost;
    private static int proxyPort;
    private static String proxyUser;
    private static String proxyPass;

    static {
        Protocol easyhttps = new Protocol("https", new EasySSLProtocolSocketFactory(), 443);
        Protocol.registerProtocol("https", easyhttps);
    }

    private HttpUtil() {
    }

    public static void setHttpProxy(String host, int port, String user, String pass) {
        hasProxy = true;
        proxyHost = host;
        proxyPort = port;
        if (user != null && pass != null && !user.isEmpty()) {
            proxyUser = user;
            proxyPass = pass;
            hasCredentialProxy = true;
        }

    }

    public static String getAuthHeaderBase64(String clientId, String clientSecret) {
        String auth = clientId + ":" + clientSecret;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth);
        return authHeader;
    }

    public static HttpResponse sendPut(String url, Map<String, String> headers, String contentType, String body, String charset) {
        return null;
    }

    private static void configProxy(HttpClient httpClient) {
        if (hasProxy) {
            HostConfiguration config = httpClient.getHostConfiguration();
            config.setProxy(proxyHost, proxyPort);
            if (hasCredentialProxy) {
                Credentials credentials = new UsernamePasswordCredentials(proxyUser, proxyPass);
                AuthScope authScope = new AuthScope(proxyHost, proxyPort);
                httpClient.getState().setProxyCredentials(authScope, credentials);
            }
        }

    }
}
