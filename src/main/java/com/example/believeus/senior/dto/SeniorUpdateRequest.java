package com.example.believeus.senior.dto;

import com.example.believeus.senior.Senior;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record SeniorUpdateRequest(
    String name,
    int age,
    LocalDate birthDate,
    String address,
    int height,
    int weight,
    Senior.CareGrade grade,
    String careNeeds
) {

}
