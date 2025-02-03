package com.example.shopping_mall.shoppingMall.controller;

import com.example.shopping_mall.shoppingMall.service.ShoppingMallService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shopping-mall")
@RequiredArgsConstructor
public class ShoppingMallController {

    private final ShoppingMallService shoppingMallService;

}
