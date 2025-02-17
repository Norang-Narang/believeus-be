package com.example.believeus.caregiver.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "caregiver_certificates")
public class CaregiverCertificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CertificateType certificateType;            // 자격증 종류 (ENUM)

    @Column(nullable = false, unique = true, length = 20)
    @Pattern(regexp = "^(제 \\d{4}-\\d{7}호|[12]-\\d{5,6})$", message = "올바른 자격증 번호 형식이 아닙니다.")
    private String certificateNumber; // 자격증 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caregiver_id", nullable = false)
    private Caregiver caregiver; // 요양보호사 FK

    @Builder
    public CaregiverCertificate(CertificateType certificateType, String certificateNumber, Caregiver caregiver) {
        this.certificateType = certificateType;
        this.certificateNumber = certificateNumber;
        this.caregiver = caregiver;
    }

    public void setCaregiver(Caregiver caregiver) {
        this.caregiver = caregiver;
    }
}
