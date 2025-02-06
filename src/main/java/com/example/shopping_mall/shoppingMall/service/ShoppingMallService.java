package com.example.shopping_mall.shoppingMall.service;

import com.example.shopping_mall.entity.shoppingMall.ShoppingMall;
import com.example.shopping_mall.shoppingMall.dto.ShoppingMallCursorInquiryResponseDto;
import com.example.shopping_mall.shoppingMall.dto.ShoppingMallDto;
import com.example.shopping_mall.shoppingMall.repository.ShoppingMallQueryRepository;
import com.example.shopping_mall.shoppingMall.repository.ShoppingMallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingMallService {

    private final ShoppingMallRepository shoppingMallRepository;
    private final ShoppingMallQueryRepository shoppingMallQueryRepository;

    // 전체평가 점수 조회 + 업소상태 조회
    public List<ShoppingMallDto> shoppingMallSummary(Integer overallRating, String businessStatus) {

        // 전체평점과 업소상태로 쇼핑몰 리스트 조회
        List<ShoppingMall> shoppingMallList
                = shoppingMallRepository.findAllByOverallRatingAndBusinessStatusOrderByMonitoringDateDesc(overallRating, businessStatus);

        //ShoppingMall -> ShoppingMallDto로 변환
        List<ShoppingMallDto> shoppingMallDtos = convertToDtoList(shoppingMallList);

        return shoppingMallDtos;
    }


    // 전체평가 점수 조회 + 업소상태 조회(페이지네이션 적용)
    public List<ShoppingMallDto> pageShoppingMallSummary(Pageable pageable, Integer overallRating, String businessStatus) {

        //페이징 처리 된 쇼핑몰 데이터 조회
        Page<ShoppingMall> shoppingMallPage = shoppingMallRepository.findAllByOverallRatingAndBusinessStatusOrderByMonitoringDateDesc(overallRating, businessStatus, pageable);

        //getContent -> 입력받은 page값에 해당하는 데이터 목록을 List형태로 변환
        List<ShoppingMall> shoppingMalls = shoppingMallPage.getContent();

        //ShoppingMall -> ShoppingMallDto로 변환
        List<ShoppingMallDto> shoppingMallDtos = convertToDtoList(shoppingMalls);

        return shoppingMallDtos;
    }


    // 전체평가 점수 조회 + 업소상태 조회(커서 기반 페이지네이션 적용)
    public ShoppingMallCursorInquiryResponseDto ShoppingMallRatingAndStatusInquiry(Integer overallRating, String businessStatus, LocalDate cursor, int size) {

        //QueryDSL로 데이터 조회
        List<ShoppingMall> shoppingMallList = shoppingMallQueryRepository.findShoppingMallByRatingAndStatusWithCursor(overallRating, businessStatus, cursor, size);

        //ShoppingMall -> ShoppingMallDto로 변환
        List<ShoppingMallDto> shoppingMallDtos = convertToDtoList(shoppingMallList);

        //페이지의 마지막 모니터링 날짜
        LocalDate lastMonitoringDate = shoppingMallList.get(shoppingMallList.size() - 1).getMonitoringDate();

        return new ShoppingMallCursorInquiryResponseDto(shoppingMallDtos, lastMonitoringDate);
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

