package com.reant.boat.controller.auth;

import com.reant.boat.domain.dto.request.auth.UserRegisterRequestDto;
import com.reant.boat.domain.dto.request.auth.UserVerifyEmailRequestDto;
import com.reant.boat.domain.dto.response.auth.UserVerifyEmailResponse;
import com.reant.boat.service.auth.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "${boat.api}/auth")
public class UserController {
    private final UserService userService;

    @PostMapping("/verify-email")
    public String verifyEmail(@RequestBody UserVerifyEmailRequestDto request){
        UserVerifyEmailResponse response;

        String code = userService.verifyEmail(request);
        return code;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterRequestDto request){
        userService.register(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
