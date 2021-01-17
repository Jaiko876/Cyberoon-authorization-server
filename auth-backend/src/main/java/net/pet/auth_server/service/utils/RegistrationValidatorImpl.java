package net.pet.auth_server.service.utils;

import net.pet.auth_server.domain.dto.RegistrationModel;
import net.pet.auth_server.domain.enumerated.ErrorCode;
import net.pet.auth_server.service.api.RegistrationValidator;
import net.pet.auth_server.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationValidatorImpl implements RegistrationValidator {

    private final UserRepository userRepository;

    @Autowired
    public RegistrationValidatorImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validate(RegistrationModel model) {
        nullCheck(model);
        uniqueEmailCheck(model);
        uniqueUsernameCheck(model);
    }

    private void nullCheck(RegistrationModel model) {
        if (model.getEmail() == null || model.getPassword() == null || model.getUsername() == null)
            throw new IllegalArgumentException(ErrorCode.ILLEGAL_ARGUMENTS.name());
    }

    private void uniqueEmailCheck(RegistrationModel model) {
        if (userRepository.existsByEmail(model.getEmail()))
            throw new IllegalArgumentException(ErrorCode.WRONG_EMAIL.name());
    }

    private void uniqueUsernameCheck(RegistrationModel model) {
        if (userRepository.existsByUsername(model.getUsername()))
            throw new IllegalArgumentException(ErrorCode.WRONG_USERNAME.name());
    }
}
