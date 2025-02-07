package com.example.shopping_mall.converter;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class TypeConverter {
    // String 타입 날짜를 LocalDate 타입으로 변경하는 메소드
    public LocalDate firstReportDateStringToLocalDate(String stringDate) {
        try {
            if (stringDate == null || stringDate.trim().isEmpty()) {
                return null;
            }
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(stringDate, dateTimeFormatter);
            return date;
        } catch (DateTimeParseException e) {
            System.err.println("날짜 변환 과정 오류: " + stringDate);
            return null;
        }
    }
    // String 타입 날짜를 LocalDate 타입으로 변경하는 메소드
    public LocalDate monitoringDateStringToLocalDate(String stringDate) {
        try {
            if (stringDate == null || stringDate.trim().isEmpty()) {
                return null;
            }
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(stringDate, dateTimeFormatter);
            return date;
        } catch (DateTimeParseException e) {
            System.out.println("날짜 변환 과정 오류: " + stringDate);
            return null;
        }
    }

    // String 타입 숫자를 int형 정수 타입으로 변경하는 메소드
    public Integer overallRatingStringToInt(String stringInt) {
        try {
            if (stringInt == null || stringInt.trim().isEmpty()) {
                return 0;
            }
            int rating = Integer.parseInt(stringInt.trim());
            return rating;
        } catch (NumberFormatException e) {
            System.out.println("숫자 변환 과정 오류: " + stringInt);
            return 0;
        }
    }
    // String 타입 숫자를 int형 정수 타입으로 변경하는 메소드
    public Integer businessInfoRatingStringToInt(String stringInt) {
        try {
            if (stringInt == null || stringInt.trim().isEmpty()) {
                return 0;
            }
            int rating = Integer.parseInt(stringInt.trim());
            return rating;
        } catch (NumberFormatException e) {
            System.out.println("숫자 변환 과정 오류: " + stringInt);
            return 0;
        }
    }
    // String 타입 숫자를 int형 정수 타입으로 변경하는 메소드
    public Integer withdrawalPolicyRatingStringToInt(String stringInt) {
        try {
            if (stringInt == null || stringInt.trim().isEmpty()) {
                return 0;
            }
            int rating = Integer.parseInt(stringInt.trim());
            return rating;
        } catch (NumberFormatException e) {
            System.out.println("숫자 변환 과정 오류: " + stringInt);
            return 0;
        }
    }
    // String 타입 숫자를 int형 정수 타입으로 변경하는 메소드
    public Integer paymentMethodRatingStringToInt(String stringInt) {
        try {
            if (stringInt == null || stringInt.trim().isEmpty()) {
                return 0;
            }
            int rating = Integer.parseInt(stringInt.trim());
            return rating;
        } catch (NumberFormatException e) {
            System.out.println("숫자 변환 과정 오류: " + stringInt);
            return 0;
        }
    }
    // String 타입 숫자를 int형 정수 타입으로 변경하는 메소드
    public Integer termsOfServiceRatingStringToInt(String stringInt) {
        try {
            if (stringInt == null || stringInt.trim().isEmpty()) {
                return 0;
            }
            int rating = Integer.parseInt(stringInt.trim());
            return rating;
        } catch (NumberFormatException e) {
            System.out.println("숫자 변환 과정 오류: " + stringInt);
            return 0;
        }
    }
    // String 타입 숫자를 int형 정수 타입으로 변경하는 메소드
    public Integer privacySecurityRatingStringToInt(String stringInt) {
        try {
            if (stringInt == null || stringInt.trim().isEmpty()) {
                return 0;
            }
            int rating = Integer.parseInt(stringInt.trim());
            return rating;
        } catch (NumberFormatException e) {
            System.out.println("숫자 변환 과정 오류: " + stringInt);
            return 0;
        }
    }
}
