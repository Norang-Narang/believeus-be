package com.example.believeus.caregiver.dto;

import com.example.believeus.caregiver.domain.CertificateType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CaregiverSignupRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String passwordConfirm;

    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    private String name;

    @NotBlank(message = "전화번호는 필수 입력 항목입니다.")
    @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다.")
    private String phoneNumber;

    @NotBlank(message = "요양보호사 자격증 번호는 필수 입력 항목입니다.")
    @Pattern(regexp = "^제 \\d{4}-\\d{7}호$", message = "올바른 자격증 번호 형식이 아닙니다.")
    private String caregiverCertificateNumber;

    @NotNull(message = "자격증 종류는 필수 선택입니다.")
    private List<CertificateType> additionalCertificates; // 사회복지사/간호조무사 등 추가 자격증

    private boolean hasVehicle;             // 차량 소유 여부
    private boolean hasDementiaTraining;    // 치매교육 이수 여부

    // 선택 입력 항목
    private String experienceYears;         // 경력 기간
    private String majorExperience;         // 주요 경력
    private String introduction;            // 한 줄 소개
    private String profileImageUrl;         // 프로필 사진 url

    @Valid
    private List<CertificateRequest> certificates;      // 자격증 리스트

    @Getter
    public static class CertificateRequest {
        private CertificateType certificateType;

        @NotBlank(message = "자격증 번호는 필수 입력 항목입니다.")
        private String certificateNumber;
    }
}
