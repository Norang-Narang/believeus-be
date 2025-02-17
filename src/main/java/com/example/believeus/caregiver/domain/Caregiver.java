package com.example.believeus.caregiver.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sun.lwawt.LWChoicePeer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "caregivers")
public class Caregiver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                // 요양보호사 ID

    @Column(nullable = false, length = 50)
    private String name;            // 이름

    @Column(nullable = false, length = 20, unique = true)
    private String phoneNumber;     // 연락처

    @Column(nullable = false)
    private String address;         // 주소

    @Column(nullable = false)
    private boolean hasVehicle;     // 차량소유여부

    @Column(nullable = false)
    private boolean hasTraining;   // 치매교육이수여부

    @Column(length = 255)
    private String profileImageUrl;     // 프로필 이미지 url

    @Column(length = 500)
    private String introduction;        // 한줄 소개

    @Column(nullable = false)
    private LocalDate registrationDate = LocalDate.now();   // 등록일

    @OneToMany(mappedBy = "caregiver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CaregiverCertificate> certificates = new ArrayList<>();    // 자격증 리스트

    @Builder
    public Caregiver(String name, String phoneNumber, boolean hasVehicle, boolean hasTraining,
                     String address, String profileImageUrl, String introduction) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.hasVehicle = hasVehicle;
        this.hasTraining = hasTraining;
        this.address = address;
        this.profileImageUrl = profileImageUrl;
        this.introduction = introduction;
    }

    // 자격증 추가 메서드
    public void addCertificate(CaregiverCertificate certificate) {
        this.certificates.add(certificate);
        certificate.setCaregiver(this);
    }

}
