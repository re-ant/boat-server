package com.reant.boat.domain.dto.request.auth;

import javax.validation.constraints.NotBlank;

import com.reant.boat.domain.entity.user.User;
import com.reant.boat.domain.enums.auth.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserRegisterRequestDto {

    @NotBlank(message = "이메일을 입력해주세요.")
    private String id;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    private String username;

    public User toEntity() {
        return User.builder()
                .userId(id)
                .password(password)
                .username(username)
                .role(Role.USER)
                .build();
    }

}