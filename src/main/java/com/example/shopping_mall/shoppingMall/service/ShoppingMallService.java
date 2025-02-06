package com.example.shopping_mall.shoppingMall.service;

import com.example.shopping_mall.converter.TypeConverter;
import com.example.shopping_mall.entity.shoppingMall.ShoppingMall;
import com.example.shopping_mall.shoppingMall.dto.ShoppingMallDto;
import com.example.shopping_mall.shoppingMall.repository.ShoppingMallRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShoppingMallService {

    private final ShoppingMallRepository shoppingMallRepository;
    private final TypeConverter typeConverter;

    //전체평가 조회
    public List<ShoppingMallDto> overallRatingInquiry(int overallRating) {

        //입력받은 별점 확인
        if (0 > overallRating || overallRating > 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "평가 별점은 최소 0점, 최대 3점입니다.");
        }

        //ShoppingMall 조회
        List<ShoppingMall> shoppingMallList = shoppingMallRepository.findAllByOverallRating(overallRating);

        // ShoppingMall -> ShoppingMallDto로 변환
        List<ShoppingMallDto> shoppingMallDtos = shoppingMallList.stream().map(shoppingMall ->
                        new ShoppingMallDto(
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

        return shoppingMallDtos;
    }

    //업소 상태 조회
    public List<ShoppingMallDto> businessStatusInquiry(String businessStatus) {

        List<ShoppingMall> shoppingMallList = shoppingMallRepository.findAllByBusinessStatusOrThrowException(businessStatus);

        // ShoppingMall -> ShoppingMallDto로 변환
        List<ShoppingMallDto> shoppingMallDtos = shoppingMallList.stream().map(shoppingMall ->
                        new ShoppingMallDto(
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

        return shoppingMallDtos;
    }

    //모니터링 날짜 내림차순 조회
    public List<ShoppingMallDto> monitoringDateDescendingOrder() {

        List<ShoppingMall> shoppingMallList = shoppingMallRepository.findAllByOrderByMonitoringDateDesc();

        // ShoppingMall -> ShoppingMallDto로 변환
        List<ShoppingMallDto> shoppingMallDtos = shoppingMallList.stream().map(shoppingMall ->
                        new ShoppingMallDto(
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

        return shoppingMallDtos;
    }

    private static final int BATCH_SIZE = 100;
    @Transactional
    public void readCsvByBufferedReader() throws IOException {
        // csv데이터는 행과 열로 이루어져있기 때문에 다루기 적합한 구조는 2차원 배열
        // 하지만 레포지에 저장할때 타일불일치 문제가 생겨서 1차원으로 바꿈
        // 레포지에 저장할때는 1차원으로 저장되는게 맞다.
        List<ShoppingMall> shoppingMallEntities = new ArrayList<>();
        // 파일리더로 불러올 파일 경로 입력
        // 한글이 깨져서 읽어져서 한글도 제대로 읽을 수 있는 기능을 추가
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\USER\\Desktop\\SeoulInternetShoppingMall.csv"), StandardCharsets.UTF_8))) {
            String line = ""; // 라인 변수 생성
            br.readLine(); // 첫 행 건너뛰기
//             데이터가 null이 아니라면 한 행씩 읽기
            while ((line = br.readLine()) != null) { // br.nextLine()
//                 쉼표로 데이터 구분하면서 읽기
                String[] splitLine = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
//                 읽은 데이터 dataList에 넣기
                List<String> dataList = Arrays.asList(splitLine);
//                 1번째 열 읽기
                String businessName = dataList.get(0);
//                 2번째 열 읽기
                String storeName = dataList.get(1);
//                 3번째 열 읽기
                String domainName = dataList.get(2);
//                 4번째 열 읽기
                String phoneNumber = dataList.get(3);
                String operatorEmail = dataList.get(4);
                String eCommerceLicense = dataList.get(5);
                String businessType = dataList.get(6);
                String firstReportDate = dataList.get(7);
                LocalDate convertedFirstReportDate = typeConverter.firstReportDateStringToLocalDate(firstReportDate);

                String companyAddress = dataList.get(8);
                String businessStatus = dataList.get(9);

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
//                100개 저장 후 남은 100개 보다 적은 수의 데이터를 저장
            } if (shoppingMallEntities.size() > 0) {
                shoppingMallRepository.saveAll(shoppingMallEntities);
            }
        }
    }
}



