package net.pet.auth_server.service;

import net.pet.auth_server.domain.dto.RegistrationModel;
import net.pet.auth_server.domain.dto.UserModel;
import net.pet.auth_server.domain.entity.User;
import net.pet.auth_server.service.api.RegistrationService;
import net.pet.auth_server.service.repository.UserRepository;
import net.pet.auth_server.service.utils.RegistrationValidatorImpl;
import net.pet.auth_server.service.utils.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private static final UserMapper USER_MAPPER = UserMapper.INSTANCE;

    private final UserRepository userRepository;
    private final RegistrationValidatorImpl validator;

    @Autowired
    public RegistrationServiceImpl(UserRepository userRepository, RegistrationValidatorImpl validator) {
        this.userRepository = userRepository;
        this.validator = validator;
    }


    @Override
    public UserModel register(RegistrationModel registrationModel) {
        validator.validate(registrationModel);
        User user = USER_MAPPER.map(registrationModel);
        userRepository.save(user);
        return USER_MAPPER.map(user);
    }
}
