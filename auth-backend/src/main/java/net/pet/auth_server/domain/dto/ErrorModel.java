package net.pet.auth_server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.pet.auth_server.domain.enumerated.ErrorCode;

@Getter
@Setter
@AllArgsConstructor
public class ErrorModel {
    private ErrorCode code;
}
