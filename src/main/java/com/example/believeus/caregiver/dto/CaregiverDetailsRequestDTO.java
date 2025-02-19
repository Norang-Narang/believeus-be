package com.example.believeus.caregiver.dto;

import com.example.believeus.util.Location;
import lombok.Data;
import java.util.List;

@Data
public class CaregiverDetailsRequestDTO {
    private Long userId;
    private String name;
    private String phoneNumber;
    private boolean hasVehicle;
    private boolean hasDementiaTraining;
    private String experienceYears;
    private String majorExperience;
    private String introduction;
    private String profileImageUrl;

    private List<Location> locations;
    private List<CertificateRequest> certificates;
}
