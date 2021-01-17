package net.pet.auth_server.rest;

import net.pet.auth_server.service.api.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

   /* @PostMapping("/registration")
    public ResponseEntity<Void> registration(RegistrationModel registrationModel) {
        UserModel userModel = registrationService.register(registrationModel);
        return new ResponseEntity<>(userModel, HttpStatus.OK);
    }*/
}
