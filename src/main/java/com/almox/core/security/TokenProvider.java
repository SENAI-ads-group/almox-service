package com.almox.core.security;

import com.almox.modules.util.HttpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenProvider {

    @Value("${auth.url}")
    private String authUrl;

    @Value("${auth.clientId}")
    private String clientId;

    @Value("${auth.clientSecret}")
    private String clientSecret;

    public Token getToken(String email, String password) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", HttpUtil.getAuthHeaderBase64(clientId, clientSecret));

        Map<String, String> parameters = new HashMap<>();
        parameters.put("grant_type", "password");

        try {
            return new Token(authUrl, email, password, headers, parameters);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
