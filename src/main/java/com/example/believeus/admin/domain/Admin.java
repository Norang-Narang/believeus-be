package com.example.believeus.admin.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;        // 관리자 계정 ID

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;            // 관리자 실명

    @Column(nullable = false, unique = true)
    private String phoneNumber;        // 연락처

    @Column(nullable = false)
    private String centerName;          // 센터 이름

    @Column(nullable = false)
    private Boolean hasBathVehicle;     // 목욕 차량 보유 여부

    @Column(nullable = false)
    private String address;             // 센터 주소

    private String centerGrade;         // 센터 등급 (선택)
    private String operatingPeriod;      // 운영 기간 (선택)
    private String introduction;        // 한 줄 소개 (선택)
    private String profileImageUrl;      // 프로필 사진 URL (선택)

    public Admin(String username, String password, String name, String phoneNumber, String centerName,
                 Boolean hasBathVehicle, String address, String centerGrade, String operatingPeriod, String introduction, String profileImageUrl) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.centerName = centerName;
        this.hasBathVehicle = hasBathVehicle;
        this.address = address;
        this.centerGrade = centerGrade;
        this.operatingPeriod = operatingPeriod;
        this.introduction = introduction;
        this.profileImageUrl = profileImageUrl;
    }
}
