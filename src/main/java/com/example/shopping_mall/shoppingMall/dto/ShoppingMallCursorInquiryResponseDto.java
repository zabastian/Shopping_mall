package com.example.shopping_mall.shoppingMall.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class ShoppingMallCursorInquiryResponseDto {

    private List<ShoppingMallDto> shoppingMallList;
    private LocalDate nextCursor;
}
