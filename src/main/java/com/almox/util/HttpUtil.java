package com.almox.util;

import com.almox.security.EasySSLProtocolSocketFactory;
import com.almox.security.HttpResponse;
import com.almox.security.Token;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.protocol.Protocol;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Iterator;
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
        try {
            StringRequestEntity stringEntity = new StringRequestEntity(body, contentType, charset);
            PutMethod httpPost = new PutMethod(url);

            try {
                httpPost.setRequestEntity(stringEntity);
                Iterator var8 = headers.entrySet().iterator();

                while (var8.hasNext()) {
                    Map.Entry<String, String> entry = (Map.Entry) var8.next();
                    httpPost.addRequestHeader((String) entry.getKey(), (String) entry.getValue());
                }

                HttpClient httpClient = new HttpClient();
                configProxy(httpClient);
                int result = httpClient.executeMethod(httpPost);
                HttpResponse resp = new HttpResponse(result, httpPost);
                resp.updateContentAsString();
                HttpResponse var11 = resp;
                return var11;
            } finally {
                httpPost.releaseConnection();
            }
        } catch (IOException var15) {
            return new HttpResponse(var15);
        }
    }

    public static HttpResponse sendPost(String url, Map<String, String> headers, Map<String, String> parameters, String contentType, String body, String charset) {
        try {
            StringRequestEntity stringEntity = new StringRequestEntity(body, contentType, charset);
            PostMethod httpPost = new PostMethod(url);

            try {
                httpPost.setRequestEntity(stringEntity);
                Iterator var9 = parameters.entrySet().iterator();

                Map.Entry entry;
                while (var9.hasNext()) {
                    entry = (Map.Entry) var9.next();
                    httpPost.addParameter((String) entry.getKey(), (String) entry.getValue());
                }

                var9 = headers.entrySet().iterator();

                while (var9.hasNext()) {
                    entry = (Map.Entry) var9.next();
                    httpPost.addRequestHeader((String) entry.getKey(), (String) entry.getValue());
                }

                HttpClient httpClient = new HttpClient();
                configProxy(httpClient);
                int result = httpClient.executeMethod(httpPost);
                HttpResponse resp = new HttpResponse(result, httpPost);
                resp.updateContentAsString();
                HttpResponse var12 = resp;
                return var12;
            } finally {
                httpPost.releaseConnection();
            }
        } catch (Exception var16) {
            return new HttpResponse(var16);
        }
    }

    public static Token oauth(String url, String username, String password, Map<String, String> headers, Map<String, String> parameters) {
        try {
            return new Token(url, username, password, headers, parameters);
        } catch (Exception var6) {
            var6.printStackTrace();
            return null;
        }
    }

    public static HttpResponse sendGet(String url, Map<String, String> headers, String charset) {
        try {
            GetMethod httpGet = new GetMethod(url);
            Iterator var5 = headers.entrySet().iterator();

            while (var5.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry) var5.next();
                httpGet.addRequestHeader((String) entry.getKey(), (String) entry.getValue());
            }

            HttpClient httpClient = new HttpClient();
            configProxy(httpClient);

            HttpResponse var8;
            try {
                int result = httpClient.executeMethod(httpGet);
                HttpResponse resp = new HttpResponse(result, httpGet);
                resp.updateContentAsString();
                var8 = resp;
            } finally {
                httpGet.releaseConnection();
            }

            return var8;
        } catch (Exception var12) {
            return new HttpResponse(var12);
        }
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
