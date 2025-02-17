package com.example.believeus.caregiver.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CaregiverProfileUpdateRequest {
    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    private String name;

    @NotBlank(message = "전화번호는 필수 입력 항목입니다.")
    private String phoneNumber;

    private String experienceYears;         // 경력 기간
    private String majorExperience;         // 주요 경력
    private String introduction;            // 한 줄 소개
    private String profileImageUrl;         // 프로필 사진 url
}

