package com.example.believeus.caregiver.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "caregivers")
public class Caregiver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 20, unique = true)
    private String phoneNumber;

    private boolean hasVehicle;             // 차량소유여부
    private boolean hasDementiaTraining;    // 치매교육 이수 여부

    private String experienceYears;         // 경력 기간
    private String majorExperience;         // 주요 경력
    private String introduction;            // 한줄 소개
    private String profileImageUrl;         // 프로필 사진 URL

    // 요양보호사 <-> 자격증 1:N 관계
    @OneToMany(mappedBy = "caregiver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CaregiverCertificate> certificates = new ArrayList<>();

    @Builder
    public Caregiver(String name, String phoneNumber, boolean hasVehicle, boolean hasDementiaTraining,
                     String experienceYears, String majorExperience, String introduction, String profileImageUrl) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.hasVehicle = hasVehicle;
        this.hasDementiaTraining = hasDementiaTraining;
        this.experienceYears = experienceYears;
        this.majorExperience = majorExperience;
        this.introduction = introduction;
        this.profileImageUrl = profileImageUrl;
    }

    // 자격증 추가 메서드
    public void addCertificate(CaregiverCertificate certificate) {
        certificates.add(certificate);
        certificate.setCaregiver(this);
    }

    // 프로필 수정 메서드
    public void updateProfile(String name, String phoneNumber, String experienceYears, String majorExperience,
                              String introduction, String profileImageUrl) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.experienceYears = experienceYears;
        this.majorExperience = majorExperience;
        this.introduction = introduction;
        this.profileImageUrl = profileImageUrl;
    }
}
