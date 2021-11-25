package com.almox.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.httpclient.HttpMethodBase;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Map;

public class HttpResponse {
    private int code;
    private HttpMethodBase httpMethodBase;
    private Exception exception;
    private String contentAsString;

    public HttpResponse(int code, HttpMethodBase httpMethodBase) {
        this.code = code;
        this.httpMethodBase = httpMethodBase;
    }

    public HttpResponse(Exception exception) {
        this.exception = exception;
    }

    public void updateContentAsString() throws IOException {
        this.contentAsString = this.httpMethodBase.getResponseBodyAsString();
    }

    public int getCode() {
        return this.code;
    }

    public Map<String, Object> getContentAsMap() throws IOException {
        this.updateContentAsString();
        String json = this.getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        return (Map) mapper.readValue(json, Map.class);
    }

    public String getContentAsString() throws IOException {
        return this.contentAsString;
    }

    public InputStream getContentAsStream() throws IOException {
        return this.httpMethodBase.getResponseBodyAsStream();
    }

    public byte[] getContentAsByteArray() throws IOException {
        return this.httpMethodBase.getResponseBody();
    }

    public String getContentAsBase64String() throws IOException {
        return Base64.getEncoder().encodeToString(this.httpMethodBase.getResponseBody());
    }

    public Exception getException() {
        return this.exception;
    }
}