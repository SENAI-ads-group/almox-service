package org.almox.core.config.security.resourceServer;

import lombok.RequiredArgsConstructor;
import org.almox.modules.operador.dto.OperadorMapper;
import org.almox.modules.operador.repository.OperadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;

import javax.annotation.PostConstruct;
import java.util.Map;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CheckTokenEndpointConfig {

    private final CheckTokenEndpoint checkTokenEndpoint;
    private final AccessTokenConverter accessTokenConverter;
    private final OperadorRepository operadorRepository;

    @PostConstruct
    public void init() {
        checkTokenEndpoint.setAccessTokenConverter(new AccessTokenConverter() {
            @Override
            public Map<String, Object> convertAccessToken(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
                Map<String, Object> defaultResult = (Map<String, Object>) accessTokenConverter.convertAccessToken(oAuth2AccessToken, oAuth2Authentication);
                Object username = defaultResult.get("user_name");
                if (username != null) {
                    operadorRepository
                            .buscarPorLogin(username.toString())
                            .ifPresent(ope -> defaultResult.put("operador", OperadorMapper.INSTANCE.toDTO(ope)));
                }
                return defaultResult;
            }

            @Override
            public OAuth2AccessToken extractAccessToken(String s, Map<String, ?> map) {
                return accessTokenConverter.extractAccessToken(s, map);
            }

            @Override
            public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
                return accessTokenConverter.extractAuthentication(map);
            }
        });
    }
}
