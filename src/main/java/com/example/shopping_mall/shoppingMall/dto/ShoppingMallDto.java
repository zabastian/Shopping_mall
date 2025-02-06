package com.example.shopping_mall.shoppingMall.dto;

import com.example.shopping_mall.entity.shoppingMall.ShoppingMall;

import java.time.LocalDate;
import java.util.List;

public record ShoppingMallDto(
        Long shoppingMallId,
        String businessName,
        String storeName,
        int overall_rating,
        String domainName,
        String phoneNumber,
        String operatorEmail,
        String business_status,
        LocalDate firstReportDate,
        LocalDate monitoringDate
) {
    public static ShoppingMallDto convertToDto(ShoppingMall shoppingMall) {
        return new ShoppingMallDto(
                shoppingMall.getShoppingMallId(),
                shoppingMall.getBusinessName(),
                shoppingMall.getStoreName(),
                shoppingMall.getOverallRating(),
                shoppingMall.getDomainName(),
                shoppingMall.getPhoneNumber(),
                shoppingMall.getOperatorEmail(),
                shoppingMall.getBusinessStatus(),
                shoppingMall.getFirstReportDate(),
                shoppingMall.getMonitoringDate()
        );
    }
}
