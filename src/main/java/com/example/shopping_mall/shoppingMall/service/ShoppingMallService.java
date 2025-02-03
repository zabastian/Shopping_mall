package com.example.shopping_mall.shoppingMall.service;

import com.example.shopping_mall.shoppingMall.repository.ShoppingMallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingMallService {

    private final ShoppingMallRepository shoppingMallRepository;

}
