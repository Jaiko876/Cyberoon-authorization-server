package net.pet.auth_server.service.utils;

import net.pet.auth_server.domain.dto.RegistrationModel;
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
            throw new IllegalArgumentException("Required fields can't be null");
    }

    private void uniqueEmailCheck(RegistrationModel model) {
        if (userRepository.findByEmail(model.getEmail()).isPresent())
            throw new IllegalArgumentException("Email must be unique");
    }

    private void uniqueUsernameCheck(RegistrationModel model) {
        if (userRepository.findByUsername(model.getUsername()).isPresent())
            throw new IllegalArgumentException("Username must be unique");
    }
}
