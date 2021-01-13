package net.pet.auth_server.service.api;

import net.pet.auth_server.domain.dto.RegistrationModel;

public interface RegistrationValidator {
    void validate(RegistrationModel model);
}
