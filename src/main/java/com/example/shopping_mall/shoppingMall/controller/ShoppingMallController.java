package com.example.shopping_mall.shoppingMall.controller;

import com.example.shopping_mall.common.dto.ApiResponse;
import com.example.shopping_mall.shoppingMall.dto.ShoppingMallDto;
import com.example.shopping_mall.shoppingMall.service.ShoppingMallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shopping-mall")
@RequiredArgsConstructor
public class ShoppingMallController {

    private final ShoppingMallService shoppingMallService;

    // 전체평가 점수 조회 + 업소상태 조회
    @GetMapping("/status-ratings")
    public ResponseEntity<ApiResponse<List<ShoppingMallDto>>> shoppingMallSummaryAPI(
            @RequestParam(value = "rating", required = false) Integer overallRating, // required = false -> 해당 파라미터는 선택사항
            @RequestParam(value = "status", required = false) String businessStatus) {

        List<ShoppingMallDto> shoppingMallDtos = shoppingMallService.shoppingMallSummary(overallRating, businessStatus);

        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.toString(), "쇼핑몰 조회 성공", shoppingMallDtos));
    }

}
