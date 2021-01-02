package net.pet.auth_server.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class Oauth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private RedirectStrategy redirectStrategy;
    @Autowired
    private DefaultTokenServices defaultTokenServices;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        if (authentication.getPrincipal() instanceof DefaultOidcUser) {
            /*OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) authentication;
            OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(new OAuth2Request(authenticationToken.getAuthorizedClientRegistrationId()), authentication);
            OAuth2AccessToken accessToken = defaultTokenServices.getAccessToken(authenticationToken);
            Cookie cookie = new Cookie("access_token", accessToken.getValue());
            httpServletResponse.addCookie(cookie);
            redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "http://localhost:8081/");*/
        }
    }
}
