package net.pet.auth_server.service.api;

import net.pet.auth_server.domain.dto.UserModel;
import org.springframework.security.core.Authentication;

public interface AuthenticationService {
    Authentication authenticate(UserModel userModel);
}
