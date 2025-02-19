package com.example.believeus.caregiver.domain;

import com.example.believeus.auth.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "caregivers")
public class Caregiver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 20, unique = true)
    private String phoneNumber;

    private boolean hasVehicle;
    private boolean hasDementiaTraining;
    private String experienceYears;
    private String majorExperience;
    private String introduction;
    private String profileImageUrl;

    // 요양보호사 <-> 자격증 1:N 관계
    @OneToMany(mappedBy = "caregiver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CaregiverCertificate> certificates = new ArrayList<>();

    public static Caregiver createCaregiver(User user, String name, String phoneNumber, boolean hasVehicle,
                                            boolean hasDementiaTraining, String experienceYears, String majorExperience,
                                            String introduction, String profileImageUrl) {
        return Caregiver.builder()
                .user(user)
                .name(name)
                .phoneNumber(phoneNumber)
                .hasVehicle(hasVehicle)
                .hasDementiaTraining(hasDementiaTraining)
                .experienceYears(experienceYears)
                .majorExperience(majorExperience)
                .introduction(introduction)
                .profileImageUrl(profileImageUrl)
                .build();
    }

    // 자격증 추가 메서드
    public void addCertificate(CaregiverCertificate certificate) {
        certificates.add(certificate);
        certificate.setCaregiver(this);
    }
}
