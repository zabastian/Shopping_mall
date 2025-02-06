package com.example.shopping_mall.shoppingMall.service;

import com.example.shopping_mall.converter.TypeConverter;
import com.example.shopping_mall.entity.shoppingMall.ShoppingMall;
import com.example.shopping_mall.shoppingMall.dto.ShoppingMallCursorInquiryResponseDto;
import com.example.shopping_mall.shoppingMall.dto.ShoppingMallDto;
import com.example.shopping_mall.shoppingMall.repository.ShoppingMallQueryRepository;
import com.example.shopping_mall.shoppingMall.repository.ShoppingMallRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ShoppingMallService {

    private final ShoppingMallRepository shoppingMallRepository;
    private final TypeConverter typeConverter;
    private final ShoppingMallQueryRepository shoppingMallQueryRepository;

    // 전체평가 점수 조회 + 업소상태 조회
    public List<ShoppingMallDto> shoppingMallSummary(
            Integer overallRating,
            String businessStatus
    ) {

        // 전체평점과 업소상태로 쇼핑몰 리스트 조회
        List<ShoppingMall> shoppingMallList
                = shoppingMallRepository.findAllByOverallRatingAndBusinessStatusOrderByMonitoringDateDesc(overallRating, businessStatus);

        List<ShoppingMallDto> shoppingMallDtos = shoppingMallList
                .stream()
                .map(ShoppingMallDto::convertToDto)
                .toList();

        return shoppingMallDtos;
    }


    // 전체평가 점수 조회 + 업소상태 조회(페이지네이션 적용)
    public List<ShoppingMallDto> pageShoppingMallSummary(
            Pageable pageable,
            Integer overallRating,
            String businessStatus
    ) {

        //페이징 처리 된 쇼핑몰 데이터 조회
        Page<ShoppingMall> shoppingMallPage = shoppingMallRepository.findAllByOverallRatingAndBusinessStatusOrderByMonitoringDateDesc(overallRating, businessStatus, pageable);

        //ShoppingMall -> ShoppingMallDto로 변환
        List<ShoppingMallDto> shoppingMallDtos = shoppingMallPage.stream()
                .map(ShoppingMallDto::convertToDto)
                .toList();

        return shoppingMallDtos;
    }


    // 전체평가 점수 조회 + 업소상태 조회(커서 기반 페이지네이션 적용)
    public ShoppingMallCursorInquiryResponseDto ShoppingMallRatingAndStatusInquiry(
            Integer overallRating,
            String businessStatus,
            LocalDate cursor,
            int size) {

        //QueryDSL로 데이터 조회
        List<ShoppingMall> shoppingMallList = shoppingMallQueryRepository.findShoppingMallByRatingAndStatusWithCursor(overallRating, businessStatus, cursor, size);

        //ShoppingMall -> ShoppingMallDto로 변환
//        List<ShoppingMallDto> shoppingMallDtos = convertToDtoList(shoppingMallList);

        List<ShoppingMallDto> shoppingMallDtos = shoppingMallList
                .stream()
                .map(ShoppingMallDto::convertToDto)
                .toList();

        //페이지의 마지막 모니터링 날짜
        LocalDate lastMonitoringDate = shoppingMallList.get(shoppingMallList.size() - 1).getMonitoringDate();

        return new ShoppingMallCursorInquiryResponseDto(shoppingMallDtos, lastMonitoringDate);
    }

    // BATCH 사이즈 100으로 지정
    private static final int BATCH_SIZE = 100;
    @Transactional
    public void readCsvByBufferedReader() throws IOException {
        List<ShoppingMall> shoppingMallEntities = new ArrayList<>();
        // 파일리더로 불러올 파일 경로 입력
        // 한글 인코딩 문제 발생 -> 불러오는 csv 파일에 UTF_8코드 추가
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\USER\\Desktop\\SeoulInternetShoppingMall.csv"), StandardCharsets.UTF_8))) {
            String line = ""; // 라인 변수 생성
            br.readLine(); // 첫 행 건너뛰기
//             데이터가 null이 아니라면 한 행씩 읽기
            while ((line = br.readLine()) != null) { // br.nextLine()
//                 쉼표로 데이터 구분하면서 읽기
                String[] splitLine = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
//                 읽은 데이터 dataList에 넣기
                List<String> dataList = Arrays.asList(splitLine);

//                 csv 파일 컬럼 순서대로 나열
                String businessName = dataList.get(0);
                String storeName = dataList.get(1);
                String domainName = dataList.get(2);
                String phoneNumber = dataList.get(3);
                String operatorEmail = dataList.get(4);
                String eCommerceLicense = dataList.get(5);
                String businessType = dataList.get(6);

//                String -> LocalDate 타입변환
                String firstReportDate = dataList.get(7);
                LocalDate convertedFirstReportDate = typeConverter.firstReportDateStringToLocalDate(firstReportDate);

                String companyAddress = dataList.get(8);
                String businessStatus = dataList.get(9);

//                String -> int 타입변환
                String overallRating = dataList.get(10);
                int convertedOverallRating = typeConverter.overallRatingStringToInt(overallRating);
                String businessInfoRating = dataList.get(11);
                int convertedBusinessInfoRating = typeConverter.businessInfoRatingStringToInt(businessInfoRating);
                String withdrawalPolicyRating = dataList.get(12);
                int convertedWithdrawalPolicyRating = typeConverter.withdrawalPolicyRatingStringToInt(withdrawalPolicyRating);
                String paymentMethodRating = dataList.get(13);
                int convertedPaymentMethodRating = typeConverter.paymentMethodRatingStringToInt(paymentMethodRating);
                String termsOfServiceRating = dataList.get(14);
                int convertedTermsOfServiceRating = typeConverter.termsOfServiceRatingStringToInt(termsOfServiceRating);
                String privacySecurityRating = dataList.get(15);
                int convertedPrivacySecurityRating = typeConverter.privacySecurityRatingStringToInt(privacySecurityRating);

                String mainProducts = dataList.get(16);
                String withdrawalPossible = dataList.get(17);
                String requiredHomepageInfo = dataList.get(18);
                String paymentMethods = dataList.get(19);
                String termsCompliance = dataList.get(20);
                String privacyPolicy = dataList.get(21);
                String extraPersonalInfoRequest = dataList.get(22);
                String purchaseSafetyService = dataList.get(23);
                String securityServer = dataList.get(24);
                String certificationMarks = dataList.get(25);
                String estimatedDeliveryDisplay = dataList.get(26);
                String withdrawalShippingFee = dataList.get(27);
                String customerComplaintBoard = dataList.get(28);
                String membershipCancellation = dataList.get(29);
                String siteEstablishmentYear = dataList.get(30);
//                                String -> LocalDate 타입변환
                String monitoringDate = dataList.get(31);
                LocalDate convertedMonitoringDate = typeConverter.monitoringDateStringToLocalDate(monitoringDate);

//                                 쇼핑몰 엔티티
                ShoppingMall ShoppingMall = new ShoppingMall(
                        businessName, storeName, domainName, phoneNumber, operatorEmail,
                        eCommerceLicense, businessType, convertedFirstReportDate, companyAddress,
                        businessStatus, convertedOverallRating, convertedBusinessInfoRating, convertedWithdrawalPolicyRating,
                        convertedPaymentMethodRating, convertedTermsOfServiceRating, convertedPrivacySecurityRating, mainProducts,
                        withdrawalPossible, requiredHomepageInfo, paymentMethods, termsCompliance,
                        privacyPolicy, extraPersonalInfoRequest, purchaseSafetyService, securityServer,
                        certificationMarks, estimatedDeliveryDisplay, withdrawalShippingFee, customerComplaintBoard,
                        membershipCancellation, siteEstablishmentYear, convertedMonitoringDate);

//                엔티티 데이터 하나씩 모아서 100개 채우기위한 adding
                shoppingMallEntities.add(ShoppingMall);
//                 100개가 쌓이면 레포지에 저장 후 초기화 하고 반복
                if (shoppingMallEntities.size() == BATCH_SIZE) {
                    shoppingMallRepository.saveAll(shoppingMallEntities);
                    shoppingMallEntities.clear();
                }
//                모든 데이터 100개씩 저장 후 남은 100개 보다 적은 수의 데이터를 저장
            } if (shoppingMallEntities.size() > 0) {
                shoppingMallRepository.saveAll(shoppingMallEntities);
            }
        }
    }
}
