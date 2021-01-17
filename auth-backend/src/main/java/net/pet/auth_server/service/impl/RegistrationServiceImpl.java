package net.pet.auth_server.service.impl;

import net.pet.auth_server.domain.dto.RegistrationModel;
import net.pet.auth_server.domain.dto.UserModel;
import net.pet.auth_server.domain.entity.User;
import net.pet.auth_server.domain.oidc.AuthOidcUser;
import net.pet.auth_server.service.api.RegistrationService;
import net.pet.auth_server.service.api.RegistrationValidator;
import net.pet.auth_server.service.repository.UserRepository;
import net.pet.auth_server.service.utils.RegistrationValidatorImpl;
import net.pet.auth_server.service.utils.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

    private static final UserMapper USER_MAPPER = UserMapper.INSTANCE;

    private final UserRepository userRepository;
    private final RegistrationValidator validator;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationServiceImpl(UserRepository userRepository, RegistrationValidatorImpl validator, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.validator = validator;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserModel register(RegistrationModel registrationModel) {
        validator.validate(registrationModel);
        User autoLoginUserCredentials = USER_MAPPER.map(registrationModel);

        encodePassword(registrationModel);
        User user = USER_MAPPER.map(registrationModel);
        userRepository.save(user);

        //Если регистрация прошла успешно, то аутентифицируем пользователя, передавая модель без encoded пароля
        return USER_MAPPER.map(autoLoginUserCredentials);
    }

    @Override
    public void registerThirdPartyUser(AuthOidcUser userModel) {
        RegistrationModel registrationModel = USER_MAPPER.map(userModel);
        if (userRepository.existsByUsername(registrationModel.getUsername()))
            return;
        registrationModel.setPassword("N/A");
        register(registrationModel);
    }

    private void encodePassword(RegistrationModel registrationModel) {
        if (!(registrationModel.getPassword() != null && registrationModel.getPassword().contains("N/A")))
            registrationModel.setPassword(passwordEncoder.encode(registrationModel.getPassword()));
    }
}
