package com.example.believeus.senior.dto;

import com.example.believeus.senior.Senior;
import com.example.believeus.util.Location;
import lombok.Data;
import java.time.LocalDate;

@Data
public class SeniorRequestDTO {
    private String name;
    private LocalDate birthDate;
    private Senior.Gender gender;
    private Senior.CareGrade careGrade;
    private Location location;
    private String careNeeds;
    private Long adminId;
}
