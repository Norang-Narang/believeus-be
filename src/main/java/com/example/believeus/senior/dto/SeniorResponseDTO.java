package com.example.believeus.senior.dto;

import com.example.believeus.senior.Senior;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SeniorResponseDTO {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String gender;
    private String careGrade;
    private String address;
    private String careNeeds;
    private LocalDate createdAt;
    private Long adminId;

    public static SeniorResponseDTO fromEntity(Senior senior) {
        return SeniorResponseDTO.builder()
                .id(senior.getId())
                .name(senior.getName())
                .birthDate(senior.getBirthDate())
                .gender(senior.getGender().name())
                .careGrade(senior.getCareGrade().name())
                .address(senior.getAddress())
                .careNeeds(senior.getCareNeeds())
                .createdAt(senior.getCreatedAt())
                .adminId(senior.getAdmin().getId())
                .build();
    }
}
