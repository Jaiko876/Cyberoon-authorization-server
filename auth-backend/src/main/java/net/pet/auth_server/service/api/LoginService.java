package net.pet.auth_server.service.api;

import net.pet.auth_server.domain.dto.UserModel;

public interface LoginService {
    void login(UserModel userModel);
}
