package com.example.believeus.matching.dto;

import com.example.believeus.caregiver.domain.Caregiver;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MatchingResponseDTO {
    private final Long caregiverId;
    private final String name;
    private final String phoneNumber;
    private final int experienceYears;
    private final boolean hasVehicle;
    private final boolean hasDementiaTraining;
    private final int matchingScore;
}
