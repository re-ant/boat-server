package com.reant.boat.domain.dto.response.auth;


import com.reant.boat.domain.dto.request.auth.UserVerifyEmailRequestDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class UserVerifyEmailResponse<D> {
    private String result;
    private String message;
    private D data;

    public static <D> UserVerifyEmailResponse<D> ofSuccess(String message, D data) {
        return new UserVerifyEmailResponse<>("SUCCESS", message, data);
    }

    public static <D> UserVerifyEmailResponse<D> ofFail(String message, D data) {
        return new UserVerifyEmailResponse<>("FAIL", message, data);
    }
}
