package com.example.believeus.admin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminSignupRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String passwordConfirm;

    @NotBlank
    private String name;

    @NotBlank
    @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다.")
    private String phoneNumber;

    @NotBlank
    private String centerName;          // 센터 이름

    @NotNull
    private Boolean hasBathVehicle;      // 목욕 차량 소유 여부

    @NotBlank
    private String address;             // 주소

    private String centerGrade;         // 센터 등급 (선택)
    private String operatingPeriod;      // 운영 기간 (선택)
    private String introduction;        // 한 줄 소개 (선택)
    private String profileImageUrl;      // 프로필 사진 URL (선택)
}
