package com.example.shopping_mall.entity.shoppingMall;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
public class ShoppingMall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shoppingMallId;

    @Column(name = "business_name", length = 50)
    private String businessName; // 상호

    @Column(name = "store_name", length = 50)
    private String storeName; // 쇼핑몰명

    @Column(name = "domain_name", length = 100)
    private String domainName; // 도메인명

    @Column(name = "phone_number", length = 50)
    private String phoneNumber; // 전화번호

    @Column(name = "operator_email", length = 100)
    private String operatorEmail; // 운영자이메일

    @Column(name = "e_commerce_license")
    private int eCommerceLicense; // 통신판매번호

    @Column(name = "business_type", length = 50)
    private String businessType; // 영업형태

    @Column(name = "first_report_date")
    private LocalDate firstReportDate; // 최초신고일자

    @Column(name = "company_address", length = 100)
    private String companyAddress; // 회사주소

    @Column(name = "business_status", length = 50)
    private String businessStatus; // 업소상태

    @Column(name = "overall_rating")
    private int overallRating; // 전체평가

    @Column(name = "business_info_rating")
    private int businessInfoRating; // 사업자정보표시평가

    @Column(name = "withdrawal_policy_rating")
    private int withdrawalPolicyRating; // 청약철회평가

    @Column(name = "payment_method_rating ")
    private int paymentMethodRating; // 결재방법평가

    @Column(name = "terms_of_service_rating")
    private int termsOfServiceRating; // 이용약관평가

    @Column(name = "privacy_security_rating")
    private int privacySecurityRating; // 개인정보보안평가

    @Column(name = "main_products", length = 50)
    private String mainProducts; // 주요취급품목

    @Column(name = "withdrawal_possible", length = 50)
    private String withdrawalPossible; //청약철회가능여부

    @Column(name = "required_homepage_info", length = 100)
    private String requiredHomepageInfo; // 초기화면필수항목중표시사항

    @Column(name = "payment_methods", length = 100)
    private String paymentMethods; // 결제방법

    @Column(name = "terms_compliance", length = 100)
    private String termsCompliance; // 이용약관준수정도

    @Column(name = "privacy_policy", length = 100)
    private String PrivacyPolicy; // 개인정보취급방침

    @Column(name = "extra_personal_info_request", length = 50)
    private String extraPersonalInfoRequest; // 표준약관이상개인정보항목요구

    @Column(name = "purchase_safety_service", length = 50)
    private String purchaseSafetyService; // 구매안전서비스

    @Column(name = "security_server", length = 50)
    private String SecurityServer; // 보안서버설치

    @Column(name = "certification_marks", length = 50)
    private String certificationMarks; // 인증마크

    @Column(name = "estimated_delivery_display", length = 50)
    private String estimatedDeliveryDisplay; // 배송예정일표시

    @Column(name = "withdrawal_shipping_fee", length = 50)
    private String withdrawalShippingFee; // 철회시배송비부담여부

    @Column(name = "customer_complaInt_board", length = 50)
    private String customerComplaintBoard; // 고객불만게시판운영

    @Column(name = "membership_cancellation", length = 50)
    private String membershipCancellation; // 회원탈퇴방법

    @Column(name = "site_establishment_year")
    private Integer siteEstablishmentYear; // 사이트개설년도

    @Column(name = "monitoring_date")
    private LocalDate monitoringDate; // 모니터링 날짜


}
