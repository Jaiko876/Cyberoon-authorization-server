package net.pet.auth_server.service.utils.mapper;

import net.pet.auth_server.domain.dto.RegistrationModel;
import net.pet.auth_server.domain.dto.UserModel;
import net.pet.auth_server.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User map(RegistrationModel registrationModel);

    UserModel map(User user);
}
