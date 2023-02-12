package com.reant.boat.handler;

import com.reant.boat.domain.enums.error.ErrorCode;
import com.reant.boat.exceiption.auth.DuplicateUserIdException;
import com.reant.boat.exceiption.auth.DuplicateUserNameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class BoatExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> unknownException(Exception e) {
        log.error("UNKNOWN EXCEPTION : {}\n{}", e.getClass().getSimpleName(), e.getCause());
        return new ResponseEntity<>(
                e.getClass().getSimpleName() + " : " + e.getCause(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );

    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity badCredentialException() {
        return new ResponseEntity("올바르지 않은 계정 정보입니다.",HttpStatus.FORBIDDEN); // 403
    }

    @ExceptionHandler(DuplicateUserIdException.class)
    public ResponseEntity duplicateUserIdException(DuplicateUserIdException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.valueOf(ErrorCode.DUPLICATE_USER_ID.getValue()));
    }

    @ExceptionHandler(DuplicateUserNameException.class)
    public ResponseEntity duplicateUserNameException(DuplicateUserNameException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.valueOf(ErrorCode.DUPLICATE_USER_NAME.getValue()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity methodNotSupportedException() {
        return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
    }
}

