package com.example.shopping_mall.auth.service;

import com.example.shopping_mall.auth.dto.request.UserCreateRequestDto;
import com.example.shopping_mall.auth.dto.request.UserLoginRequestDto;
import com.example.shopping_mall.auth.dto.response.UserCreateResponseDto;
import com.example.shopping_mall.auth.dto.response.UserLoginResponseDto;
import com.example.shopping_mall.auth.repository.AuthRepository;
import com.example.shopping_mall.common.config.PasswordEncoder;
import com.example.shopping_mall.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    public UserCreateResponseDto createUser (UserCreateRequestDto requestDto) {
        //이메일 중복 확인
        if(authRepository.findUserByEmail(requestDto.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다.");
        }

        //비밀번호 암호화
        String encodePassword = passwordEncoder.encode(requestDto.getPassword());

        //유저 생성
        User createUser = new User(requestDto.getEmail(), encodePassword);

        //생성 된 유저 저장
        User savedUser = authRepository.save(createUser);

        return new UserCreateResponseDto(savedUser.getUserId(), savedUser.getEmail());
    }

}
