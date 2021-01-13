package net.pet.auth_server.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationModel {
    private String email;
    private String username;
    private String password;
}
