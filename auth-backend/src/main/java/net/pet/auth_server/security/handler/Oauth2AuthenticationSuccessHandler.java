package net.pet.auth_server.security.handler;

import net.pet.auth_server.domain.oidc.AuthOidcUser;
import net.pet.auth_server.service.api.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Oauth2AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private RegistrationService registrationService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        if (authentication.getPrincipal() instanceof AuthOidcUser) {
            AuthOidcUser authOidcUser = (AuthOidcUser) authentication.getPrincipal();
            registrationService.registerThirdPartyUser(authOidcUser);
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
