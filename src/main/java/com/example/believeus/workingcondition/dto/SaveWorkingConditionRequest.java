package com.example.believeus.workingcondition.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SaveWorkingConditionRequest {

    private String location;

    private String availableTime;

    private Integer preferredSalary;
}
