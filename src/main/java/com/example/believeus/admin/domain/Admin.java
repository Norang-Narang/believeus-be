package com.example.believeus.admin.domain;

import com.example.believeus.auth.domain.User;
import jakarta.persistence.*;
        import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String name;            // 관리자 실명

    @Column(nullable = false, unique = true)
    private String phoneNumber;                // 연락처

    @Column(nullable = false)
    private String centerName;           // 센터 이름

    @Column(nullable = false)
    private String address;              // 센터 주소

    @Column(nullable = false)
    private Boolean hasBathVehicle;      // 목욕 차량 보유 여부

    private String centerGrade;          // 센터 등급 (선택)
    private String operatingPeriod;      // 운영 기간 (선택)
    private String introduction;         // 한 줄 소개 (선택)
    private String profileImageUrl;      // 프로필 사진 URL (선택)

    public static Admin createAdmin(User user, String name, String centerName, String phoneNumber, String address,
                                    String centerGrade, Boolean hasBathVehicle,
                                    String operatingPeriod, String introduction, String profileImageUrl) {
        return Admin.builder()
                .user(user)
                .name(name)
                .centerName(centerName)
                .phoneNumber(phoneNumber)
                .address(address)
                .hasBathVehicle(hasBathVehicle)
                .centerGrade(centerGrade)
                .operatingPeriod(operatingPeriod)
                .introduction(introduction)
                .profileImageUrl(profileImageUrl)
                .build();
    }
}
