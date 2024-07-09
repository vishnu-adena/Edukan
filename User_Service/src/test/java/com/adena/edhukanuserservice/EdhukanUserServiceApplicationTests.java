package com.adena.edhukanuserservice;

import com.adena.edhukanuserservice.securityconfig.repository.JpaRegisteredClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.UUID;

@SpringBootTest
class EdhukanUserServiceApplicationTests {

   @Autowired
   JpaRegisteredClientRepository registeredClientRepository;


    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("edukan")
                .clientSecret("$2a$12$a6lHyHh.Xlo5jGG4SMLv/.E0pTdUqvk/4okAp9PF.KdX20WXMAsO6")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("http://localhost:3000/oauth/callback")
                .postLogoutRedirectUri("http://localhost:3000/oauth/callback")
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .scope("ADMIN")
                .scope("STUDENT")
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build();
        registeredClientRepository.save(oidcClient);
    }

}
