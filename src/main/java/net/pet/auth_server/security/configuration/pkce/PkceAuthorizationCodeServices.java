package net.pet.auth_server.security.configuration.pkce;

import net.pet.auth_server.domain.enumerated.CodeChallengeMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class PkceAuthorizationCodeServices implements AuthorizationCodeServices {

    private final RandomValueStringGenerator generator = new RandomValueStringGenerator();
    private final Map<String, PkceAuthentication> authorizationCodeStore = new ConcurrentHashMap<>();
    private final ClientDetailsService clientDetailsService;
    private final PasswordEncoder passwordEncoder;

    public PkceAuthorizationCodeServices(ClientDetailsService clientDetailsService, PasswordEncoder passwordEncoder) {
        this.clientDetailsService = clientDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String createAuthorizationCode(OAuth2Authentication authentication) {
        PkceAuthentication pkceAuthentication = obtainPkceAuthentication(authentication);
        String code = generator.generate();
        authorizationCodeStore.put(code, pkceAuthentication);
        return code;
    }

    @Override
    public OAuth2Authentication consumeAuthorizationCode(String code) throws InvalidGrantException {
        throw new UnsupportedOperationException();
    }

    public OAuth2Authentication consumeAuthorizationCodeAndCodeVerifier(String code, String verifier) {
        return authorizationCodeStore.get(code).getAuthentication(verifier);
    }

    private PkceAuthentication obtainPkceAuthentication(OAuth2Authentication authentication) {
        Map<String, String> requestParameters = authentication.getOAuth2Request().getRequestParameters();

        if (isPublicClient(requestParameters.get("client_id")))
            throw new InvalidRequestException("Public clients are not allowed ");
        if (!requestParameters.containsKey("code_challenge"))
            throw new InvalidRequestException("Code challenge required.");

        String codeChallenge = requestParameters.get("code_challenge");
        CodeChallengeMethod codeChallengeMethod = getCodeChallengeMethod(requestParameters);
        return new PkceAuthentication(codeChallenge, codeChallengeMethod, authentication);
    }

    private CodeChallengeMethod getCodeChallengeMethod(Map<String, String> requestParameters) {
        return Optional.ofNullable(requestParameters.get("code_challenge_method"))
                .map(String::toUpperCase)
                .map(CodeChallengeMethod::valueOf)
                .orElse(CodeChallengeMethod.PLAIN);
    }

    private boolean isPublicClient(String clientId) {
        String clientSecret = clientDetailsService.loadClientByClientId(clientId).getClientSecret();
        return clientSecret == null || passwordEncoder.matches("", clientSecret);
    }
}
