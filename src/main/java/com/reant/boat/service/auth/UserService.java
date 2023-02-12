package com.reant.boat.service.auth;

import com.reant.boat.domain.dto.request.auth.UserRegisterRequestDto;
import com.reant.boat.domain.dto.request.auth.UserVerifyEmailRequestDto;
import com.reant.boat.domain.entity.user.User;
import com.reant.boat.exceiption.auth.DuplicateUserIdException;
import com.reant.boat.exceiption.auth.DuplicateUserNameException;
import com.reant.boat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String register(UserRegisterRequestDto userRegisterRequestDto) {
        checkRequestInfoIsDuplicated(userRegisterRequestDto);
        User user = userRepository.save(userRegisterRequestDto.toEntity());
        user.encodePassword(passwordEncoder);

        return user.getUserId();
    }

    private void checkRequestInfoIsDuplicated(UserRegisterRequestDto userRegisterRequestDto) {
        if(userRepository.findByUserId(userRegisterRequestDto.getId()).isPresent()) {
            throw new DuplicateUserIdException("이미 존재하는 UserID 입니다.");
        }

        if(userRepository.findByUsername(userRegisterRequestDto.getUsername()).isPresent()) {
            throw new DuplicateUserNameException("이미 존재하는 UserName 입니다.");
        }
    }

    public String verifyEmail(UserVerifyEmailRequestDto userVerifyEmailRequestDto) {
        return generateRandomCode();
    }

    public String generateRandomCode() {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 10;

        return new Random().ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
