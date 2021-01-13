package net.pet.auth_server.domain.dto;

import lombok.Getter;
import lombok.Setter;
import net.pet.auth_server.domain.entity.Authority;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UserModel {
    private UUID id;
    private String username;
    private String password;
    private String email;
    private String surname;
    private String name;
    private Boolean enabled;
    private List<Authority> authorities;
}
