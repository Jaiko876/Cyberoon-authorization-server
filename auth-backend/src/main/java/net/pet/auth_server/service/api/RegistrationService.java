package net.pet.auth_server.service.api;

import net.pet.auth_server.domain.dto.RegistrationModel;
import net.pet.auth_server.domain.dto.UserModel;

public interface RegistrationService {
    UserModel register(RegistrationModel registrationModel);
    //User registerThirdPartyUser(UserModel userModel);
}