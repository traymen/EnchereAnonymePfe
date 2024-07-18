package com.aymen.enchere.reclamation;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;


@Configuration
public class KeycloakConfig {
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration registration = ClientRegistration.withRegistrationId("keycloak")
                .clientId("your-client-id")
                .clientSecret("your-client-secret")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .scope("openid")
                .authorizationUri("https://your-keycloak-server/auth/realms/your-realm/protocol/openid-connect/auth")
                .tokenUri("https://your-keycloak-server/auth/realms/your-realm/protocol/openid-connect/token")
                .userInfoUri("https://your-keycloak-server/auth/realms/your-realm/protocol/openid-connect/userinfo")
                .build();

        return new InMemoryClientRegistrationRepository(registration);
    }
/*
    @Bean
    public OAuth2RestTemplate keycloakRestTemplate() {
        return new OAuth2RestTemplate(clientRegistrationRepository().findByRegistrationId("keycloak"));
    }
    */
}
