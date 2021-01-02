package net.pet.auth_server.service.api;

import net.pet.auth_server.domain.dto.UserModel;
import net.pet.auth_server.domain.entity.User;

public interface RegistrationService {
    User register(User user);
    User registerThirdPartyUser(UserModel userModel);
}
