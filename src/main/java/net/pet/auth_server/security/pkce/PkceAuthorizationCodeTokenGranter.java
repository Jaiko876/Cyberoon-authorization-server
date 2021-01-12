package net.pet.auth_server.security.pkce;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.security.oauth2.common.exceptions.RedirectMismatchException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.HashMap;
import java.util.Map;

public class PkceAuthorizationCodeTokenGranter extends AuthorizationCodeTokenGranter {
    private final PkceAuthorizationCodeServices authorizationCodeServices;

    public PkceAuthorizationCodeTokenGranter(AuthorizationServerTokenServices tokenServices, PkceAuthorizationCodeServices authorizationCodeServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        super(tokenServices, authorizationCodeServices, clientDetailsService, requestFactory);
        this.authorizationCodeServices = authorizationCodeServices;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> requestParameters = tokenRequest.getRequestParameters();
        String authorizationCode = requestParameters.get("code");
        String redirectUri = requestParameters.get("redirect_uri");
        if (authorizationCode == null)
            throw new InvalidRequestException("An authorization code me be supplied.");
        String codeVerifier = requestParameters.getOrDefault("code_verifier", "");
        OAuth2Authentication storedAuth =
                authorizationCodeServices.consumeAuthorizationCodeAndCodeVerifier(authorizationCode, codeVerifier);
        if (storedAuth == null)
            throw new InvalidGrantException("Invalid authorization code: " + authorizationCode);
        OAuth2Request pendingOauth2Request = storedAuth.getOAuth2Request();
        String redirectUriApproval = pendingOauth2Request.getRequestParameters().get("redirect_uri");
        if ((redirectUri != null || redirectUriApproval != null) && !redirectUriApproval.equals(redirectUri))
            throw new RedirectMismatchException("Redirect URI mismatch");
        String pendingClientId = pendingOauth2Request.getClientId();
        String clientId = tokenRequest.getClientId();
        if (clientId != null && !clientId.equals(pendingClientId))
            throw new InvalidClientException("Cliend ID mismatch");
        HashMap<String, String> combinedParameters = new HashMap<>(pendingOauth2Request.getRequestParameters());
        combinedParameters.putAll(requestParameters);
        OAuth2Request finalStoredOauth2Request = pendingOauth2Request.createOAuth2Request(combinedParameters);
        Authentication userAuthentication = storedAuth.getUserAuthentication();
        return new OAuth2Authentication(finalStoredOauth2Request, userAuthentication);
    }
}
