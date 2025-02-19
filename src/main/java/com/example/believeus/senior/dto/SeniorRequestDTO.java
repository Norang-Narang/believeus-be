package com.example.believeus.senior.dto;

import com.example.believeus.senior.Senior;
import lombok.Data;
import java.time.LocalDate;

@Data
public class SeniorRequestDTO {
    private String name;
    private LocalDate birthDate;
    private Senior.Gender gender;
    private int height;
    private int weight;
    private Senior.CareGrade careGrade;
    private String address;
    private String careNeeds;
    private Long adminId;
}
