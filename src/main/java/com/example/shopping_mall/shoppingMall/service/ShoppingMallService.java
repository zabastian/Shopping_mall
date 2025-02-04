package com.example.shopping_mall.shoppingMall.service;

import com.example.shopping_mall.entity.shoppingMall.ShoppingMall;
import com.example.shopping_mall.shoppingMall.dto.ShoppingMallDto;
import com.example.shopping_mall.shoppingMall.repository.ShoppingMallRepository;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingMallService {

    private final ShoppingMallRepository shoppingMallRepository;

    // 전체평가 점수 조회 + 업소상태 조회
    public List<ShoppingMallDto> shoppingMallSummary(Integer overallRating, String businessStatus) {

        // 전체평점과 업소상태로 쇼핑몰 리스트 조회
        List<ShoppingMall> shoppingMallList
                = shoppingMallRepository.findAllByOverallRatingAndBusinessStatusOrderByMonitoringDateDesc(overallRating, businessStatus);

        //ShoppingMall -> ShoppingMallDto로 변환
        List<ShoppingMallDto> shoppingMallDtos = convertToDtoList(shoppingMallList);

        return shoppingMallDtos;
    }


    //ShoppingMall -> ShoppingMallDto로 변환 메서드
    private List<ShoppingMallDto> convertToDtoList(List<ShoppingMall> shoppingMallList) {
        return shoppingMallList.stream().map(shoppingMall -> new ShoppingMallDto(
                        shoppingMall.getShoppingMallId(),
                        shoppingMall.getBusinessName(),
                        shoppingMall.getStoreName(),
                        shoppingMall.getOverallRating(),
                        shoppingMall.getDomainName(),
                        shoppingMall.getPhoneNumber(),
                        shoppingMall.getOperatorEmail(),
                        shoppingMall.getBusinessStatus(),
                        shoppingMall.getFirstReportDate(),
                        shoppingMall.getMonitoringDate()))
                .collect(Collectors.toList());
    }
}
