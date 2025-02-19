package com.example.believeus.admin.dto;

import lombok.Data;

@Data
public class AdminDetailsRequestDTO {
    private Long userId;
    private String name;
    private String centerName;
    private String phone;
    private String address;
    private String centerGrade;
    private Boolean hasBathVehicle;
    private String operatingPeriod;
    private String introduction;
    private String profileImageUrl;
}
