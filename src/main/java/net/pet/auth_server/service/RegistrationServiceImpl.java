package net.pet.auth_server.service;

import net.pet.auth_server.domain.dto.UserModel;
import net.pet.auth_server.domain.entity.User;
import net.pet.auth_server.service.api.RegistrationService;
import net.pet.auth_server.service.repository.UserRepository;
import net.pet.auth_server.service.utils.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;

    @Autowired
    public RegistrationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) {
        return userRepository.save(user);
    }

    @Override
    public User registerThirdPartyUser(UserModel userModel) {
        User user = UserMapper.INSTANCE.mapThirdPartyModel(userModel);
        return null;
    }
}
