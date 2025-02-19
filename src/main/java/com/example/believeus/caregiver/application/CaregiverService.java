package com.example.believeus.caregiver.application;

import com.example.believeus.auth.domain.Role;
import com.example.believeus.auth.domain.User;
import com.example.believeus.auth.repository.UserRepository;
import com.example.believeus.caregiver.domain.Caregiver;
import com.example.believeus.caregiver.domain.CaregiverCertificate;
import com.example.believeus.caregiver.dto.CaregiverDetailsRequestDTO;
import com.example.believeus.caregiver.repository.CaregiverCertificateRepository;
import com.example.believeus.caregiver.repository.CaregiverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CaregiverService {
    private final CaregiverRepository caregiverRepository;
    private final CaregiverCertificateRepository certificateRepository;
    private final UserRepository userRepository;

    public void registerCaregiverDetails(CaregiverDetailsRequestDTO request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        user.updateRole(Role.ROLE_CAREGIVER);
        user.completeRegistration();
        userRepository.save(user);
        userRepository.flush();

        Caregiver caregiver = Caregiver.createCaregiver(
                user,
                request.getName(),
                request.getPhoneNumber(),
                request.isHasVehicle(),
                request.isHasDementiaTraining(),
                request.getExperienceYears(),
                request.getMajorExperience(),
                request.getIntroduction(),
                request.getProfileImageUrl()
        );
        caregiverRepository.save(caregiver);
        caregiverRepository.flush();

        // 자격증 저장
        if (request.getCertificates() != null && !request.getCertificates().isEmpty()) {
            List<CaregiverCertificate> certificates = request.getCertificates().stream()
                    .filter(cert -> cert.getType() != null && cert.getNumber() != null)
                    .map(cert -> CaregiverCertificate.builder()
                            .caregiver(caregiver)
                            .certificateType(cert.getType())
                            .certificateNumber(cert.getNumber())
                            .build())
                    .collect(Collectors.toList());

            if (!certificates.isEmpty()) {
                certificateRepository.saveAll(certificates);
                certificateRepository.flush();
            }
        }
    }
}

