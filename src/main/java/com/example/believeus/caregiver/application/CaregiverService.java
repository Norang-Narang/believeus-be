package com.example.believeus.caregiver.application;

import com.example.believeus.caregiver.domain.Caregiver;
import com.example.believeus.caregiver.domain.CaregiverCertificate;
import com.example.believeus.caregiver.dto.CaregiverSignupRequest;
import com.example.believeus.caregiver.repository.CaregiverCertificateRepository;
import com.example.believeus.caregiver.repository.CaregiverRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CaregiverService {

    private final CaregiverRepository caregiverRepository;
    private final CaregiverCertificateRepository caregiverCertificateRepository;

    @Transactional
    public void signUp(CaregiverSignupRequest request) {
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
                    .caregiver(caregiver)
                    .build();
            caregiverCertificateRepository.save(certificate);
        }
    }
}
