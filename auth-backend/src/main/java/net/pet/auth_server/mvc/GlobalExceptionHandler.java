package net.pet.auth_server.mvc;

import net.pet.auth_server.domain.dto.ErrorModel;
import net.pet.auth_server.domain.enumerated.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { BadCredentialsException.class })
    protected ResponseEntity<ErrorModel> handleBadCredentials() {
        return new ResponseEntity<>(new ErrorModel(ErrorCode.BAD_CREDENTIALS), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = { IllegalArgumentException.class })
    protected ResponseEntity<ErrorModel> handle(IllegalArgumentException ex) {
        if (ex == null)
            throw new RuntimeException();
        return new ResponseEntity<>(new ErrorModel(ErrorCode.valueOf(ex.getMessage())), HttpStatus.BAD_REQUEST);
    }
}
