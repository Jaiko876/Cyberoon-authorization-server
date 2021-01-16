package net.pet.auth_server.domain.oidc;

import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public class AuthOidcUser extends DefaultOidcUser {

    public AuthOidcUser(OidcUser oidcUser) {
        super(oidcUser.getAuthorities(), oidcUser.getIdToken(), "email");
    }
}
