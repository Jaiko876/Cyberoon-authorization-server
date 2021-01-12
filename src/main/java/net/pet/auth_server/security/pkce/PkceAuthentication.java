package net.pet.auth_server.security.pkce;

import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

public class PkceAuthentication {
    private final String codeChallenge;
    private final CodeChallengeMethod codeChallengeMethod;
    private final OAuth2Authentication authentication;

    public PkceAuthentication(OAuth2Authentication authentication) {
        this.codeChallenge = null;
        this.codeChallengeMethod = CodeChallengeMethod.NONE;
        this.authentication = authentication;
    }

    public PkceAuthentication(String codeChallenge, CodeChallengeMethod codeChallengeMethod, OAuth2Authentication authentication) {
        this.codeChallenge = codeChallenge;
        this.codeChallengeMethod = codeChallengeMethod;
        this.authentication = authentication;
    }

    public OAuth2Authentication getAuthentication(String codeVerifier) {
        if (codeChallengeMethod == CodeChallengeMethod.NONE)
            return authentication;
        else if (codeChallengeMethod.decode(codeVerifier).equals(codeChallenge))
            return authentication;
        throw new InvalidGrantException("Invalid code verifier.");
    }
}
