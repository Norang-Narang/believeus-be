package com.example.believeus.caregiver.dto;

import com.example.believeus.caregiver.domain.CertificateType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    private String phoneNumber;

    private boolean hasVehicle;
    private boolean hasDementiaTraining;

    // 선택 입력 항목
    private String experienceYears;
    private String majorExperience;
    private String introduction;
    private String profileImageUrl;

    @Valid
    @NotEmpty(message = "자격증 정보는 필수 입력 항목입니다.")
    private List<CertificateRequest> certificates;

    @Getter
    @NoArgsConstructor
    public static class CertificateRequest {
        @NotNull(message = "자격증 종류는 필수 선택입니다.")
        private CertificateType certificateType;

        @NotBlank(message = "자격증 번호는 필수 입력 항목입니다.")
        private String certificateNumber;
    }
}
