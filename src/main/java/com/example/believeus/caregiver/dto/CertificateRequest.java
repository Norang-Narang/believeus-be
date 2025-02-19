package com.example.believeus.caregiver.dto;

import com.example.believeus.caregiver.domain.CertificateType;
import lombok.Data;

@Data
public class CertificateRequest {
    private CertificateType type;       // 자격증 종류 (ENUM)
    private String number;              // 자격증 번호
}
