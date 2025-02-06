package com.example.shopping_mall.entity.shoppingMall;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "shopping_mall")
public class ShoppingMall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shoppingMallId;

    @Lob
    @Column(name = "business_name", columnDefinition = "LONGTEXT")
    private String businessName; // 상호

    @Lob
    @Column(name = "store_name", columnDefinition = "MEDIUMTEXT")
    private String storeName; // 쇼핑몰명

    @Lob
    @Column(name = "domain_name", columnDefinition = "LONGTEXT")
    private String domainName; // 도메인명

    @Lob
    @Column(name = "phone_number", columnDefinition = "MEDIUMTEXT")
    private String phoneNumber; // 전화번호

    @Lob
    @Column(name = "operator_email", columnDefinition = "MEDIUMTEXT")
    private String operatorEmail; // 운영자이메일

    @Lob
    @Column(name = "e_commerce_license")
    private String eCommerceLicense; // 통신판매번호

    @Lob
    @Column(name = "business_type", columnDefinition = "MEDIUMTEXT")
    private String businessType; // 영업형태

    @Lob
    @Column(name = "first_report_date")
    private LocalDate firstReportDate; // 최초신고일자

    @Lob
    @Column(name = "company_address", columnDefinition = "MEDIUMTEXT")
    private String companyAddress; // 회사주소

    @Lob
    @Column(name = "business_status", columnDefinition = "MEDIUMTEXT")
    private String businessStatus; // 업소상태

    @Lob
    @Column(name = "overall_rating")
    private int overallRating; // 전체평가

    @Lob
    @Column(name = "business_info_rating")
    private int businessInfoRating; // 사업자정보표시평가

    @Lob
    @Column(name = "withdrawal_policy_rating")
    private int withdrawalPolicyRating; // 청약철회평가

    @Lob
    @Column(name = "payment_method_rating ")
    private int paymentMethodRating; // 결재방법평가

    @Lob
    @Column(name = "terms_of_service_rating")
    private int termsOfServiceRating; // 이용약관평가

    @Lob
    @Column(name = "privacy_security_rating")
    private int privacySecurityRating; // 개인정보보안평가

    @Lob
    @Column(name = "main_products", columnDefinition = "MEDIUMTEXT")
    private String mainProducts; // 주요취급품목

    @Lob
    @Column(name = "withdrawal_possible", columnDefinition = "MEDIUMTEXT")
    private String withdrawalPossible; //청약철회가능여부

    @Lob
    @Column(name = "required_homepage_info", columnDefinition = "MEDIUMTEXT")
    private String requiredHomepageInfo; // 초기화면필수항목중표시사항

    @Lob
    @Column(name = "payment_methods", columnDefinition = "MEDIUMTEXT")
    private String paymentMethods; // 결제방법

    @Lob
    @Column(name = "terms_compliance", columnDefinition = "MEDIUMTEXT")
    private String termsCompliance; // 이용약관준수정도

    @Lob
    @Column(name = "privacy_policy", columnDefinition = "MEDIUMTEXT")
    private String privacyPolicy; // 개인정보취급방침

    @Lob
    @Column(name = "extra_personal_info_request", columnDefinition = "MEDIUMTEXT")
    private String extraPersonalInfoRequest; // 표준약관이상개인정보항목요구

    @Lob
    @Column(name = "purchase_safety_service", columnDefinition = "MEDIUMTEXT")
    private String purchaseSafetyService; // 구매안전서비스

    @Lob
    @Column(name = "security_server", columnDefinition = "MEDIUMTEXT")
    private String securityServer; // 보안서버설치

    @Lob
    @Column(name = "certification_marks", columnDefinition = "MEDIUMTEXT")
    private String certificationMarks; // 인증마크

    @Lob
    @Column(name = "estimated_delivery_display", columnDefinition = "MEDIUMTEXT")
    private String estimatedDeliveryDisplay; // 배송예정일표시

    @Lob
    @Column(name = "withdrawal_shipping_fee", columnDefinition = "MEDIUMTEXT")
    private String withdrawalShippingFee; // 철회시배송비부담여부

    @Lob
    @Column(name = "customer_complaint_board", columnDefinition = "MEDIUMTEXT")
    private String customerComplaintBoard; // 고객불만게시판운영

    @Lob
    @Column(name = "membership_cancellation", columnDefinition = "MEDIUMTEXT")
    private String membershipCancellation; // 회원탈퇴방법

    @Lob
    @Column(name = "site_establishment_year")
    private String siteEstablishmentYear; // 사이트개설년도

    @Lob
    @Column(name = "monitoring_date")
    private LocalDate monitoringDate; // 모니터링 날짜

    public ShoppingMall(String businessName, String storeName, String domainName, String phoneNumber,
                        String operatorEmail, String eCommerceLicense, String businessType, LocalDate firstReportDate,
                        String companyAddress, String businessStatus, int overallRating, int businessInfoRating,
                        int withdrawalPolicyRating, int paymentMethodRating, int termsOfServiceRating, int privacySecurityRating,
                        String mainProducts, String withdrawalPossible, String requiredHomepageInfo, String paymentMethods,
                        String termsCompliance, String privacyPolicy, String extraPersonalInfoRequest, String purchaseSafetyService,
                        String securityServer, String certificationMarks, String estimatedDeliveryDisplay, String withdrawalShippingFee,
                        String customerComplaintBoard, String membershipCancellation, String siteEstablishmentYear, LocalDate monitoringDate
    ) {
        this.businessName = businessName;
        this.storeName = storeName;
        this.domainName = domainName;
        this.phoneNumber = phoneNumber;
        this.operatorEmail = operatorEmail;
        this.eCommerceLicense = eCommerceLicense;
        this.businessType = businessType;
        this.firstReportDate = firstReportDate;
        this.companyAddress = companyAddress;
        this.businessStatus = businessStatus;
        this.overallRating = overallRating;
        this.businessInfoRating = businessInfoRating;
        this.withdrawalPolicyRating = withdrawalPolicyRating;
        this.paymentMethodRating = paymentMethodRating;
        this.termsOfServiceRating = termsOfServiceRating;
        this.privacySecurityRating = privacySecurityRating;
        this.mainProducts = mainProducts;
        this.withdrawalPossible = withdrawalPossible;
        this.requiredHomepageInfo = requiredHomepageInfo;
        this.paymentMethods = paymentMethods;
        this.termsCompliance = termsCompliance;
        this.privacyPolicy = privacyPolicy;
        this.extraPersonalInfoRequest = extraPersonalInfoRequest;
        this.purchaseSafetyService = purchaseSafetyService;
        this.securityServer = securityServer;
        this.certificationMarks = certificationMarks;
        this.estimatedDeliveryDisplay = estimatedDeliveryDisplay;
        this.withdrawalShippingFee = withdrawalShippingFee;
        this.customerComplaintBoard = customerComplaintBoard;
        this.membershipCancellation = membershipCancellation;
        this.siteEstablishmentYear = siteEstablishmentYear;
        this.monitoringDate = monitoringDate;
    }


}
