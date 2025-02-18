package com.example.believeus.workingcondition.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateWorkingConditionRequest {

    private Long id;

    private String location;

    private String availableTime;

    private Integer preferredSalary;
}
