package com.example.believeus.caregiver.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum CertificateType {
    CAREGIVER("요양보호사"),
    SOCIAL_WORKER_L1("사회복지사_1급"),
    SOCIAL_WORKER_L2("사회복지사_2급"),
    NURSING_ASSISTANT_L1("간호조무사_1급"),
    NURSING_ASSISTANT_L2("간호조무사_2급");

    private final String displayName;

    CertificateType(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }

    @JsonCreator
    public static CertificateType fromValue(@JsonProperty("certificateType") String value) {
        for (CertificateType type : CertificateType.values()) {
            if (type.getDisplayName().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid certificate type: " + value);
    }
}
