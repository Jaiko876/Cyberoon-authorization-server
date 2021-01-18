package net.pet.auth_server.rest;

import net.pet.auth_server.domain.dto.UserModel;
import net.pet.auth_server.service.api.AuthenticationProviderHandler;
import net.pet.auth_server.service.api.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class LoginController {

    private final AuthenticationService authenticationService;
    private final AuthenticationProviderHandler authenticationProviderHandler;

    @Autowired
    public LoginController(AuthenticationService authenticationService, AuthenticationProviderHandler authenticationProviderHandler) {
        this.authenticationService = authenticationService;
        this.authenticationProviderHandler = authenticationProviderHandler;
    }

    @PostMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response, @RequestBody UserModel user) throws ServletException, IOException, BadCredentialsException {
        Authentication authentication = authenticationService.authenticate(user);
        authenticationProviderHandler.onAuthenticationSuccess(request, response, authentication);
    }
}
