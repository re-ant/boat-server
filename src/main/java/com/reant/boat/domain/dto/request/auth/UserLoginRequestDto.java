package com.reant.boat.domain.dto.request.auth;

import lombok.Data;

@Data
public class UserLoginRequestDto {
    private String id;
    private String password;
}