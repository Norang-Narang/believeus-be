package com.example.believeus.caregiver.application;

import com.example.believeus.caregiver.domain.Caregiver;
import com.example.believeus.caregiver.domain.CaregiverCertificate;
import com.example.believeus.caregiver.dto.CaregiverSignupRequest;
import com.example.believeus.caregiver.repository.CaregiverCertificateRepository;
import com.example.believeus.caregiver.repository.CaregiverRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CaregiverService {

    private final CaregiverRepository caregiverRepository;
    private final CaregiverCertificateRepository caregiverCertificateRepository;

    public void signUp(@Valid CaregiverSignupRequest request) {
        validateRequest(request);       // 검증 로직 먼저 실행

        saveCaregiverWithCertificates(request);     // DB 저장 로직 별도 메서드에서 실행
    }

    private void validateRequest(CaregiverSignupRequest request) {
        // 필수 필드 검증
        if (request.getName() == null || request.getName().isBlank()) {
            throw new IllegalArgumentException("이름은 필수 입력 항목입니다.");
        }
        if (request.getPhoneNumber() == null || request.getPhoneNumber().isBlank()) {
            throw new IllegalArgumentException("전화번호는 필수 입력 항목입니다.");
        }
    }

    @Transactional
    public void saveCaregiverWithCertificates(CaregiverSignupRequest request) {
        Caregiver caregiver = Caregiver.builder()
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .hasVehicle(request.isHasVehicle())
                .hasDementiaTraining(request.isHasDementiaTraining())
                .experienceYears(request.getExperienceYears())
                .majorExperience(request.getMajorExperience())
                .introduction(request.getIntroduction())
                .profileImageUrl(request.getProfileImageUrl())
                .build();

        caregiverRepository.save(caregiver);

        // 자격증 정보 저장
        for (CaregiverSignupRequest.CertificateRequest certRequest : request.getCertificates()) {
            CaregiverCertificate certificate = CaregiverCertificate.builder()
                    .certificateType(certRequest.getCertificateType())
                    .certificateNumber(certRequest.getCertificateNumber())
                    .build();

            // 자격증과 요양보호사 연관 설정
            caregiver.addCertificate(certificate);
            caregiverCertificateRepository.save(certificate);
        }
    }
}

