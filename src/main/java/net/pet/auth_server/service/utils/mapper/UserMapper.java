package net.pet.auth_server.service.utils.mapper;

import net.pet.auth_server.domain.dto.UserModel;
import net.pet.auth_server.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User mapThirdPartyModel(UserModel userModel);
}
