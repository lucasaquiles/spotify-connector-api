package com.lucasaquiles.spotify.connector.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.http.AccessTokenRequiredException;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.implicit.ImplicitAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.Arrays;

@Slf4j
public class SpotifyConfiguration extends OAuth2FeignRequestInterceptor{

    @Value("${spotify.api.secret}")
    private String secret;

    @Value("${spotify.api.clientId}")
    private String clientId;

    @Value("${spotify.api.authorize}")
    private String authUrl;

    @Value("${spotify.api.scope}")
    private String scope;

    private final OAuth2ClientContext context;
    private final AccessTokenProvider accessTokenProvider = new AccessTokenProviderChain(Arrays
            .<AccessTokenProvider> asList(new AuthorizationCodeAccessTokenProvider(),
                    new ImplicitAccessTokenProvider(),
                    new ResourceOwnerPasswordAccessTokenProvider(),
                    new ClientCredentialsAccessTokenProvider()));

    public SpotifyConfiguration(OAuth2ClientContext context) {

        super(context, null);
        this.context = context;
    }

    @Override
    protected OAuth2AccessToken acquireAccessToken() throws UserRedirectRequiredException {
        return acquireAccessToken(resource());
    }


    protected OAuth2AccessToken acquireAccessToken(OAuth2ProtectedResourceDetails resource)
            throws UserRedirectRequiredException {
        AccessTokenRequest tokenRequest = context.getAccessTokenRequest();

        if (tokenRequest == null) {
            throw new AccessTokenRequiredException(
                    "Cannot find valid context on request for resource '"
                            + resource.getId() + "'.",
                    resource);
        }

        String stateKey = tokenRequest.getStateKey();

        if (stateKey != null) {
            tokenRequest.setPreservedState(
                    context.removePreservedState(stateKey));
        }

        OAuth2AccessToken existingToken = context.getAccessToken();

        if (existingToken != null) {
            context.setAccessToken(existingToken);
        }

        OAuth2AccessToken obtainableAccessToken;
        obtainableAccessToken = accessTokenProvider.obtainAccessToken(resource,
                tokenRequest);

        if (obtainableAccessToken == null || obtainableAccessToken.getValue() == null) {
            throw new IllegalStateException(
                    " Access token provider returned a null token, which is illegal according to the contract.");
        }

        context.setAccessToken(obtainableAccessToken);

        return obtainableAccessToken;
    }

    OAuth2ProtectedResourceDetails resource() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null || authentication instanceof AnonymousAuthenticationToken) {
            ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
            details.setAccessTokenUri(authUrl);
            details.setClientId(clientId);
            details.setClientSecret(secret);
            details.setScope(Arrays.asList(scope));
            log.debug("ClientCredentialsResourceDetails");
            return details;
        } else {

            ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();

            details.setClientSecret(secret);
            details.setClientId(clientId);
            details.setAccessTokenUri(authUrl);
            details.setUsername("");
            details.setPassword("");
            details.setScope(Arrays.asList(scope));
            details.setGrantType("client_credentials");

            log.info("eita ${}", details);

            return details;
        }
    }
}
