package com.almox.core.security;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Token {
    private String accessToken;
    private String tokenType;
    private long expiresIn;
    private long timestamp;
    private List<String> scope = new ArrayList();
    private String tenant;
    private int status;
    private int result;
    private String error;
    private String message;
    private String url;
    private String username;
    private String password;
    private Map<String, Object> extra = new HashMap();
    private Map<String, String> parameters = new HashMap();
    private Map<String, String> headers = new HashMap();

    public Token(String url, String username, String password, Map<String, String> headers, Map<String, String> parameters) throws HttpException, IOException {
        this.url = url;
        this.username = username;
        this.password = password;
        this.parameters = parameters;
        this.headers = headers;
        this.request();
    }

    private void request() throws IOException, HttpException {
        PostMethod httpPost = new PostMethod(this.url);
        httpPost.addParameter("username", this.username);
        httpPost.addParameter("password", this.password);
        Map.Entry entry;
        Iterator var3;
        if (this.parameters != null) {
            var3 = this.parameters.entrySet().iterator();

            while (var3.hasNext()) {
                entry = (Map.Entry) var3.next();
                httpPost.addParameter((String) entry.getKey(), (String) entry.getValue());
            }
        }

        if (this.headers != null) {
            var3 = this.headers.entrySet().iterator();

            while (var3.hasNext()) {
                entry = (Map.Entry) var3.next();
                httpPost.addRequestHeader((String) entry.getKey(), (String) entry.getValue());
            }
        }

        HttpClient httpClient = new HttpClient();
        this.result = httpClient.executeMethod(httpPost);
        HttpResponse resp = new HttpResponse(this.result, httpPost);
        Map<String, Object> mapResp = resp.getContentAsMap();
        String accessToken = (String) mapResp.get("access_token");
        if (accessToken != null) {
            this.setAccessToken(accessToken);
            this.setTenant((String) mapResp.get("tenant"));
            this.setTokenType((String) mapResp.get("token_type"));
            this.setExpiresIn((long) Integer.valueOf(mapResp.get("expires_in").toString()));
            this.timestamp = System.currentTimeMillis();
            String scope = (String) mapResp.get("scope");
            if (scope != null) {
                String[] scopeData = scope.split(" ");

                for (int i = 0; i < scopeData.length; ++i) {
                    this.scope.add(scopeData[i]);
                }
            }

            if (mapResp.containsKey("extra")) {
                this.extra.putAll((Map) mapResp.get("extra"));
            }
        } else {
            this.setError((String) mapResp.get("error"));
            this.setStatus(resp.getCode());
            this.setMessage((String) mapResp.get("message"));
        }

        System.out.println(httpPost.getResponseBodyAsString());
        httpPost.releaseConnection();
    }

    public int getSecondsRemaining() {
        int elapsed = (int) (System.currentTimeMillis() - this.timestamp) / 1000;
        return (int) (this.expiresIn - (long) elapsed);
    }

    public void validate() throws HttpException, IOException {
        System.out.printf("Remaining: %d\n", this.getSecondsRemaining());
        if (this.getSecondsRemaining() < 30) {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException var2) {
                var2.printStackTrace();
            }

            this.request();
            System.out.println("new token .... " + this.accessToken);
        }

        if (this.error != null) {
            this.request();
        }

    }

    public String getAccessToken() {
        return this.accessToken;
    }

    void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public long getExpiresIn() {
        return this.expiresIn;
    }

    void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<String> getScope() {
        return this.scope;
    }

    void setScope(List<String> scope) {
        this.scope = scope;
    }

    public String getTenant() {
        return this.tenant;
    }

    void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public int getStatus() {
        return this.status;
    }

    void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return this.error;
    }

    void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return this.message;
    }

    void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getExtra() {
        return this.extra;
    }
}
