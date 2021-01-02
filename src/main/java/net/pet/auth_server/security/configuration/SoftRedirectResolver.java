package net.pet.auth_server.security.configuration;

import org.springframework.security.oauth2.provider.endpoint.DefaultRedirectResolver;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class SoftRedirectResolver extends DefaultRedirectResolver {

    @Override
    protected boolean redirectMatches(String requestedRedirect, String redirectUri) {
        UriComponents requestedRedirectUri = UriComponentsBuilder.fromUriString(requestedRedirect).build();
        String requestedRedirectUriScheme = (requestedRedirectUri.getScheme() != null ? requestedRedirectUri.getScheme() : "");
        String requestedRedirectUriHost = (requestedRedirectUri.getHost() != null ? requestedRedirectUri.getHost() : "");
        String requestedRedirectUriPath = (requestedRedirectUri.getPath() != null ? requestedRedirectUri.getPath() : "");

        UriComponents registeredRedirectUri = UriComponentsBuilder.fromUriString(redirectUri).build();
        String registeredRedirectUriScheme = (registeredRedirectUri.getScheme() != null ? registeredRedirectUri.getScheme() : "");
        String registeredRedirectUriHost = (registeredRedirectUri.getHost() != null ? registeredRedirectUri.getHost() : "");
        String registeredRedirectUriPath = (registeredRedirectUri.getPath() != null ? registeredRedirectUri.getPath() : "");


        return registeredRedirectUriScheme.equals(requestedRedirectUriScheme) &&
                hostMatches(registeredRedirectUriHost, requestedRedirectUriHost) &&
                registeredRedirectUriPath.equals(StringUtils.cleanPath(requestedRedirectUriPath));
    }
}
