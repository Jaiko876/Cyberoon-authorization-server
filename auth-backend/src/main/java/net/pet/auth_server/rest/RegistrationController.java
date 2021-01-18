package net.pet.auth_server.rest;

import net.pet.auth_server.domain.dto.RegistrationModel;
import net.pet.auth_server.domain.dto.UserModel;
import net.pet.auth_server.service.api.AuthenticationProviderHandler;
import net.pet.auth_server.service.api.AuthenticationService;
import net.pet.auth_server.service.api.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class RegistrationController {

    private final RegistrationService registrationService;
    private final AuthenticationService authenticationService;
    private final AuthenticationProviderHandler authenticationProviderHandler;

    @Autowired
    public RegistrationController(RegistrationService registrationService, AuthenticationService authenticationService, AuthenticationProviderHandler authenticationProviderHandler) {
        this.registrationService = registrationService;
        this.authenticationService = authenticationService;
        this.authenticationProviderHandler = authenticationProviderHandler;
    }

    @PostMapping("/registration")
    public void registration(HttpServletRequest request, HttpServletResponse response, @RequestBody RegistrationModel registrationModel) throws ServletException, IOException {
        UserModel registeredUser = registrationService.register(registrationModel);
        Authentication authentication = authenticationService.authenticate(registeredUser);
        authenticationProviderHandler.onAuthenticationSuccess(request, response, authentication);
    }
}
