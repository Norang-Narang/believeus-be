package com.example.believeus.matching.dto;

import com.example.believeus.util.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CaregiverMatchDTO {
    private Long caregiverId;               // 요양보호사 ID
    private String name;                    // 요양보호사 이름
    private String phoneNumber;             // 연락처
    private String profileImageUrl;         // 프로필 사진 URL
    private Location primaryLocation;       // 주요 활동 지역
    private int experienceYears;            // 경력
    private boolean hasVehicle;             // 차량 보유 여부
    private boolean hasDementiaTraining;    // 치매 교육 여부
    private int matchingScore;              // 매칭 점수
}
