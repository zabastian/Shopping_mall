package com.example.shopping_mall.auth.controller;

import com.example.shopping_mall.auth.dto.request.UserCreateRequestDto;
import com.example.shopping_mall.auth.dto.request.UserLoginRequestDto;
import com.example.shopping_mall.auth.dto.response.UserCreateResponseDto;
import com.example.shopping_mall.auth.dto.response.UserLoginResponseDto;
import com.example.shopping_mall.auth.service.AuthService;
import com.example.shopping_mall.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    //회원가입
    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<UserCreateResponseDto>> createUserAPI (@RequestBody UserCreateRequestDto requestDto) {

        UserCreateResponseDto responseDto = authService.createUser(requestDto);

        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.toString(), "회원가입이 완료되었습니다.", responseDto), HttpStatus.CREATED);
    }

}
