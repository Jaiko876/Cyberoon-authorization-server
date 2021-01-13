package net.pet.auth_server.rest;

import net.pet.auth_server.domain.dto.RegistrationModel;
import net.pet.auth_server.domain.dto.UserModel;
import net.pet.auth_server.service.api.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/registration")
    public ResponseEntity<UserModel> registration(RegistrationModel registrationModel) {
        UserModel userModel = registrationService.register(registrationModel);
        return new ResponseEntity<>(userModel, HttpStatus.OK);
    }
}
