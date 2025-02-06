package com.example.shopping_mall.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCreateResponseDto {

    private Long id;
    private String email;

}
